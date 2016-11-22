package Shangxuetang

import java.util

trait Listen{
    val name:String
    def listen() = println("your friend "+name+" is listening ... listen")
}

trait Read{
    val name:String
    def read() = println("your friend "+name+" is reading ... ")
    def listen() = println("your friend "+name+" is listening ... read")
}
trait Speak{
    val name:String
    def speak() = println("your friend "+name+" is speaking ... ")
}

class Human(val name:String) {
    def listen() = println(name+" is listening ... ")
}
class Animal(val name:String)

class Cat( override val name:String) extends Animal(name:String) with Speak with Listen with Read{
    override def toString: String = "hello "+name+" !"
    override def listen(): Unit = super.listen()
}

object TestFreind {
    def main(args: Array[String]): Unit = {
//        val  cat = new Cat("miaomiao")
//        cat.listen()
//        cat.speak()
//        println(cat)

        var arr = new util.ArrayList[String]()
        arr.add("hello")
        arr.add("world")
        println(arr)
    }
}
