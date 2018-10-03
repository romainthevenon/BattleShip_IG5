import org.scalatest._

import battleShip._


class ShipSpec extends FunSuite with DiagrammedAssertions {
  val c1 = Coordinate(1,3)
  val c2 = Coordinate(1,4)
  val c3 = Coordinate(1,5)
  val c4 = Coordinate(1,6)

  val s1 = Ship("test1",2,List(c1,c2))
  val s2 = Ship("test2",2,List(c2,c3))
  val s3 = Ship("test3",2,List(c3,c4))
  val s4 = Ship("test4",3,Nil)
  val s5 = Ship("test5",3,List(c1))
  val s6 = s5.removeCoordinate(c1).get

  test("Test issuperposed") {
    assert(s1.isSuperposed(s2))
    assert(!s1.isSuperposed(s3))
  }

  test("Test isSink") {
    assert(!s1.isSink)
    assert(s4.isSink)
  }

  test("Test removeCoordinate") {
    assert(s6.isSink)
  }
}