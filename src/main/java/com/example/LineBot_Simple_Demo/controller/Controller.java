package com.example.LineBot_Simple_Demo.controller;

import com.example.LineBot_Simple_Demo.model.Message;
import com.example.LineBot_Simple_Demo.service.AuthenticateServiceImp;
import com.example.LineBot_Simple_Demo.service.MessageQueryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final MessageQueryServiceImp queryService;
    @Autowired
    public Controller(MessageQueryServiceImp queryService){
        this.queryService = queryService;
    }
    @PostMapping("/line")
    public ResponseEntity handleTextMessageEvent(@RequestHeader("X-Line-Signature") String X_Line_Signature, @RequestBody String requestBody) {
        //System.out.println("POST Body: " + requestBody);
        try {
            AuthenticateServiceImp Service = new AuthenticateServiceImp();
            if (!Service.authenticate(X_Line_Signature, requestBody))
                return new ResponseEntity("Authenticate Failed!", HttpStatus.UNAUTHORIZED);
            System.out.println(queryService.save(requestBody));
            return new ResponseEntity("Success!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Fail to save message:"+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/test")
//    public List<Message> getMessage() {
//        return List.of(new Message("123", "Hiya", 166777));
//    }

}

