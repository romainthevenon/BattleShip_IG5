import org.scalatest._

import battleShip._

class PlayerSpec extends FunSuite {

	test("Test : createShip") {
		val p1 = Player("test",Nil,Nil,Nil,0)
		val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)
		val s1 = p1.createShip("destroyer",2,c1,"H")
    	
    	assert(s1.get.isContained(c1))
    	assert(s1.get.isContained(c2))
  	}

  	test("Test : isDead (check if the player is dead)") {
		val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)
		val s1 = Ship("destroyer",2,List(Coordinate(1,1),Coordinate(1,2)))
		val p1 = Player("test",Nil,Nil,Nil,0)
		val p2 = Player("test",List(s1),List(c1),List(c2),1)
  		
  		assert(p1.isDead)
  		assert(!p2.isDead)
  	}

  	test("Test : isShootGood (check if the coordinate is contained in the ListShotGood)") {
  		val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)	
  		val p2 = Player("test",Nil,List(c1),List(c2),1)
  		
  		assert(p2.isShotGood(c1))
  		assert(!p2.isShotGood(c2))
  	}

  	test("Test : isShootBad (check if the coordinate is contained in the ListShotBad)") {
  	  	val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)	
  		val p2 = Player("test",Nil,List(c1),List(c2),1)

  		assert(!p2.isShotBad(c1))
  		assert(p2.isShotBad(c2))
  	}

  	test("Test : reset (check if the ListShot and ListShip is empty after reset player)") {
  		val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)
  		val s1 = Ship("destroyer",2,List(Coordinate(1,1),Coordinate(1,2)))
  		val p1 = Player("test",List(s1),List(c1),List(c2),1)
		val p2 = p1.reset

  		assert(p2.isDead)
  		assert(p2.listShip.isEmpty)
  		assert(p2.listShotGood.isEmpty)
  		assert(p2.listShotBad.isEmpty)
  		assert(!p2.isShotGood(c1))
  		assert(!p2.isShotBad(c2))
  		assert(p2.score == p1.score)
  	}

  	test("Test : removeShip (check if the ship is remove)") {
  		val c1 = Coordinate(1,1)
		val c2 = Coordinate(2,1)	
  		val s1 = Ship("destroyer",2,List(Coordinate(1,1),Coordinate(1,2)))
		val p1 = Player("test",List(s1),List(c1),List(c2),1)	
  		val p2 = p1.removeShip(s1)
  		assert(p2.isDead)
  	}

  	test("Test : addShot (check if the shot is added in the list)") {
  		val p1 = Player("test",Nil,Nil,Nil,0)
  		val c1 = Coordinate(1,1)
  		val c2 = Coordinate(1,2)
  		val p2 = p1.addShot(c1,true)
  		val p3 = p1.addShot(c2,false)

  		assert(!p2.listShotGood.isEmpty)
  		assert(p2.listShotBad.isEmpty)
  		assert(p3.listShotGood.isEmpty)
  		assert(!p3.listShotBad.isEmpty)
  	}

  	test("Test : addShip (check if the ship is added in the list)") {
  		val c1 = Coordinate(1,1)
  		val c2 = Coordinate(1,2)
  		val s1 = Ship("destroyer",2,List(c1,c2))
  		val p1 = Player("test",Nil,Nil,Nil,0)
  		val p2 = p1.addShip(s1)

  		assert(!p2.listShip.isEmpty)
  		assert(!p2.isDead)
  	}

  	test("Test : isContainedInOneShip (check if the coordinate is contained in one ship of the player)") {
  		val c1 = Coordinate(1,1)
  		val c2 = Coordinate(1,2)
  		val c3 = Coordinate(4,2)
  		val s1 = Ship("destroyer",2,List(c1,c2))
  		val p1 = Player("test",List(s1),Nil,Nil,0)

  		assert(p1.isContainedInOneShip(c1) != None)
  		assert(p1.isContainedInOneShip(c3) == None)
  	}

  	test("Test : addScore (check if the score is increased)") {
  		val p1 = Player("test",Nil,Nil,Nil,0)
  		val p2 = p1.addScore

  		assert(p2.score == 1)
  	}

}