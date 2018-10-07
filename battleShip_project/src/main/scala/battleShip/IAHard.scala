package battleShip

import battleShip._
import scala.util.Random

object IAHard {

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
		if (p.listShotGood.isEmpty) {
			val randX = (new Random).nextInt(10)
			val x = randX + 1
			val randY = (new Random).nextInt(10)
			val y = randY + 1
			val c=  Coordinate(x,y)
			if(p.isShotGood(c) | p.isShotBad(c)) {
				chooseCoordinateToShot(p)
			} else {
				return c
			}
		} else {
			val oldShot = p.listShotGood.head
			if (!p.isShotGood(oldShot.copy(_y = oldShot.y-1)) && !p.isShotBad(oldShot.copy(_y = oldShot.y-1))) {
				return oldShot.copy(_y = oldShot.y-1)
			} else if (!p.isShotGood(oldShot.copy(_x = oldShot.x+1)) && !p.isShotBad(oldShot.copy(_x = oldShot.x+1)))  {
				return oldShot.copy(_x = oldShot.x+1)
			} else if (!p.isShotGood(oldShot.copy(_y = oldShot.y+1)) && !p.isShotBad(oldShot.copy(_y = oldShot.y+1)))  {
				return oldShot.copy(_y = oldShot.y+1)
			} else if (!p.isShotGood(oldShot.copy(_x = oldShot.x-1)) && !p.isShotBad(oldShot.copy(_x = oldShot.x-1)))  {
				return oldShot.copy(_x = oldShot.x-1)
			} else {
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
	}

}