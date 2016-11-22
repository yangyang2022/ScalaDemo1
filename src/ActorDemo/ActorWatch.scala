package ActorDemo

import akka.actor.Actor.Receive
import akka.actor._

case object Explode

class Kenny extends Actor{
    override def receive: Receive = {
        case Explode => throw new Exception("Boom")
        case  s:String => println(s"kenny recevied a msg $s")
    }

    @scala.throws[Exception](classOf[Exception])
    override def preStart(): Unit = println("kenny: preStart")

    @scala.throws[Exception](classOf[Exception])
    override def postStop(): Unit = println("kenny: postStop")

    @scala.throws[Exception](classOf[Exception])
    override def preRestart(reason: Throwable, message: Option[Any]): Unit ={
        println("kenny: preReStart")
        super.preRestart(reason, message)
    }

    @scala.throws[Exception](classOf[Exception])
    override def postRestart(reason: Throwable): Unit ={
        println("kenny: postReStart")
        super.postRestart(reason)
    }
}
class Parent extends Actor{
    //start kenny as a child,then keep a eye on it
    val kenny = context.actorOf(Props[Kenny],name = "kenny")
    context.watch(kenny)
    override def receive: Receive = {
        case Terminated(kenny) => println("OMG! they kill kenny")
        case s:String => println(s"Parent recived a msg! $s")
    }
}
object ActorWatch {

    def main(args: Array[String]): Unit = {

        val system = ActorSystem("watchSystem")
        val actor  = system.actorOf(Props[Parent],name = "parent")
        //lookup kenny
        val kenny = system.actorSelection("/user/parent/kenny")
//        kenny ! kenny
        kenny ! Explode
        kenny ! "hello"

        Thread.sleep(3000)
        println("system shutdown ")
        system.terminate()
    }
}
