package com.example.LineBot_Simple_Demo.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class AuthenticateServiceImp implements AuthenticateService{

    final private Mac mac;
    public AuthenticateServiceImp() throws Exception {
        String channelSecret =  new String(Files.readAllBytes(Paths.get("channel_secret")));
        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
    }

    @Override
    public Boolean authenticate(String X_Line_Signature, String requestBody) throws UnsupportedEncodingException {
        byte[] source = requestBody.getBytes("UTF-8");
        String signature = Base64.encodeBase64String(mac.doFinal(source));
        // Compare x-line-signature request header string and the signature
        return signature.equals(X_Line_Signature);
    }

}

