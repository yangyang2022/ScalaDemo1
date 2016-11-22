package ActorDemo

import scala.concurrent.{Future,future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.util.{Success,Failure}


object Cloud{
    def sleep(time:Long) = Thread.sleep(time)
    def runAlgorithm(i:Int):Future[Int] = Future{
        sleep(Random.nextInt(1000))
        val result = i+10
        println(s"returning result from cloud: $result")
        result
    } andThen{
        case _ => println("hello world")
    } andThen{
        case  _ => println("hello yangyang")
    }
}
object ActorFuture {
    def sleep(time:Long) = Thread.sleep(time)
    def longRunningCompletion(i:Int):Future[Int] = Future {
        sleep(100)
        i+1
    }
    def main(args: Array[String]): Unit = {
        //part one

//        println("starting ... ")
//        val f = Future{
//            sleep(Random.nextInt(1000))
//            if(Random.nextInt(1000) > 500 ) throw new Exception("ohhh!") else 42
//        }
//        println("before completed")
//        f.onComplete{
//            case Success(value) => println(s"Got the callback, value= $value")
//            case Failure(e) =>e.printStackTrace()
//        }
//        //do other work
//        println("A ... ");sleep(200)
//        println("B ... ");sleep(200)
//        println("C ... ");sleep(200)
//        println("D ... ");sleep(200)
//        println("E ... ");sleep(200)
//        println("F ... ");sleep(200)

        //part two
//        longRunningCompletion(11).onComplete {
//            case Success(value) => println(s"result= $value")
//            case Failure(e) => e.printStackTrace()
//        }
//
//        sleep(1000)

        //part three
        println("start ... ")
        val rs1 = Cloud.runAlgorithm(10)
        val rs2 = Cloud.runAlgorithm(20)
        val rs3 = Cloud.runAlgorithm(30)

        println("before for ... ")
        val res = for{
            r1 <- rs1
            r2 <- rs2
            r3 <- rs3
        }yield (r1 + r2 + r3)

        println("before success ...")
        res onSuccess{
            case result => println(s"total is $result")
        }
        println("end ... ")
        sleep(2000)
    }
}
