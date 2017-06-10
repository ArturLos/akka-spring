package com.example.akkaspring.configuration;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  public ActorSystem create() {
    ActorSystem actorSystem = ActorSystem.create("akka-spring-arti");
    AkkaSpringExtension.SpringExtProvider.get(actorSystem).initialize(applicationContext);
    return actorSystem;
  }

}
