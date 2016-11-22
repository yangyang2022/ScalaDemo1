package ActorDemo

import java.util.concurrent.TimeUnit

import akka.actor.Actor.Receive
import akka.actor._
import akka.pattern.gracefulStop

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class testActor extends Actor{
    override def receive: Receive = {
        case s:String => println(s"receive a msg : $s")
        case _ => println("get some other msg !!! ")
    }

    @scala.throws[Exception](classOf[Exception])
    override def postStop(): Unit = println("TestActor::postStop!!")
}

object ActorStop {
    def main(args: Array[String]): Unit = {
        val system = ActorSystem("PosionPillTest")
        val actor = system.actorOf(Props[testActor],name = "test")

//        actor ! "hello"
//        system.stop(actor)
//        actor ! PoisonPill
//        actor ! "world"
//
//        system.terminate()

        //gracefulStop
        actor ! Kill
        system.terminate()

//        try{
//            val stop:Future[Boolean] = gracefulStop(actor,2 seconds)
//            Await.result(stop,3 seconds)
//        }catch {
//            case e:Exception =>e.printStackTrace()
//        }finally {
//            system.terminate()
//        }
    }
}
