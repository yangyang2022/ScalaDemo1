package DemoScalaApi

import java.io.{File, FileOutputStream, PrintWriter}

import scala.io.Source
import scala.util.{Success, Try,Failure}

object manage{
    def using[A<:{def close():Unit},B](resource:A)(f:A=>B) = {
        try{
            f(resource)
        }finally {
//            resource.close()
        }
    }
}
//class GrandParant
//class Parent extends GrandParant
//class Child extends Parent
//
//class InvariantClass[A]
//class CovariantClass[+A]
//class ContravariantClass[-A]
//
//class LinkList[A]{
//    private class Node[A](elem:A){
//        var next:Node[A] = _
//        override def toString: String = elem.toString
//    }
//    private var head:Node[A] = _
//    def add(elem:A): Unit ={
//        val n =new Node[A](elem)
//        n.next = head
//        head = n
//    }
//    private def printNodes(n:Node[A]): Unit ={
//        if(n != null){
//            println(n)
//            printNodes(n.next)
//        }
//    }
//    def printAll(){printNodes(head)}
//}
//
//trait Animal
//class Dog extends Animal{
//    override def toString: String = "Dog"
//}
//class SuperDog extends Dog{
//    override def toString: String = "super dog"
//}
//class FunnyDog extends Dog {
//    override def toString: String = "funny dog"
//}
//class Dogg{def speak(){println("woof!!!")}}
//class King{def speak(){println("king!!!")}}


//trait Animal{
//    def speak
//}
//class Dog(var name:String)extends Animal{
//    override def speak: Unit = println("Woof!!!")
//
//    override def toString: String = name
//}
//class SuperDog(name:String) extends Dog(name){
//    override def speak: Unit = println("i am super dog !!! woof !!!")
//
//    def useSuperPower = println("use super power!!!")
//}

trait Animal
final case class Dog(name:String) extends Animal
final case class Cat(name:String) extends Animal

trait HumanLike[A]{
    def speak(speaker:A):Unit
}
object HumanLike{
    implicit object DogIsHumanlike extends HumanLike[Dog]{
        override def speak(speaker: Dog): Unit = println(s"I am a dog, my name is ${speaker.name}")
    }
}


object FileDemo {

//    def invarMethod(x:InvariantClass[Parent]){}
//    def covarMthod(x:CovariantClass[Parent]){}
//    def contraMthod(x:ContravariantClass[Parent]){}
//
//    //structure type
//    def callSpeck[A<:{def speak():Unit}](elem:A): Unit ={
//        elem.speak()
//    }

    import collection.mutable.ArrayBuffer
//    def makeDogSpeak(dogs:ArrayBuffer[Dog]) = dogs.foreach(_.speak)

    def makeHumanlike[A](animal:A)(implicit humanLike: HumanLike[A]): Unit ={
        humanLike.speak(animal)
    }
    def timer[A](blockCode: =>A) = {
        val startTime = System.nanoTime()
        val result = blockCode
        val stopTime = System.nanoTime()
        val delta = stopTime- startTime
        (result,delta/1000000d)
    }

    def readFile(filePath:String) = Source.fromFile(new File(filePath)).getLines


    // Try -> SUCCESS -> FAIL
    def readTextFile(filename:String):Try[List[String]] = {
        Try(Source.fromFile(filename).getLines.toList)
    }

    def toInt(s:String):Option[Int] = {
        try{
            Some(Integer.parseInt(s.trim))
        }catch {
            case e:Exception => None
        }
    }

    def main(args: Array[String]): Unit = {
//        val path = FileDemo.getClass.getResource("/data1.txt").getPath


//        val bag = List("1","2","3","hello","5","foo","6","foo")
//
//        println(bag.map(toInt).flatten.sum)
//        println(bag.flatMap(toInt).sum)


//        readTextFile(path) match {
//            case Success(lines) => lines.foreach(println)
//            case Failure(f) => println(f)
//        }

//        val (result,time) = timer(readFile(path))
//        println(result.size +" : "+time)


//        makeHumanlike(new Dog("hehe     "))


//        def add[A](x:A,y:A)(implicit numeric: Numeric[A]):A = numeric.plus(x,y)
//        println(add(1,2))
//        println(add(1.1,2.2))
//        println(add(1,2.5f))

//        val fido = new Dog("Fido")
//        val wonder = new SuperDog("Wonder Dog")
//        val shaggy = new SuperDog("Shaggy")
//        val dogs = ArrayBuffer[Dog]()
//        dogs += fido
//        dogs += wonder
//        makeDogSpeak(dogs)
//
//        val superDogs = ArrayBuffer[SuperDog]()
//        superDogs += shaggy
//        superDogs += wonder
//
//        makeDogSpeak(superDogs)


//        callSpeck(new Dogg)
//        callSpeck(new King)

//        val dogs = new LinkList[Dog]
//        dogs.add(new Dog)
//        dogs.add(new SuperDog)
//        dogs.add(new FunnyDog)
//
//        val superDogs = new LinkList[SuperDog]
//        superDogs.add(new SuperDog)
//
//        def printDogs(dogs:LinkList[Dog]): Unit ={
//            dogs.printAll()
//        }
//        printDogs(dogs)
//
//        val ints = new LinkList[Int]
//        ints.add(1)
//        ints.add(2)
//        ints.add(3)
//        ints.add(4)
//        ints.printAll()

//        invarMethod(new InvariantClass[Parent])
//
//        //covariant
//        covarMthod(new CovariantClass[Child])
//        covarMthod(new CovariantClass[Parent])
//
//        //contravoriant
//        contraMthod(new ContravariantClass[GrandParant])
//        contraMthod(new ContravariantClass[Parent])


//        val path  = FileDemo.getClass.getResourceAsStream("/data.txt")
//
//        val filePath = "D:\\testCode\\data.txt"
//
//        println(filePath)

        //writing to file
//        val pw = new PrintWriter(new FileOutputStream(new File(filePath)),true)
//        pw.println("hello yangyang")

//        using(scala.io.Source.fromInputStream(path)){
//            resource=>{
//                for(line <- resource.getLines()){
//                    println(line)
//                }
//            }
//        }

    }
}
