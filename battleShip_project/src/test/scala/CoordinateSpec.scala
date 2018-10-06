import org.scalatest._

import battleShip._


class CoordinateSpec extends FunSuite {

  test("Test isValid") {
    val c1 = Coordinate(1,3)
    val c2 = Coordinate(5,11)
    val c3 = Coordinate(11,4)
    val c4 = Coordinate(-3,12)
    val c5 = Coordinate(10,10)
    
    assert(c1.isValid)
    assert(!c2.isValid)
    assert(!c3.isValid)
    assert(!c4.isValid)
    assert(c5.isValid)
  }
}