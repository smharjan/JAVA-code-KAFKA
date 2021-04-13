/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assessment.controller;

import com.example.assessment.model.Assessment;
import com.example.assessment.repository.AssessmentRepository;
import com.example.assessment.service.AssessmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 *
 * @author suraj
 */
@RestController
public class AssessmentController {
    
    @Autowired
    private AssessmentService assessmentService;
    
    @Autowired
    AssessmentRepository assessmentRepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    @Autowired
    private Gson gson;
 
    @PostMapping("/assessment")
    public ResponseEntity<String> postModelToKafka(@RequestBody Assessment emp)
            throws InterruptedException, ExecutionException {
        Assessment assessmentId = assessmentService.saveJob(emp);
        System.out.println("ID of the recently created data:>>>>>>>>>>  " + assessmentId.getId());
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("test", gson.toJson(emp));
        return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
    }
    
    @GetMapping("/assessment/{id}")
    public ResponseEntity<Assessment> getExerciseById(@PathVariable("id") long id) {
            Optional<Assessment> exerciseData =  assessmentRepository.findById(id);

            if (exerciseData.isPresent()) {
                    return new ResponseEntity<>(exerciseData.get(), HttpStatus.OK);
            } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
//    
//    
//    public AssessmentController(AssessmentService assessmentService) {
//        this.assessmentService = assessmentService;
//    }
//    
//    @RequestMapping("/")
//    public String viewIndex(Model model){
//        System.out.println("asdddddddddddddddd");
//        List<Assessment> listJobs = assessmentService.listAll();
//        model.addAttribute("listJobs", listJobs);
//        return "index";
//    }
//    
//    @RequestMapping("/new")
//    public String showNewJobPage(Model model) {
//        Assessment assessment = new Assessment();
//        model.addAttribute("assessment", assessment);
//
//        return "new_job";
//    }
//    
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String saveJob(@ModelAttribute("assessment") Assessment assessment) {
//        Assessment assessmentasd = assessmentService.saveJob(assessment);
//        System.out.println("GEtiing recent saved ID====>>>>>>>>>>>" + assessmentasd.getId());
//        return "redirect:/";
//    }
//    
//    @RequestMapping("/edit/{id}")
//    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("edit_job");
//        Assessment assessment = assessmentService.get(id);
//        mav.addObject("assessment", assessment);
//
//        return mav;
//    }
//    
//    @RequestMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable(name = "id") int id) {
//        assessmentService.delete(id);
//        return "redirect:/";       
//    }
}
