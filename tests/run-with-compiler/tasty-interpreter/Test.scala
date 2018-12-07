import scala.tasty.Reflection
import scala.tasty.file._

object Test {
  def main(args: Array[String]): Unit = {
    ConsumeTasty("", List("IntepretedMain"), new TastyInterpreter)
  }
}

trait Ref {
  def get: Any
}
class Eager(val get: Any) extends Ref
class Lazy(thunk: => Any) extends Ref {
  lazy val get: Any = thunk
}
class Var(private var value: Any) extends Ref {
  def get = value

  def update(rhs: Any): Unit = {
    value = rhs
  }
}

class TastyInterpreter extends TastyConsumer {

  final def apply(reflect: Reflection)(root: reflect.Tree): Unit = {
    import reflect._
    object Traverser extends TreeTraverser {

      override def traverseTree(tree: Tree)(implicit ctx: Context): Unit = tree match {
        // TODO: check the correct sig and object enclosement for main
        case DefDef("main", _, _, _, Some(rhs)) =>
          val interpreter = new Interpreter(reflect)

          interpreter.eval(rhs)(Map.empty)
        // TODO: recurse only for PackageDef, ClassDef
        case tree =>
          super.traverseTree(tree)
      }
    }
    Traverser.traverseTree(root)(reflect.rootContext)
  }
}
