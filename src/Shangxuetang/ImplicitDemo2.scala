package Shangxuetang

/**
  * Created by syy on 2016/11/13.
  */

class Man(val name:String)
class SuperMan(val name:String) {
    def emitLaser() = println("emit laser ...")
}
object ImplicitDemo2 {

    implicit def man2Superman(man:Man):SuperMan = new SuperMan(man.name)

    def main(args: Array[String]): Unit = {
        val man = new Man("yangyang")
        man.emitLaser()
    }
}
