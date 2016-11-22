package Shangxuetang

object Demo1 {

    def main(args: Array[String]): Unit = {

//        val name = "yangyang"
//        val age = 22;
//
//        println(s"name is $name,age is $age")
//
//        val height = 1.9999
//        println(f"height is $height")
//
//        println(s"a\nb")
//        println(raw"a\nb")

//        lazy val lazyVal = {
//            println("lazy value")
//            1
//        }
//        println("after lazy")
//        println(lazyVal)

//        val scores = Map("alic"->100,"hello"->"world","yangyang"->200)
//
//        scores.get("hello")match {
//            case Some(score) => println(score)
//            case None => println("No score")
//        }

//        def max(x:Int,y:Int):Int = if(x>y) x else y
//
//        println(max(1,2))

        def add(x:Int,y:Int) = x+y
//
//        def add2 = add(_:Int,2)
//        println(add2(3))

//        def multi(x:Int)(y:Int) = x*y
//        def m2 = multi(2)_
//
//        println(m2(100))

//        val t = ()=>333
//        def fun(c:()=>Int): Int ={
//            println(c())
//            1111
//        }
//        println(fun(t))

//        def fun(call:(Int,Int)=>Int) = {
//            println(call(123,123))
//        }
//        fun((x:Int,y:Int)=>{println(x*y);x*y})
//        fun(add)

//        def add3(x:Int,y:Int,z:Int):Int = {
//            def add2(x:Int,y:Int):Int = x+y
//            add2(add2(x,y),z)
//        }
//
//        println(add3(1,2,3))

//        def sum(f:Int=>Int):(Int,Int)=>Int = {
//            def sumF(a:Int,b:Int):Int = if(a>b) 0 else f(a) + sumF(a+1,b)
//            sumF
//        }
//        def sumF = sum(e=>e+1)
//        println(sumF(1,10))

//        for(i <- 1 until 100) println(i)

//        for(i <- 1 to 100 if i % 2 == 1;if i % 5 >3 ) println(i)

        //====================== collection =====================
        //==== RDD -> Real distributed data

        val list = List(1,2,3,4,5,1,2,3,4,5)
//        println(list(1))
//        println(list.map(e=>{println("***"+e);e+2}))

//        println(list.map(_*2))
//        var t2 = list.+:("test")
//        println(t2)
//        println(5::list)
//        val t2 = list::6::List(1,2,3)::Nil
//        println(t2)
//        t2.foreach(println(_))

//        list.distinct.foreach(println)
//        println(list.reduce(_-_))
//        println(list.sum)
//        println(list.reduce((x,y)=>{println(x+"-"+y);x+y}))
//        println(0.asInstanceOf[Int])

//        val tuple = (1,2,3,"hello")
//        println(tuple._1+tuple._4)

//        var map = Map[String,Int]("a"->1,"b"->2)
//        println(map("a"))
//        map += ("c"->3)
//        println(map)
//        map.foreach(println)
//
//        val mapp = Map(("a",1),("b",2),("hello","world"))
//        println(mapp("hello"))
//        mapp.values.foreach(println)


        //======================= test class ================

        val demo = new Demo1
        demo.printStr("nihao yangyang")
        println(Demo1.ss)
        Demo1.prints("this is objetc class")
    }

    val ss = "hello object"
    def prints(s:String) = println(s)
    class Demo1 {
        val s = "hello world class"
        def printStr(s:String) = println(s)
    }
}
