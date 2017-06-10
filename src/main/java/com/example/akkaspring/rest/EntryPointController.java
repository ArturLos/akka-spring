package com.example.akkaspring.rest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.example.akkaspring.actors.CounterActor;
import com.example.akkaspring.configuration.AkkaSpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class EntryPointController {

  @Autowired
  private ActorSystem actorSystem;

  private ActorRef counterActor;

  @PostConstruct
  public void init() {
    /** W ten sposób aktor nie ma stanu. Za każdym razem jest tworzony nowy. */
//    counterActor = actorSystem.actorOf(Props.create(CounterActor.class), "a1");

    /** Aktor jest pobierany z konteksu springa jako bean. W proprs jest nazwa bean'a springa
     * z scope prototype. */
    counterActor = actorSystem.actorOf(
      AkkaSpringExtension.SpringExtProvider.get(actorSystem).props("counterActor")
    );
  }



  @RequestMapping(value = "count", method = RequestMethod.GET)
  public String counterActor() throws Exception {
    counterActor.tell(new CounterActor.GetCounter(), ActorRef.noSender());
    return "-1";
  }

}
