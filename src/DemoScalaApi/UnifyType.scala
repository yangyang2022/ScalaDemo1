package DemoScalaApi

import java.time.LocalDate

import scala.Some

/**
  * Created by syy on 2016/11/13.
  */

trait Similarity{
    def isSimilar(x:Any):Boolean
    def isNotSimilar(x:Any):Boolean = !isSimilar(x)
}
class Point(xc:Int,yc:Int) extends Similarity{
    var x = xc
    var y = yc

    override def isSimilar(obj: Any): Boolean = {
        obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x
    }
}

abstract class AbsIterator{
    type T
    def hasNext:Boolean
    def next :T
}
trait RichIterator extends AbsIterator{
    def foreach(f:T => Unit) {while (hasNext) f(next) }
}

class StringIterator(s:String) extends RichIterator{
    override type T = Char

    private var i = 0;

    override def hasNext: Boolean = i < s.length

    override def next = {val ch = s charAt i;i+=1; ch }

}
abstract class Notification
case class Email(sourceEmail: String, title: String, body: String) extends Notification
case class SMS(sourceNumber: String, message: String) extends Notification
case class VoiceRecording(contactName: String, link: String) extends Notification

class Decorator(left:String,right:String){
    def layout[A](x:A) =left+x.toString+right
}

object Blah{
    def sum(list: List[Int]):Int = list.sum
}

class IntPair(val x:Int,val y:Int)
object IntPair{
    import math.Ordering
    implicit def ipord:Ordering[IntPair] = Ordering.by(ip=>(ip.x,ip.y))
}

//object Twice{
//    def apply(x:Int): Int = x*2
//    def unapply(x:Int): Option[Int] = if( (x % 2) == 0) Some(x/2) else None
//}

object Twice {
    def apply(x: Int): Int = x * 2
    def unapply(z: Int): Option[Int] = if (z%2 == 0) Some(z/2) else None
}

class Stack[T]{
    var elems:List[T] = Nil
    def push(x:T) = elems = x::elems
    def pop(){elems = elems.tail}
    def top:T = elems.head
}
object UnifyType{

    def main(args: Array[String]): Unit = {

        val list = List(1,2,3,4,5,6,7,8,9,10)

            val stack = new Stack[Int]
        stack.push(1)
        stack.push(2)
        stack.push(3)
        println(stack.top)

//        for(i<-Iterator.range(0,20);j<-Iterator.range(i,20) if i+j == 32) println(s"($i,$j)")


//        def foo(n:Int,v:Int) = for(i <- 0 until n ;j <- i until n if i+j == v) yield (i,j)
//
//        foo(20,32) foreach println


//        def even(from:Int,to:Int):List[Int] = {
//            for(i<- List.range(from,to) if i % 2 ==0) yield i
//        }
//
//        println(even(0,20))

//        val tt = Twice(22)
//
//        tt match {
//            case Twice(n) => println("res "+n)
//        }

//

//        def containsScala(s:String):Boolean = {
//            val z:Seq[Char] = s
//            z match {
//                case Seq('s','c','a','l','a',rest @_*) =>
//                    println("rest is: "+rest)
//                    true
//                case Seq(_*) =>
//                    false
//            }
//        }
//
//        val res = containsScala("scalaxhello")
//        println(res)


//        import scala.xml._

//        val df = java.text.DateFormat.getInstance()
//        val dataString = df.format(new java.util.Date())
//
//        val localDateString = LocalDate.now()
//
//        def theDate(name:String) = {
//            <dataMsg addressTo={name}>
//                Hello {name} ! Today is {dataString} ,now is {localDateString}
//            </dataMsg>
//        }
//
//        println(theDate("Yangyang"))


//        val page =
//            <html>
//                <head>
//                    <title>Hello XHTML world</title>
//                </head>
//                <body>
//                    <h1>Hello world</h1>
//                    <p><a href="scala-lang.org">Scala</a> talks XHTML</p>
//                </body>
//            </html>;
//        println(page.toString())

//        val pair1 = new IntPair(1,2)
//        println(IntPair.ipord)


//        def matchTest(x:Any):Any = x match {
//            case 1 => "one"
//            case "two" => 2
//            case y:Int => "scala int"
//        }
//
//        println(matchTest(1))
//        println(matchTest(2))
//        println(matchTest("two"))


//        val firstSms = SMS("12345", "Hello!")
//        val secondSms = SMS("12345", "Hello!")
//        if (firstSms == secondSms) {
//            println("They are equal!")
//        }
//        println("SMS is: " + firstSms)

//        val emailFromJohn = Email("john.doe@mail.com", "Greetings From John!", "Hello World!")
//        val editEmail = emailFromJohn.copy(title = "hello yangyang",body = "yangyang body!")
//
//        println(editEmail)
//        println(emailFromJohn == editEmail)
//        println(emailFromJohn.getClass == editEmail.getClass)
//        println(emailFromJohn.getClass)
//        println(editEmail.getClass)

//        def filter(xs:List[Int],p:Int=>Boolean):List[Int] = {
//            if(xs.isEmpty) xs
//            else if(p(xs.head)) xs.head::filter(xs.tail,p)
//            else filter(xs.tail,p)
//        }
//
//        def ModN(n:Int)(x:Int) = ( x % n ) == 0
//
//        println(filter(list,ModN(2)))
//        println(filter(list,ModN(3)))

//        def filter(xs:List[Int],threadHold:Int) = {
//            def process(ys:List[Int]):List[Int] = {
//                if(ys.isEmpty) ys
//                else if(ys.head < threadHold) ys.head::process(ys.tail)
//                else process(ys.tail)
//            }
//            process(xs)
//        }
//
//        println(filter(List(1,2,3,4,5,6),3))

//         def apply(f:Int=>String,v:Int) = f(v)
//
//        val deco = new Decorator("[ "," ]")
//        println(apply(deco.layout,7))
//
//        println(apply(e=>e+" hello",123))


//        val s = "hello world"
//        class Iter extends StringIterator(s) with RichIterator
//
//        val iter = new Iter
//        iter foreach println

//        val set = new scala.collection.mutable.LinkedHashSet[Any]
//        set += " hello yangyang"
//        set += main _
//
//        val iter = set.iterator
//        while(iter.hasNext) println(iter.next())
//
//        set.foreach(println)

//        val point = new Point(11,22)
//        val point2 = new Point(111,33)
//
//        println(point.isSimilar(point2))
//        println(point.isNotSimilar(point))



    }

}
