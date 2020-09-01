package com.spayker.consumer.controller;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;
import com.spayker.consumer.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 *  A controller layer with all needed (for now) methods.
 *  Last ones are called when request handling starts happening.
 *  Requests come on correspond url that linked by RequestMapping annotation with an appropriate declared method below.
 **/
@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    /**
     *  Returns an Consumer instance found by id.
     *  @param id Long value to make search by id possible
     *  @return found Consumer entity
     **/
    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        return new ResponseEntity<>(trainingService.findTrainingById(id), HttpStatus.OK);
    }

    /**
     *  Returns all trainings by account id
     *  @param account - Account data container to be used in search
     *  @return ResponseEntity with list of found trainings
     **/
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Training>> getAllTrainingsByAccountId(@Valid @RequestBody Account account) {
        return new ResponseEntity<>(trainingService.findTrainingsByAccount(account), HttpStatus.OK);
    }

}
