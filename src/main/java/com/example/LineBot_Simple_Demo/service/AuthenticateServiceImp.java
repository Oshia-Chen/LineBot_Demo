package com.example.LineBot_Simple_Demo.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticateServiceImp implements AuthenticateService{
    public AuthenticateServiceImp(){}

    @Override
    public Boolean authenticate(String X_Line_Signature, String requestBody) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String channelSecret = "c2f72e225f35ab9a581d3faee777c7bf"; // Channel secret string
        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] source = requestBody.getBytes("UTF-8");
        String signature = Base64.encodeBase64String(mac.doFinal(source));
        // Compare x-line-signature request header string and the signature
        return signature.equals(X_Line_Signature);
    }

}

