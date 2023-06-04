package com.huy.webdoan;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class WebDoAnApplication {
    public static void main(String[] args){
        SpringApplication.run(WebDoAnApplication.class, args);
    }

}
