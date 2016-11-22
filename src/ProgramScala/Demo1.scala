package ProgramScala

import scala.annotation.tailrec
import scala.io.Source
import scala.util.control.NonFatal


object manage{
    def apply[R<:{def close():Unit},T](resource: =>R)(f:R=>T) = {
        var res:Option[R] = None
        try{
            res = Some(resource)
            f(res.get)
        }catch {
            case NonFatal(ex) => println(s"Nonfatal exception! $ex")
        }finally {
            if(res != None){
                println(s"closing resource ...")
                res.get.close()
            }
        }
    }
}
object Breed extends Enumeration{
    type Breed = Value
    val doberman = Value("doberman")
    val yorkie = Value("yorkie terrier")
    val scottie = Value("Scottish terrier")
    val dane = Value("Great Dane")
    val portie = Value("Portuguese Water Dog")
}
object WeekDay extends Enumeration{
    type WeekDay = Value
    val MON,TUE,WED,THU,FRI,SAT,SUN = Value
}

trait Logging{
    def info(message:String):Unit
    def warning(message:String):Unit
    def error(message:String):Unit
}
trait StdoutLogging extends Logging{
    override def info(message: String): Unit = println(s"INFO: $message")

    override def warning(message: String): Unit = println(s"Warnning: $message")

    override def error(message: String): Unit = println(s"error: $message")
}

class ServiceImportance(name:String){
    def work(i:Int):Int = {
        println(s"service: Doing impoertance work! $i")
        i+1
    }
}

case class Address(street:String,city:String,country:String)
//case class Person(name:String,age:Int,address:Address)
case class Person(name:String,age:Int)

case class With[A,B](a:A,b: B)

sealed abstract class HttpMethod{
    def body:String
    def bodyLength = body.length
}

case class Connect(body:String) extends HttpMethod
case class Delete(body:String) extends HttpMethod
case class Get1(body:String) extends HttpMethod


object Demo1 {


   def isEvne(n:Int) = n % 2 == 0

    def countLines(fileName:String) = {
        println()
        var source:Option[Source] = None
        try{
            source = Some(Source.fromFile(fileName))
            val size = source.get.getLines.size
            println(s"file $fileName has $size lines ")
        }catch {
            case NonFatal(ex) => println(s"Non fatal exception!$ex")
        }finally {
            for(s<-source){
                println(s"closing $fileName")
                s.close()
            }
        }
    }


    def handle(method:HttpMethod) = method match {
        case Connect(body) => s"connect: (length: ${method.bodyLength}) $body"
        case Delete(body) => s"delete: (length: ${method.bodyLength}) $body"
        case Get1(body) => s"Get: (length: ${method.bodyLength}) $body"
    }

    def main(args: Array[String]): Unit = {


        println("hello world")



//        val list = List(1,2,3,4,5)
//
//        def reduceLeft[A,B](s:Seq[A])(f:A=>B):Seq[B] = {
//            @tailrec
//            def rl(acc:Seq[B],s2:Seq[A]):Seq[B] = s2 match {
//                case head +: tail => rl(f(head)+:acc,tail)
//                case _ => acc
//            }
//            rl(Seq.empty[B],s)
//        }
//
//        def reduceRight[A,B](s:Seq[A])(f:A=>B):Seq[B] = s match {
//            case head +: tail => f(head) +: reduceRight(tail)(f)
//            case _ => Seq.empty[B]
//        }
//        val res = reduceRight(list)(i=>i+" - ")
//        println(res)
//        println(reduceLeft(list)(i=>i+","))

//        val list = List(1,2,3,4,5)
//
//        val list2 = list.map(_.toString)
//
//        println(list2)
//        val fncLeft = (x:String,y:String) => s"($x)-($y)"
//        val fncRigt = (x:String,y:String) => s"($y -$x)"
//
//        val s1 = list2 reduceLeft fncLeft
//        println(s1)
//        println(list2 reduceRight(fncRigt))

//        val res = (List(1,2,3,4,5) foldRight List.empty[String]) {
//            (x,list) => ("["+x+"]")::list
//        }
//        println(res)

//        val list = List(1,2,3,4,5)
//        val res = (list fold 10)(_*_)
//        println("res: "+res)
//
//        println(list reduce(_*_))
//        println(List.empty[Int] reduce(_+_))
//        println((List.empty[Int] fold(10))(_*_))

//        println(List.empty[Int] reduceOption(_*_))

//        val list = List("now","is","the","","time")
//        println(list flatMap (e=>e.toList))


//        val state = Map("Alaba"->"MonogoDb","java"->"Bruce ecker","c++"->"Brtucher")
//        state foreach(kv => println(kv._1 +" -> "+kv._2))
//        state foreach{
//            case (k,v) => println(k+" : "+v)
//        }

//        val list1 = List("program","scala")
//        val list2 = List("hello","world")
//        println(list1 ++ list2)
//
//        val seq1 = "hello" +: list1
//        println(seq1)
//        val seq2 = list1 :+ "hello"
//        println(seq2)


//        def multi(d1:Double,d2:Double,d3:Double) = d1 * d2 * d3
//        val dd = (1.1,2.2,3.3)
//
//        println(multi(dd._1,dd._2,dd._3))
//        val multiTuple = Function.tupled(multi _)
//        println(multiTuple(dd))
//        val unMultiTuple = Function.untupled(multiTuple)
//        println(unMultiTuple(dd._1,dd._2,dd._3))


//        def multi(i:Int)(factor:Int) = i*factor
//        val byFive = multi(5) _
//        val byTwo = multi(2) _
//
//        println(byFive(2))
//        println(byTwo(2))

//        def cat3(s1:String,s2:String) = s1 + s2
//        def cat3c = (cat3 _).curried
//        println(cat3c("hello")("world"))
//
//        val cat3unc = Function.uncurried(cat3c)
//        println(cat3unc("hello","world"))

//        def cat1(s1:String)(s2:String) = s1+s2
//
//        val hello = cat1("hello")_
//
//        println(hello(" world"))
//        println(cat1("hello")("yangyang"))


//        def m1(multiply:Int=>Int) = {
//            (1 to 10) filter(isEvne) map multiply reduce(_*_)
//        }
//        def m2:Int=>Int = {
//            val factor = 2
//            val multiply = (i:Int) => i*factor
//            multiply
//        }
//        println(m1(m2))



//        def factor = 3
//        val multiplier = (i:Int) => i *factor
//        val res = (1 to 10 ) filter(_%2 == 0) map(multiplier) reduce(_*_)
//        println(res)

//        val res = (1 to 10) filter (_%2 ==0) map(_*2) reduce (_+_)
//        println(res)


//        val as = Seq(
//            Address("scala","Anytown","USA"),
//            Address("java","hello","China")
//        )
//        val ps = Seq(
//            Person("Bulk",22),
//            Person("Clojure",23)
//        )
//
//        val pas = ps zip as
//
//        val res = pas map {
//            case (Person(name,age),Address(street,city,country)) =>
//                s"$name (age: $age) lives at $street, $city,$country"
//        }
//        println(res)


//        val Person(name,age,Address(_,state,_)) = Person("Dean",23,Address("1 scala","CA","USA"))
//        println(name+" : "+age+" : "+state)


//        val methods = Seq(
//            Connect("connect body ..."),
//            Delete("delete body ..."),
//            Get1("get body ...")
//        )
//        methods foreach(method => println(handle(method)))


//        val res = for{x <- Seq(List(5,5,6,7,5),List("a","b"))} yield x match {
//            case seqd:Seq[Double] => ("seq double",seqd)
//            case seqs:Seq[String] => ("seq string",seqs)
//            case _ => println("no")
//        }
//
//        println(res)


//        val bookRe = """Book: title=([^,]+),\s+author=(.+)""".r
//        val magaziRe = """maga: title=([^,]+),\s+issue=(.+)""".r
//        val catalog = Seq(
//            "Book: title=Programing ,scala, author=Dean Wampler",
//            "Maga: title=The new yorker, issue=Janury 2014",
//            "Unkonwn: text=who put this here"
//        )
//        for(item <- catalog){
//            item match {
//                case bookRe(title,author) => println(s"""Book:title "$title",author= "$author" """)
//                case magaziRe(title,issue) => println(s"""Maga title: "$title" ,issue= "$issue" """)
//                case entry => println(s"unrecognized entry: $entry")
//            }
//        }
//        val s = "hello".replace("e","xx")
//        println(s)

//        val seq = List(1,2,3,4,5)
//        seq.sliding(2) foreach println
//        println(seq.sliding(3,1).toList)

//        val list = List(1,2,3,4,5)
//        val emptyList = List.empty[Int]
//        val map = Map("one"->1,"two"->2)
//
//        def window[T](seq:Seq[T]) :String = seq match {
//            case head1+:head2+:tail => s"($head1,$head2) ," + window(tail)
//            case head+:tail => s"($head,_), "+window(tail)
//            case Nil => "Nil"
//        }
//        for(seq <- Seq(list,emptyList,map.toSeq)) {
//            println(window(seq))
//        }

//        val list = List(1,2,3,4,5)
//        val vec = Vector(1,2,3,4,5)
//        val map = Map("one"->1,"two"->2,"three"->3)
//
//        def reverseSeq2String[T](list:Seq[T]):String = list match {
//            case prefix :+ end => reverseSeq2String(prefix) + s" :+ $end"
//            case Nil => "Nil"
//        }
//        for(seq<-Seq(list,vec,map.toSeq)) {
//            println(reverseSeq2String(seq))
//        }


//        val with1:With[String,Int] = With("foo",1)
//        val with2: String With Int = With("far",2)
//        Seq(with1,with2) foreach {
//            w => w match {
//                case  s With i => println(s"$s with $i")
//                case _ => println("unkonwn ... ")
//            }
//        }

//        val list = 1 +: 2 +: 3 +: Nil
//        val list2 = 1::2::3::Nil
//        println(list2)
//        println(list)

//        val itemcost = Seq(("Pencil",0.53),("Paper",1.35),("Notebook",2.43))
//        val itemcostIndex = itemcost.zipWithIndex
//
//        for(itemIndex <- itemcostIndex) itemIndex match {
//            case ((item,cost),index) => println(s"$index: $item costs $cost each")
//        }


//        val alice = Person("Alice",22,Address("1 scala Lane","Chicago","USA"))
//        val bob = Person("bob",23,Address("2 scala Lane","Chicago","USA"))
//        val yangyang = Person("yangyang",24,Address("3 scala Lane","Chicago","USA"))
//
//        for(person <- Seq(alice,bob,yangyang)){
//            person match {
//                case p @ Person("Alice",22,address) => println(s"Hi Alice! $p")
//                case p @ Person("bob",23, a @ Address(street,city,country)) =>
//                    println(s"Hi ${p.name}! age ${p.age} in ${a.city}")
//                case p @ Person(name,age,_) => println(s"unkown ${p.name} -- ${p.age}")
//            }
//        }

//        for(person <- Seq(alice,bob,yangyang)){
//            person match {
//                case Person("Alice",22,Address(_,"Chicago",_)) => println("hi alice")
//                case Person("bob",23,Address("2 scala Lane","Chicago","USA")) => println("hi bob")
//                case Person(name,age,_) => println(s"who are you? $age and $name")
//            }
//        }


        //guard clause

//        for(i <- Seq(1,2,3,4,5)){
//            i match {
//                case _ if i % 2 ==0 => println(s"even: $i")
//                case _ =>println(s"old: $i")
//            }
//        }



//        val seq = Seq(
//            ("scala","martin","Odersky"),
//            ("Clojure","Rich","Hickey"),
//            ("Lisp","john","McCarthy")
//        )
//
//        for(tuple <- seq) tuple match {
//            case ("scala",_,_) => println("found scala")
//            case (first,second,third) => println(s"found other: $first $second $third")
//        }

//        val list = List(1,2,3,4,5)
//        val emptyList = Nil
//        def list2String[T](list:List[T]):String = list match {
//            case head::tail => s"$head " + list2String(tail)
//            case Nil => "Nil"
//        }
//        for(l <- List(list,emptyList)) println(list2String(l))

//        val nonEmptySeq = Seq(1,2,3,4,5)
//        val emptySeq = Seq.empty[Int]
//        val nonEmptyList = List(1,2,3,4,5)
//        val emptyList = List.empty[Int]
//        val nonVector = Vector(1,2,3,4,5)
//        val emptyVector = Vector.empty[Int]
//        val nonMap = Map("one"->1,"two"->2,"three"->3)
//        val emptyMap = Map.empty[String,Int]
//
//        def seq2String[T](seq:Seq[T]):String = seq match {
//            case head +: tail => s"$head "+seq2String(seq.tail)
//            case Nil => "Nil"
//        }
//
//        for(seq <- Seq(nonEmptySeq,emptySeq,nonEmptyList,emptyList,nonVector,emptyVector,nonMap.toSeq,emptyMap.toSeq)){
//            println(seq2String(seq))
//        }


//        val bools = Seq(true,false)
//        for(bool <- bools){
//            bool match {
//                case true => println("Got head")
//                case false => println("Got tail")
//            }
//        }

//        val servic1 = new ServiceImportance("uno")
//        (1 to 3) foreach(i=>println(s"result: ${servic1.work(i)}"))

//        val service2 = new ServiceImportance("dos") with StdoutLogging{
//            override def work(i: Int): Int = {
//                info(s"Starting work: i= $i")
//                val res = super.work(i)
//                info(s"Ending work:i= $i,result is: $res")
//                res
//            }
//        }
//        (1 to 3) foreach(i=>println(s"result: ${service2.work(i)}"))

//        val gross = 100000f
//        val net = 6400f
//        val percent = (net/gross)*100
//        println(f"$$${gross}%.2f vs. $$${net}%.2f or${percent}%.1f%%")


//        import WeekDay._
//        def isWorkingDay(d:WeekDay) = ! (d == SAT) || (d == SUN)
//        WeekDay.values filter isWorkingDay foreach println



//        import Breed._
//        println("ID\tBreed")
//        for(breed <- Breed.values) println(s"${breed.id}\t${breed}")
//        println("just terrier")
//        Breed.values filter(_.toString.endsWith("terrier")) foreach println

//        println("===============================")
//        def isTerrier(b:Breed) = b.toString.endsWith("terrier")
//        Breed.values filter isTerrier foreach println




//        println()
//        val filename = "F:\\Scala\\ScalaDemo1\\resource\\data.txt"
//        manage(Source.fromFile(filename)){
//            source =>
//                val size = source.getLines.size
//                println(s"file $filename has $size lines")
//                if(size > 20) throw new RuntimeException("Big file")
//                for(line <- source.getLines())
//                    println(line)
//
//        }

//        val list = List("hello world","yangyang","hello xiaomo","dongtain")
//
//        for(name<-list if name contains("hello") if !(name contains("mo"))) println(name)
//
//        val names = for{
//            name<-list
//            if (name contains("hello")) && (!(name contains("mo")))
//        } yield name
//
//        println(names)
//
//        for{
//            name <- list
//            upper = name.toUpperCase()
//        }println(upper)

//        val dogs = Lis

//        var count = 0
//        do {
//            count += 1
//            println(count)
//        }while(count < 10)


//        val filePath = "F:\\Scala\\ScalaDemo1\\resource\\data.txt"
//        countLines(filePath)

        //call by value
//        def test1(code:()=>Unit): Unit = {
//            println("start")
//            code()
//            println("end")
//        }
//        test1{
//            println("111")
//            ()=>println("222")
//        }
//
//        println("========================")
//        def test2(code: => Unit)={
//            println("start")
//            code
//            println("end")
//        }
//        test2{
//            println("111")
//            println("222")
//        }



    }

}
