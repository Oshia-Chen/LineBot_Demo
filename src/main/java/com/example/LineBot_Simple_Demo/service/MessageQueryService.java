package com.example.LineBot_Simple_Demo.service;

import java.util.List;

public interface MessageQueryService {
    String save(String message);
    List<String> queryMessageListByUser(String userId);
}
