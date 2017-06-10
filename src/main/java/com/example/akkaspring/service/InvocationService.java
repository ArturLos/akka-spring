package com.example.akkaspring.service;

import org.springframework.stereotype.Service;

@Service
public class InvocationService {

  public void invoke() {
    System.out.println("Invoke");
  }
}
