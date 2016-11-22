package Shangxuetang



class SpecialPerson(val name:String)
class Student(val name:String)
class Older(val name:String)

class Teacher(val name:String)


object ImplicitDemo {

    implicit def obj2SpecialPerson(obj: Object):SpecialPerson ={
        if(obj.getClass == classOf[Student]){
            val stu = obj.asInstanceOf[Student]
            new SpecialPerson(stu.name)
        }else if(obj.getClass == classOf[Older]){
            val old = obj.asInstanceOf[Older]
            new SpecialPerson(old.name)
        }else{
            Nil
        }
    }
    var ticketsNumber = 0
    def buySpecialTicket(p:SpecialPerson) ={
        ticketsNumber += 1
        "T-"+ticketsNumber
    }
    def main(args: Array[String]): Unit = {

        println("start ... ")

        val yangyang = new Student("yangyang")
        println(buySpecialTicket(yangyang))

        val old = new Older("hello")
        println(buySpecialTicket(old))

        val teacher = new Teacher("world")
        buySpecialTicket(teacher)
    }
}
