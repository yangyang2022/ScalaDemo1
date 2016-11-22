package DemoScalaApi

import scala.io.Source
import scala.util.Try

object StringUtils{
    implicit class StringIncrement(s:String){
        def increment = s.map(c=>(c+1).toChar)
        def decrement = s.map(c=>(c-1).toChar)
        def hidAll = s.replaceAll(".","*")
        def plusOne = s.toInt + 1
        def asBoolean = s match  {
            case "0" | "" | "zero" | " " => false
            case _ => true
        }
        @throws(classOf[NumberFormatException])
        def toInt1(radix:Int) = Integer.parseInt(s,radix)
    }
    def ~=(a:Double,b:Double,precision:Double): Boolean ={
//        if((a-b).abs < precision) true else false
        (a-b).abs < precision
    }

    val fileInputStream = Demo2.getClass.getResourceAsStream("/data.txt");

    val fileResource = Source.fromInputStream(fileInputStream)
    fileResource.getLines().foreach(println)
}

class Person1(var firstName:String,var lastName:String){
    println("constructor start ...")
    private val HOME = System.getProperty("user.home")
    val age = 22

    override def toString: String = s"$firstName $lastName is $age years old"

    def printHome = println(HOME)
    def printFullname = println(this)

    printHome
    printFullname
    println("still in home ... ")
}
object Pizza{
    val DEFAULT_CRUST_SIZE = 12
    val DEFAULT_CRUST_TYPE = "THIN"
}
class Pizza (var crustSize:Int,var crustType:String){
    def this(crustSize:Int){
        this(crustSize,Pizza.DEFAULT_CRUST_TYPE)
    }
    def this(crustType:String){
        this(Pizza.DEFAULT_CRUST_SIZE,crustType)
    }
    def this(){
        this(Pizza.DEFAULT_CRUST_SIZE,Pizza.DEFAULT_CRUST_TYPE)
    }

    override def toString: String = s"$crustSize : $crustType"
}

object Brian{
    val brain = new Brian
    def getInstance = brain
}
class Brian private {
    override def toString: String = "this is the brrain ... "
}

class Stock{
    private var price:Double = _
    def setPrice(p:Double) = price = p
    def isHighter(that:Stock) = this.price > that.price
}

//case class Address(city:String,state:String)
//class Person(var name:String,var address: Address){
//    override def toString: String = if(address == null) name else s"$name @ $address"
//}
//class Employee(name:String,address:Address,var age:Int) extends Person(name, address)

//abstract class Pet(name:String){
//    val greeting:String
//    val age:Int
//    def sayHello = println(greeting)
//
//    override def toString: String = s"i say $greeting and my age is: $age"
//}
//class Cat(name: String) extends Pet(name){
//    override val greeting: String = "Meow"
//    override val age: Int = 11
//}
//class Dog(name:String) extends Pet(name){
//    override val greeting: String = "Woof"
//    override val age: Int = 13
//}

//class Person(name:String,age:Int){
//    def canEqual(a:Any) = a.isInstanceOf[Person]
//
//    override def equals(that: scala.Any): Boolean = that match {
//        case that:Person => that.canEqual(this) && this.hashCode() == that.hashCode()
//        case _ => false
//    }
//
//    override def hashCode(): Int = name.hashCode+age.hashCode()
//}

trait Human{
    def hello = "hello human"
}
trait Mother extends Human{
    override def hello: String = "hello mother"
}
trait Father extends Human{
    override def hello: String = "hello father"
}

class Children extends Human with Mother with Father{
    def printSuper = super.hello
    def printHuman = super[Human].hello
    def printMother = super[Mother].hello
    def printFather = super[Father].hello

}
//class Person{
//    protected var fname = ""
//    protected var lname = ""
//
//    def setFirstName(firstName:String):this.type ={
//        fname = firstName
//        this
//    }
//    def setLastName(lastName:String):this.type  = {
//        lname = lastName
//        this
//    }
//}
//class Employee extends Person{
//    protected var role = ""
//
//    def setRole(role:String) : this.type  = {
//        this.role = role
//        this
//    }
//
//    override def toString: String = s"$fname $lname is $role"
//}

class Starship
class RomulanShip

trait starfleetWarpCore{
    this:Starship =>
    //code
}
class Enterprise extends Starship with starfleetWarpCore

class Foo{
    def exec(f:String => Unit,name:String) = f(name)
}

object Margin extends Enumeration{
    type Margin = Value
    val TOP,BUTTON,LEFT,RIGHT = Value
}
class Person(var name:String) extends Ordered[Person]{

    override def compare(that: Person): Int = this.name.compare(that.name)

    override def toString: String = name
}

object Demo2 {

//    implicit class StringUtil(s:String){
//        implicit def increment = s.map(c=>(c+1).toChar)
//    }

//    def printAll(strings: String*): Unit ={
//        strings.foreach(println)
//    }
//    def executeFuction(callback:()=>Unit): Unit ={
//        callback()
//    }
//
//    def exec(callback:Int=>Unit): Unit = {
//        callback(1)
//    }
//
//    def execTimes(callback:(Int,Int)=>Int,i:Int,j:Int) ={
//        println("res is: "+callback(i,j))
//    }

    def wrap(prefix:String,html:String,postfix:String) = prefix+html+postfix
    def wrapWithDiv = wrap("<div>",_:String,"</div>")


    def saySomething(prefix:String) = (s:String)=>prefix+" : "+s

    def greeting(language:String) = (name:String)=> language match {
        case "english" => "english: hello "+name
        case "spanlish" => "spanlish: buenos disa "+name
    }

    def divide:PartialFunction[Int,Int] = {
        case d:Int if d!= 0 => 42 / d
    }
    val isEven:PartialFunction[Int,String] = {
        case x if x % 2 == 0 => x +" is even"
    }
    val isOld:PartialFunction[Int,String] ={
        case x if x % 2 != 0 => x +" is old"
    }

    def main(args: Array[String]): Unit = {
        import scala.collection.mutable.ArrayBuffer
        import StringUtils._
        import Margin._




//        val map = Map(1->"a",2->"b",3->"c")
//        val x1 = map.transform((k,v)=>v.toUpperCase)
//        println(x1)
//
//        println(map.filterKeys(Set(2,3)))


//        val map = Map("hello"->"world").withDefaultValue("no such value")
//
//        println(map("hello1"))

//        val x = List.tabulate(5)(e=>e*e)
//        println(x)

//        val ty = new Person("Tyler")
//        val al = new Person("Al")
//
//        if(ty < al) println(ty) else println(al)

//        val list = List("hello","world","nihao","yangyang","he","abc","acb")
//        println(list.sorted)
//        println(list.sortWith(_.length < _.length))

//        val x = ("AL"->"Alabama")
//
//        val it = x.productIterator
//
//        for(e<-it) println(e)
//        //no print
//        for(e<- it) println(e)

//        val currentMargin = TOP
//        if(currentMargin == TOP) println("top")
//
//        Margin.values foreach println

//        val list = List(3,1,2,4)
//        println(list.reverse)
//        println(list.sorted)


//        val women = List("hello","world")
//        val man = List("yangyang","demo")
//
//        val couples = women zip man
//
//        println(couples.toMap )



//        val findMax = (x:Int,y:Int) =>{
//            val winner = x max y
//            println(s"compared $x to $y ,$winner was larger ")
//            winner
//        }
//
//        val a = Array(12,6,15,2,20,9)
//        println(a.reduceLeft(_+_))
//        println(a.reduceLeft(_ min _))
//        a.reduceLeft(findMax)

//        val coupls = List((1,2),("a","b"))
//        val x = coupls.unzip
//        println(x)

//        val list = List(15,10,5,8,20,12)
//        println(list.groupBy(_>10))
//        println(list.partition(_>10))
//        println(list.span(_<20))
//        println(list.splitAt(2))

//        val list = List("apple","banana",1,2)
//
//        def onlyString(s:Any) = s match {
//            case a:String => true
//            case _ =>false
//        }
//        println(list.filter(onlyString))


//        def toInt(in:String):Option[Int] = {
//            try{
//                Some(Integer.parseInt(in))
//            }catch{
//                case e:Exception => None
//            }
//        }
//        val bag = List("1","2","one","4","one handred seventy five")
//        println(bag.flatMap(toInt).sum)

//        val res = List(List(1,2,3),List(2,4,6,List(9,9,9)))
//        println(res.flatten.distinct)

//        val fruits = Array("apple","banana","orange")
//        for((elem,count) <- fruits.zip(Stream from(10))) println(s"element $count is $elem")

//        val nums = ArrayBuffer(1,2,3)
//        nums.append(1,2,4)
//        println(nums)
//        val a = ArrayBuffer.range('a','h')
//        println(a)
//        a.trimStart(3)
//        println(a)

//        println(nums += 4)
//        println(nums += (5,6))
//        println(nums -= 9)
//
//        println(nums --= Array(5,6))

//        val sample = 1 to 10
//        val evenNumbers = sample collect(isEven orElse isOld)
//        println(evenNumbers)

//        val res = List(0,1,2) collect (divide)
//        println(res)
//
//        println(List(42,"cat") collect{case i:Int => i+1 })

//        println(divide(33))
//        println(greeting("spanlish")("yangyang"))

//        val sayHello = saySomething("hello")
//        println(sayHello("world"))
//
//        println(saySomething("hello")("world"))
//
//        println(wrapWithDiv("val i =1"))
//        println(wrapWithDiv("hello"))

//        import scala.collection.mutable.ArrayBuffer
//
//        val fruits = ArrayBuffer("apple")
//
//        val addToBasket = (s:String)  =>{
//            fruits += s
//            println(fruits.mkString(","))
//
        //  }
//
//        def buyStuff(f:String=>Unit,s:String) = f(s)
//
//        buyStuff(addToBasket,"hello")
//        buyStuff(addToBasket,"world")


//        var votingAge = 21
//        val isOfVotingAge = (age:Int) => age > votingAge
//
//        def printResult(f:Int=>Boolean,x:Int) = f(x)
//
//        println(printResult(isOfVotingAge,20))

//        var hello = "hello"
//        def sayHello(name:String) = println(s"$hello $name")
//
//        val foo = new Foo
//        foo.exec(sayHello,"world")


//        execTimes((x:Int,y:Int)=>x*y,11,2)

//        val plusone = (i:Int) => println(i+1)
//        exec(plusone)

//        val hello = ()=>println("hello world")
//        executeFuction(hello)
//        executeFuction(()=>println("ni hao yangyang"))

//        val c = math.cos _
//        println(c(0))

//        val employee = new Employee
//        employee.setFirstName("shen").setLastName("yangyang").setRole("admin")
//        println(employee)

//        val fruits = List("apple","banana","cherry")
//        printAll(fruits:_*)

//        val  (hello,world,demo ) = getInfo
//        println(hello+" : "+world+" : "+demo )

//        val children = new Children
//
//        println(children.printSuper)
//
//        println(children.printHuman)
//        println(children.printMother)
//        println(children.printFather)


//        val p1 = new Person("hello",12)
//        val p2 = new Person("hello",12)
//
//        println(p1.hashCode() +" : "+ p2.hashCode())
//        println(p1 == p2)
//        println(p1.equals(p2))


//        val dog = new Dog("Fido")
//        val cat = new Cat("Morris")
//
//        dog.sayHello
//        cat.sayHello
//        println(dog)
//        println(cat)

//        val employee = new Employee("yangyang",Address("anhui","wuhu"),123)
//        println(employee)


//        val s1 = new Stock
//        s1.setPrice(200)
//        val s2 = new Stock
//        s2.setPrice(100)
//        println(s1.isHighter(s2))

//        val p1 = Brian.getInstance
//        val p2 = Brian.getInstance
//        println(p1 == p2 )

//        val p = new Pizza(22,"hello")
//        println(p)
//        val list:List[String] = List("1","2","3","hello","world")
//        def listToString(s:List[String]):String = s match {
//            case head::rest => head +" "+listToString(rest)
//            case Nil => "";
//        }
//        println(listToString(list))

//        val i = 10
//        i match {
//            case a if 1 to 10 contains a => println("0 - 10")
//            case a if 1 to 10 contains a => println("10 -20")
//            case _ => println("none")
//        }

//        def toInt(s:String):Option[Int] ={
//            try{
//                Some(Integer.parseInt(s))
//            }catch {
//                case e:Exception => None
//            }
//        }
//        toInt("a42") match {
//            case Some(e) => println(e)
//            case None => println("error")
//        }


//        def matchType(x:Any) = x match {
//            case x @ List(1,_*) => s"$x"
//            case Some(e) => s"$e"
//            case p @ Person(firstName,"yangyang") => s"$p firstname is $firstName"
//        }
//        println(matchType(List(1,2,3,4)))
//        println(matchType(Some("foo")))
//        println(matchType(Person("hello","yangyang")))

//        val s = List(1,2,4)
//
//        val x = (s:Any) match  {
////            case list : List[_] => s"list1 is $list"
//            case list @ List(1,_*) => s"list2 is $list"
//            case _ => "not any ..."
//        }
//        println(x)


//        val i:Any = 1
//        val x = (i : @switch) match {
//            case 1 => "one"
//            case "hello" => "two"
//            case _ => "no"
//        }
//        println("x is: "+x)

//        val s = "peter pipr picked a peck of pickled pappers"
//        println(s.count(_ == 'p'))


//        val names = List("hello","world")
//        val capName = for(name <- names) yield name.capitalize
//        println(capName)

//        val array = Array.ofDim[Int](2,2)
//        println(array)
//        for(i <- 0 to 1 ;j <- 0 to 1) println(s"${array(i)(j)}")

//        for(i<- 1 to 2;j <- 1 to 2) println(s"$i - $j")

//        val pi = scala.math.Pi
        //        println(f"pi is $pi%01.5f")

//        val a = 0.3
//        val b = 0.1+0.3
//        println(a)
//        println(b)
//        println( ~=(a,b,0.001) )
//        println(a == b)

//        val s = null.asInstanceOf[String]
//
//        println(s)

//        println("a100".toInt1(8))
//        println(Integer.parseInt("100",8))
//        println(Short.MinValue)
//        println(LocalTime.now())
//        println(LocalDateTime.now())


//        val pattern = "([0-9]+)([a-zA-Z]+)".r
//        val pattern(count,fruit) = "100 hello"
//        println(count +" : "+fruit)

//        import StringUtils._
//        println("0".asBoolean)

//        import StringUtils._
//        println("hello".increment)

//        println("hello"(0))
//        println("hello".increment)


    }
}
