def f (a: Int) (b: Int) = a + b
def times (a: Int, b: Int)(c: Int) = a * b * c

@main
def main(): Unit = {
  val x = 4
  val a = f 3
    (x)
  println(s"a = $a")
  val chain = 3 .equals 4 .toString .slice.curried 1 10 + ": end"
  println(chain)
  val b = (2, 3)
  println(s"f 3 x = ${1 * 5 + f 3 x + 10 * 2}")
  println(s"times b = ${times.tupled b 2}")

  val list = 1 .to 10
  println s"list = $list"
}

