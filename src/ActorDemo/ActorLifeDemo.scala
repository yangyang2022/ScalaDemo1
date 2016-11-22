package ActorDemo

import akka.actor.Actor.Receive
import akka.actor._

case object ForceRestart

class Kenny extends Actor {
    println("enter kenny constructor ... ")


    @scala.throws[Exception](classOf[Exception])
    override def preStart(): Unit = println("kenny preStart...")


    @scala.throws[Exception](classOf[Exception])
    override def postStop(): Unit = println("kenny postStop ... ")

    @scala.throws[Exception](classOf[Exception])
    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
        println("kenny restart ... ")
        println(s"MESSAGE: ${message.getOrElse("")}")
        println(s"reason: ${reason.getMessage}")
    }

    @scala.throws[Exception](classOf[Exception])
    override def postRestart(reason: Throwable): Unit = {
        println("kenny post restart ...")
        println(s"reason: ${reason.getMessage}")
        super.postRestart(reason)
    }


    override def receive: Receive = {
        case ForceRestart => throw new Exception("boom!")
        case _ => println("kenny recived a message!")
        case _ => sender ! PoisonPill
    }
}

object ActorLifeDemo {

    def main(args: Array[String]): Unit = {
        val system = ActorSystem("lifeCycleDemo")
        val kenny = system.actorOf(Props[Kenny],name = "kenny")

        println("sending kenny a simple string message ")
        kenny ! "hello"
        Thread.sleep(1000)

        println("make kenny restart ")
        kenny ! ForceRestart
        Thread.sleep(1000)

        println("stop kenny ")
        system.stop(kenny)

        println("shutdown system ...")
        system.terminate()
    }
}
