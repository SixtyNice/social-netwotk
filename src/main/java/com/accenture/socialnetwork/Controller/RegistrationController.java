package com.accenture.socialnetwork.Controller;

import com.accenture.socialnetwork.Entity.UserEntity;
import com.accenture.socialnetwork.Services.RegistrationService.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> registration(@RequestBody UserEntity user) {
        if (registrationService.registration(user)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
