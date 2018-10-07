package battleShip

import battleShip._
import scala.util.Random

object AIMedium {

/*
For this AI :
- place Ship --> Random
- Shot --> Random (but can't shoot where it has already shoot)
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
      * This function returns the coordinate to shot (random and check if the coordinate has already been played)
      * @return Coordinate 
      */
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