package com.example.assessmentconsumer.service;

import com.example.assessmentconsumer.model.Assessment;
import com.example.assessmentconsumer.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Assessment> listAll (){
        return repo.findAll();

    }

//    public void save (Assessment assessment) {
//        repo.save(assessment);
//    }

    public Assessment saveJob(Assessment assessment) {
        Assessment newAssessment = new Assessment();
        System.out.println("ASSESSMENT======" + assessment.getStatus());
        assessment.setStatus("in progress");

        Assessment as = repo.save(assessment);
        return (as);
    }

    public Assessment saveJobAsDone(Assessment assessment) {

        System.out.println("ASSESSMENT======" + assessment.getStatus());
        assessment.setStatus("done");

        Assessment as = repo.save(assessment);
        return (as);
    }


    public void delete(long id) {
        repo.deleteById(id);
    }

    public Assessment get(long id) {
        return repo.findById(id).get();
    }
}
