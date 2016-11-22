package ActorDemo

import akka.actor.Actor.Receive
import akka.actor._

case class CreateChild(name:String)
case class Name(name:String)

class Child extends Actor{
    var name = "no name"

    @scala.throws[Exception](classOf[Exception])
    override def postStop(): Unit = println(s"D'oh! They killed me ($name) : (${self.path})")

    override def receive: Receive = {
        case Name(name) => this.name = name
        case _ => println(s"Child $name get message!")
    }
}
class Parent extends Actor{
    override def receive: Receive = {
        case CreateChild(name) =>
            //parent create a child here
            println(s"Parent about to craete Child: $name")
            val child = context.actorOf(Props[Child],name=s"$name")
            child ! Name(name)
        case _ => println(s"Parent got some other msg!")
    }
}
object ActorParentChild {

    def main(args: Array[String]): Unit = {

        val system = ActorSystem("ParentChild")
        val parent = system.actorOf(Props[Parent],name = "Parent")

        //send msg to parent to create Child
        parent ! CreateChild("john")
        parent ! CreateChild("yangyang")
        Thread.sleep(500)

        //lookup yangyang then kill it
        println("Sending yangyang a PoisonPill ... ")
        val yangyang = system.actorSelection("/user/Parent/yangyang")
        yangyang ! PoisonPill
        println("yangyang killed")

        Thread.sleep(5000)
        system.terminate()

    }
}
