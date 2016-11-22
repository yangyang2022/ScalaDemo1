package ActorDemo

import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.Actor.Receive

class HelloActor extends Actor{
    override def receive: Receive = {
        case "hello" => println("hello back at you!")
        case _ => println("hum???")
    }
}
class HelloActorByParam(name:String) extends Actor{
    override def receive: Receive = {
        case "hello" =>{
            println(s"hello from $name")
//            sender ! "Ok i am get it"
        }
        case _ => println(s"humm??? said $name")
    }
}

object ActorHello {

    def main(args: Array[String]): Unit = {

        //an actor need an actorSystem
        val system = ActorSystem("helloActor")

        //create and start the actor
        val helloActor = system.actorOf(Props(new HelloActorByParam("yangyang")),name = "helloActor")

        //send the actor two message
        helloActor ! "hello"
        helloActor ! "yangyang"

        //shutdown the system
        system.terminate()
    }
}
