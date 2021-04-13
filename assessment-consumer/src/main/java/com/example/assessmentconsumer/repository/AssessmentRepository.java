package com.example.assessmentconsumer.repository;

import com.example.assessmentconsumer.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository <Assessment, Long> {
    public List<Assessment> findAll();
}
