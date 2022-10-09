package com.teduscheduler.service;

import com.teduscheduler.model.Semester;
import com.teduscheduler.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public List<Semester> findAll(){
        return semesterRepository.findAllBy(Sort.by("year").descending().and(Sort.by("code").descending()));
    }

    public Semester findSemesterByYearAndCode(String year, String code) {
        return semesterRepository.findSemesterByYearAndCode(year, code);
    }

    public Semester save(Semester semester){
        Semester dbSemester = semesterRepository.findSemesterByYearAndCode(semester.getYear(), semester.getCode());

        if (dbSemester != null) {
            if (dbSemester.equals(semester))
                return dbSemester;

            dbSemester.setSemesterName(semester.getSemesterName());
            dbSemester.setLastUpdate(semester.getLastUpdate());
            return semesterRepository.save(dbSemester);
        }

        return semesterRepository.save(semester);
    }
    
    public Long findLastUpdate(String year, String code){
        Semester dbSemester = semesterRepository.findSemesterByYearAndCode(year, code);

        if (dbSemester == null) {
            return -4L;
        }
        return dbSemester.getLastUpdate();
    }
}
