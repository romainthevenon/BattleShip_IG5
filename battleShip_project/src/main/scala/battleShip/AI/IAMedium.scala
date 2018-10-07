package battleShip

import battleShip._
import scala.util.Random

object IAMedium {

	def chooseCoordinateToPlaceShip() : Coordinate = {
		val randX = (new Random).nextInt(10)
		val x = randX + 1
		val randY = (new Random).nextInt(10)
		val y = randY + 1
		Coordinate(x,y)
	}

	def chooseDirection() : String = {
		val rand = (new Random).nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

	def chooseCoordinateToShot(p: Player) : Coordinate = {
		val randX = (new Random).nextInt(10)
		val x = randX + 1
		val randY = (new Random).nextInt(10)
		val y = randY + 1
		val c = Coordinate(x,y)
		if(p.isShotGood(c) | p.isShotBad(c)) {
			chooseCoordinateToShot(p)
		} else {
			return c
		}
	}
} 