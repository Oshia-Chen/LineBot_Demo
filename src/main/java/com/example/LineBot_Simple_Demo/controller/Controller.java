package com.example.LineBot_Simple_Demo.controller;

import com.example.LineBot_Simple_Demo.service.AuthenticateServiceImp;
import com.example.LineBot_Simple_Demo.service.MessageQueryServiceImp;
import com.example.LineBot_Simple_Demo.service.SendMessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        try {
            AuthenticateServiceImp Service = new AuthenticateServiceImp();
            if (!Service.authenticate(X_Line_Signature, requestBody))
                return new ResponseEntity("Authenticate Failed!", HttpStatus.UNAUTHORIZED);
            System.out.println(queryService.save(requestBody));
            return new ResponseEntity("Success!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Failed to save message:"+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendMessage")
    public ResponseEntity sendMessageToUser(@RequestBody String requestBody){
        try{
            SendMessageServiceImp service = new SendMessageServiceImp();
            service.sendMessage(requestBody);
            return new ResponseEntity("Success!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Failed to send message:"+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

