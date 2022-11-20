package com.example.LineBot_Simple_Demo.service;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.json.JSONObject;

public class SendMessageServiceImp {
    public SendMessageServiceImp() {}

    public void sendMessage(String requestBody) throws Exception {
        JSONObject object = new JSONObject(requestBody);
        String message = object.getString("message");
        String userId = object.getString("userId");
        String token = new String(Files.readAllBytes(Paths.get("LineBot_Token")));
        final LineMessagingClient client = LineMessagingClient
                .builder(token)
                .build();

        final TextMessage textMessage = new TextMessage(message);
        final PushMessage pushMessage = new PushMessage(
                userId,
                textMessage);

        final BotApiResponse botApiResponse;
        botApiResponse = client.pushMessage(pushMessage).get();
        System.out.println(botApiResponse);
    }
}
