import org.scalatest._

import battleShip._


class ShipSpec extends FunSuite {

  test("Test : issuperposed (check if ship is superposed on other ship") {
    val c1 = Coordinate(1,3)
    val c2 = Coordinate(1,4)
    val c3 = Coordinate(1,5)
    val c4 = Coordinate(1,6)
    val s1 = Ship("test1",2,List(c1,c2))
    val s2 = Ship("test2",2,List(c2,c3))
    val s3 = Ship("test3",2,List(c3,c4))

    assert(s1.isSuperposed(s2))
    assert(!s1.isSuperposed(s3))
  }

  test("Test : isSink (check if ship is sink)") {
    val c1 = Coordinate(1,3)
    val c2 = Coordinate(1,4)
    val s1 = Ship("test1",2,List(c1,c2))
    val s2 = Ship("test2",3,Nil)

    assert(!s1.isSink)
    assert(s2.isSink)
  }

  test("Test : removeCoordinate (check if the coordinate is delete after use the function)") {
    val c1 = Coordinate(1,3)
    val c2 = Coordinate(1,5)
    val c3 = Coordinate(1,6)    
    val s1 = Ship("test3",2,List(c2,c3))
    val s2 = Ship("test5",3,List(c1))
    val s3 = s2.removeCoordinate(c1)
    val s4 = s1.removeCoordinate(c3)
   
    assert(s3.isSink)
    assert(!s4.isContained(c3))

  }

  test("Test : isContained (check if the coordinate is contained in the ship)") {
    val c1 = Coordinate(1,3)
    val c2 = Coordinate(1,4)
    val c3 = Coordinate(1,5)
    val c4 = Coordinate(1,6)
    val s1 = Ship("test1",2,List(c1,c2))
    val s2 = Ship("test2",2,List(c2,c3))

    assert(s1.isContained(c1))
    assert(!s2.isContained(c4))
  }
}