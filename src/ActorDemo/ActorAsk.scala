package ActorDemo

import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.Actor.Receive
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._


case object AskNameMessage
class testActor extends Actor{
    override def receive: Receive = {
        case AskNameMessage => sender ! "hello"
        case _ => println("that was unexpected !")
    }
}
object ActorAsk {

    //ask is send a msg and wait reply
    def main(args: Array[String]): Unit = {

        val system = ActorSystem("askActor")
        val actor = system.actorOf(Props[testActor],name = "testActor")

        implicit val timeout = Timeout(5 seconds)

        //1 this is one way to ask another actor for information
        val future = actor ? AskNameMessage
        val res = Await.result(future,timeout.duration).asInstanceOf[String]
        println(res)

        //2 a slightly diffrently way to ask another actor for information
        val future2 = ask(actor,AskNameMessage).mapTo[String]
        val res2 = Await.result(future2,1 second)
        println(res2)

        system.terminate()
    }
}
