package Shangxuetang

/**
  * Created by syy on 2016/11/13.
  */

class SignPen{
    def write(context:String) = println(context)
}
object ImplicitContext{
    implicit val signPen = new SignPen
}
object ImplicitArgs {
    def signForExam(name:String)(implicit signPen: SignPen) = {
        signPen.write(name+" arrive in time!!!!")
    }
    def main(args: Array[String]): Unit = {
        import ImplicitContext.signPen
        signForExam("yangyang")
        signForExam("hello")
    }
}
