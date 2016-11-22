package Shangxuetang

import scala.actors.Actor

//send message each other
case class Message(content:String,sender:Actor)

class YangyangActor extends Actor{
    override def act(): Unit = {
        while (true){
            receive{
                case Message(content,sender) => {println("yangyang receive: "+content);sender!"i am yangyang,please call me "}
            }
        }
    }
}
class GagaActor(val yangyangActor: YangyangActor) extends Actor{
    override def act(): Unit = {
        yangyangActor ! Message("hello yangyang,how are you? ",this)
        while (true){
            receive{
                case respone:String => println("gaga recived: "+respone)
            }
        }
    }
}
object HelloActor3 {
    def main(args: Array[String]): Unit = {

        val yangyangActor = new YangyangActor
        val gagaActor = new GagaActor(yangyangActor)

        yangyangActor.start()
        gagaActor.start()

    }
}
