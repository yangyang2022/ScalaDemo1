package Shangxuetang

import scala.actors.Actor

//send a int or string
class HelloActor extends Actor{
    override def act(): Unit = {
        while (true){
            receive{
                case name:String => println("hello "+name)
                case money:Int => println("how much? "+money)
            }
        }
    }
}
object HelloActor {

    def main(args: Array[String]): Unit = {

        val helloActor = new HelloActor
        helloActor.start()
        helloActor ! "yangyang"
    }
}
