package com.example.LineBot_Simple_Demo.controller;

import com.example.LineBot_Simple_Demo.model.Message;
import com.example.LineBot_Simple_Demo.service.AuthenticateServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class Controller {


    @PostMapping("/line")
    public ResponseEntity handleTextMessageEvent(@RequestHeader("X-Line-Signature") String X_Line_Signature, @RequestBody String requestBody) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        //System.out.println("POST Body: " + requestBody);
        AuthenticateServiceImp Service = new AuthenticateServiceImp();
        if(!Service.authenticate(X_Line_Signature, requestBody)) return new ResponseEntity("Authenticate Failed!", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity("Success!", HttpStatus.OK);
    }

}

