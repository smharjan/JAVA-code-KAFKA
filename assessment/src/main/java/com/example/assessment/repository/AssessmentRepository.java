/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assessment.repository;

import com.example.assessment.model.Assessment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author suraj
 */
public interface AssessmentRepository extends JpaRepository <Assessment, Long> {
    
    public List<Assessment> findAll();
    
}
