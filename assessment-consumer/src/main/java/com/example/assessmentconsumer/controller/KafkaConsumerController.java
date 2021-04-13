/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assessmentconsumer.controller;


import com.example.assessmentconsumer.model.Assessment;
import com.example.assessmentconsumer.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.google.gson.Gson;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author suraj
 */

@RestController
@EnableAsync
@EnableScheduling
public class KafkaConsumerController {
    @Autowired
    private Gson gson;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private AssessmentService assessmentService;


    @KafkaListener(topics = { "test" })
    public void getTopics(@RequestBody String job) {
        System.out.println("Kafka event consumed is: " + job);
        Assessment model = gson.fromJson(job, Assessment.class);

        System.out.println(model.getStatus());
        assessmentService.saveJob(model);

        System.out.println("Kafka event converted value is: " + model.toString());

        System.out.println("Some work is going on....");

        Random random = new Random();
        int randInt = random.nextInt((5 - 1 + 1) + 1);
//        System.out.println("Wait for "+ randInt + " seconds!!");

        taskScheduler.schedule(
                () -> {
//                    Assessment assessmentIdDone =
                            assessmentService.saveJobAsDone(model);
                },
                new Date(OffsetDateTime.now().plusSeconds(30).toInstant().toEpochMilli())
//                new Date(OffsetDateTime.now().plusSeconds(randInt).toInstant().toEpochMilli())
        );
    }
}
