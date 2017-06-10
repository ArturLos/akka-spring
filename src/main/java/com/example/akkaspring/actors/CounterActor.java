package com.example.akkaspring.actors;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.*;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.example.akkaspring.service.InvocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class CounterActor extends AbstractActor{

  private int counter = 0 ;

  @Autowired
  private InvocationService invocationService;

  public CounterActor() {
    final CounterActor actor = this;
    receive(ReceiveBuilder
        .match(GetCounter.class, command -> {
          actor.counter ++;
          System.out.println("counter: " + actor.counter);
          invocationService.invoke();
        })

        .build()
    );
  }

  public static class GetCounter{}
}
