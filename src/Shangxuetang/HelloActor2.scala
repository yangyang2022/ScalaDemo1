package Shangxuetang

import scala.actors.Actor

case class Login(val name:String,val password:String)
case class Register(val name:String,val password:String)

class UserLoginActor extends Actor{
    override def act(): Unit ={
        while (true){
            receive{
                case Login(name,password) => println("Login: name"+name+" password: "+password)
                case Register(name,password) => println("register: name"+name+" password: "+password)
            }
        }
    }
}
object HelloActor2 {
    def main(args: Array[String]): Unit = {
        val userlogin = new UserLoginActor
        userlogin.start()
        userlogin!Login("yangyang","123123")
        userlogin!Register("yangyang","123456")
    }
}
