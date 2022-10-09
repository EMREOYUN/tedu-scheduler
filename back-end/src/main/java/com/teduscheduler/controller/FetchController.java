package com.teduscheduler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teduscheduler.config.AppConfig;
import com.teduscheduler.model.*;
import com.teduscheduler.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/fetch")
public class FetchController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private FetchService fetchService;

    @Autowired
    private SemesterService semesterService;

    private static final Logger logger = LoggerFactory.getLogger(FetchController.class);


    @PostMapping("/data")
    public ResponseEntity<?> updateData(@RequestParam("key") String fetchSecretKey,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("semester") String semesterStr) throws IOException {
        if (!fetchSecretKey.equals(appConfig.getFetchSecretKey())) {
            String errorMessage = "Wrong Secret Key!";
            logger.error(errorMessage);
            return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
        }

        if (file.isEmpty()) {
            String errorMessage = "File is Empty";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        String fileName = file.getOriginalFilename();

        if (!fileName.substring(fileName.length() - 4).equals(".xls")) {
            String errorMessage = "File extension is not correct.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Semester semester;

        try {
            semester = createSemester(semesterStr);
        } catch (Exception e) {
            String errorMessage = "Semester json is wrong.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Semester dbSemester = semesterService.findSemesterByYearAndCode(semester.getYear(), semester.getCode());

        logger.info("Database update started");

        if (fetchService.fetchCourseData(file, dbSemester)) {
            logger.info("Database updated");
            return new ResponseEntity<>("Database updated", HttpStatus.OK);
        } else {
            logger.info("Database couldn't be updated");
            return new ResponseEntity<>("Database couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    private Semester createSemester(String semesterStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(semesterStr, Map.class);
        Semester semester = new Semester();

        try {
            semester.setSemesterName(map.get("semesterName"));
            semester.setCode(map.get("code"));
            semester.setYear(map.get("year"));
            long millis = new java.util.Date().getTime();
            semester.setLastUpdate(millis);
        } catch (Exception e) {
            throw e;
        }

        semesterService.save(semester);
        return semester;
    }

}
