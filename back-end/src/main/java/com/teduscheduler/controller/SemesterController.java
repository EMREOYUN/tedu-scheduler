package com.teduscheduler.controller;

import com.teduscheduler.model.Semester;
import com.teduscheduler.service.SemesterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    private static final Logger logger = LoggerFactory.getLogger(SemesterController.class);

    @GetMapping("/getall")
    public ResponseEntity<?> getAllSemesters(){

        List<Semester> semesterList = semesterService.findAll();

        return new ResponseEntity<>(semesterList, HttpStatus.OK);
    }
    @GetMapping("/lastupdate/{year}-{code}")
    public ResponseEntity<?> GetLastUpdate(@PathVariable String year, @PathVariable String code){
        if (year.isEmpty() || code.isEmpty()) {
            String infoMessage = "Year or Semester Code cannot be empty.";
            logger.error(infoMessage);
            return new ResponseEntity<>(infoMessage, HttpStatus.BAD_REQUEST);
        }

        Long lastUpdate = semesterService.findLastUpdate(year, code);

        if (lastUpdate == -4){
            String infoMessage = "An error occured while getting last update time!";
            logger.error(infoMessage);
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        }
        else if (lastUpdate == -3){
            String infoMessage = "Please select a semester to get the update time.";
            logger.error(infoMessage);
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        }
        else if (lastUpdate == -2){
            String infoMessage = "Archived Semester";
            logger.error(infoMessage);
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        }
        else if (lastUpdate == -1){
            String infoMessage = "Tutorial Mode";
            logger.error(infoMessage);
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        }
        else {
            String season = "";
            switch (code) {
                case "001":
                    season = "Fall";
                    break;
                case "002":
                    season = "Spring";
                    break;
                case "003":
                    season = "Summer";
                    break;
            }
            Date lastUpdateDate = new Date(lastUpdate);
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");  
            String strDate = dateFormat.format(lastUpdateDate);  
            String infoMessage = "Last update for " + season + " " + year + "-" + (Integer.parseInt(year)+1) + ": " + strDate;
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        }
    }
}
