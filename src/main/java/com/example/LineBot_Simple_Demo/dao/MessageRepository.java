package com.example.LineBot_Simple_Demo.dao;

import com.example.LineBot_Simple_Demo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MessageRepository extends MongoRepository<Message, String> {

}