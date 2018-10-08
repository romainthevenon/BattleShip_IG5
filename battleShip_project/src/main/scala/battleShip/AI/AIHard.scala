package battleShip

import battleShip._
import scala.util.Random

object AIHard {

/*
For this AI :
- place Ship --> Random
- Shot --> check if the list of good shots is not empty, if not empty, take the first shot in the list (it is the last shot good) and play a coordinate around this shot, otherwise play a random 
*/

	/**
      * This function returns the coordinate to place a ship (random)
      * @return Coordinate 
      */
	def chooseCoordinateToPlaceShip() : Coordinate = {
		val randX = (new Random).nextInt(10)
		val x = randX + 1
		val randY = (new Random).nextInt(10)
		val y = randY + 1
		Coordinate(x,y)
	}

	/**
      * This function returns the direction (random)
      * @return String : The direction 
      */
	def chooseDirection() : String = {
		val rand = (new Random).nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

	/**
      * This function returns the coordinate to shoot (check if the list of good shots is not empty, if not empty, take the first shot in the list (it is the last shot good) and play a coordinate around this shot, otherwise play a random --> random valid if (x and y are even) or (x and y are uneven))
      * @return Coordinate 
      */
	def chooseCoordinateToShoot(p: Player) : Coordinate = {
		if (p.listShotGood.isEmpty) {
			val randX = (new Random).nextInt(10)
			val x = randX + 1
			val randY = (new Random).nextInt(10)
			val y = randY + 1
			val c=  Coordinate(x,y)
			if(p.isShotGood(c) | p.isShotBad(c)) {
				chooseCoordinateToShoot(p)
			} else if((x % 2 == 0) && (y % 2 ==0)) {
				return c
			} else if((x % 2 != 0) && (y % 2 !=0)) {
				return c
			} else {
				chooseCoordinateToShoot(p)
			}
		} else {
			val oldShot = p.listShotGood.head
			if (oldShot.copy(_y = oldShot.y-1).isValid && !p.isShotGood(oldShot.copy(_y = oldShot.y-1)) && !p.isShotBad(oldShot.copy(_y = oldShot.y-1))) {
				return oldShot.copy(_y = oldShot.y-1)
			} else if (oldShot.copy(_x = oldShot.x+1).isValid && !p.isShotGood(oldShot.copy(_x = oldShot.x+1)) && !p.isShotBad(oldShot.copy(_x = oldShot.x+1)))  {
				return oldShot.copy(_x = oldShot.x+1)
			} else if (oldShot.copy(_y = oldShot.y+1).isValid && !p.isShotGood(oldShot.copy(_y = oldShot.y+1)) && !p.isShotBad(oldShot.copy(_y = oldShot.y+1)))  {
				return oldShot.copy(_y = oldShot.y+1)
			} else if (oldShot.copy(_x = oldShot.x-1).isValid && !p.isShotGood(oldShot.copy(_x = oldShot.x-1)) && !p.isShotBad(oldShot.copy(_x = oldShot.x-1)))  {
				return oldShot.copy(_x = oldShot.x-1)
			} else {
				val randX = (new Random).nextInt(10)
				val x = randX + 1
				val randY = (new Random).nextInt(10)
				val y = randY + 1
				val c = Coordinate(x,y)
				if(p.isShotGood(c) | p.isShotBad(c)) {
					chooseCoordinateToShoot(p)
				} else {
					return c
				}
			}
		}
	}

}