package com.example.LineBot_Simple_Demo.service;

import com.example.LineBot_Simple_Demo.dao.MessageRepository;
import com.example.LineBot_Simple_Demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageQueryServiceImp implements MessageQueryService{
    public MessageQueryServiceImp(){}

    @Autowired
    public MessageRepository messageRepository;

    @Override
    public String save(@NonNull String message) {
        Message messageObj = new Message();
        //transfer message string to Message object.
        JSONObject object = new JSONObject(message);
        for(int i=0; i<object.getJSONArray("events").length(); i++) {
            JSONObject event = object.getJSONArray("events").getJSONObject(i);
            String eventType = event.getJSONObject("message").getString("type");
            if ("text".equals(eventType)) {
                messageObj.setMessage(event.getJSONObject("message").getString("text"));
                messageObj.setTimeStamp(event.getInt("timestamp"));
                messageObj.setUserId(event.getJSONObject("source").getString("userId"));
                return messageRepository.save(messageObj).getUserId();
            }
        }
        return "";
    }

    @Override
    public List<String> queryMessageListByUser(@NonNull String userId){
        List<Message> list = messageRepository.findAll();
        List<String> messagesOfUser = new ArrayList<>();
        for (Message message : list) {
            String id = message.getUserId();
            if(userId.equals(id)) messagesOfUser.add(message.getMessage());
        }
        return messagesOfUser;
    }

}

