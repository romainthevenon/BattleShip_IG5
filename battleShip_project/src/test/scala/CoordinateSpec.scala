import org.scalatest._

import battleShip._


class CoordinateSpec extends FunSuite with DiagrammedAssertions {
  val c1 = Coordinate("A",3)
  val c2 = Coordinate("E",11)
  val c3 = Coordinate("K",4)
  val c4 = Coordinate("Z",12)
  val c5 = Coordinate("J",10)

  test("Test isValid") {
    assert(c1.isValid)
    assert(!c2.isValid)
    assert(!c3.isValid)
    assert(!c4.isValid)
    assert(c5.isValid)
  }
}