package com.example.LineBot_Simple_Demo.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthenticateService {
    public Boolean authenticate(String X_Line_Signature, String requestBody) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException;

}
