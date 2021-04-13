/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assessment.service;

import com.example.assessment.model.Assessment;
import com.example.assessment.repository.AssessmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author suraj
 */
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
//        System.out.println("ASSESSMENT======" + assessment.getStatus());
        assessment.setStatus("new");
        
        Assessment entity = repo.save(assessment);
	return (entity);
    }
    
    
    public void delete(long id) {
        repo.deleteById(id);
    }

    public Assessment get(long id) {
        return repo.findById(id).get();
    }
    
    
}
