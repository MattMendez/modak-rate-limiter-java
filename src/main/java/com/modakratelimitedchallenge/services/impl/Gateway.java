package com.modakratelimitedchallenge.services.impl;

import org.springframework.stereotype.Service;

@Service
public class Gateway {

    void send(String userId, String message) {

        System.out.println("sending message to user " + userId);

    }
}
