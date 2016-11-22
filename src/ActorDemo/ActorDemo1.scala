package ActorDemo

import akka.actor.Actor.Receive
import akka.actor._

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class Ping(pong : ActorRef) extends Actor{
    var count = 0
    def incrementAndPrint{ count += 1; println(count+" ping")}


    override def receive: Receive = {
        case StartMessage =>
            incrementAndPrint
            pong ! PingMessage
        case PongMessage =>
            incrementAndPrint
            if(count > 99 ){
                sender ! StopMessage
                println("ping stopped!!")
                context.stop(self)
            }else{
                sender ! PingMessage
            }
        case _ => println("Ping got something excepted!!!")
    }
}

class Pong extends Actor{
    override def receive: Receive ={
        case PingMessage =>
            println("pong")
            sender ! PongMessage
        case StopMessage =>
            println("pong stopped!!!")
            context.stop(self)
        case _ => println("pong got some excepted!!!")
    }
}


object ActorDemo1 {


    def main(args: Array[String]): Unit = {

        val system = ActorSystem("PingPongSystem")
        val pong = system.actorOf(Props[Pong],name = "pong")
        val ping = system.actorOf(Props(new Ping(pong)),name = "ping")

        //start
        ping ! StartMessage

        Thread.sleep(1000)
        system.terminate()
    }
}
