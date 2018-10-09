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
      * @param random
      * @return Coordinate 
      */
	def chooseCoordinateToPlaceShip(random : Random) : Coordinate = {
		val randX = random.nextInt(10)
		val x = randX + 1
		val randY = random.nextInt(10)
		val y = randY + 1
		Coordinate(x,y)
	}

	/**
      * This function returns the direction (random)
      * @param random
      * @return String : The direction 
      */
	def chooseDirection(random : Random) : String = {
		val rand = (new Random).nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

	/**
      * This function returns the coordinate to shoot (random and check if the coordinate has already been played)
      * @param p The player
      * @param random
      * @return Coordinate 
      */
	def chooseCoordinateToShoot(p: Player, random : Random) : Coordinate = {
		val randX = random.nextInt(10)
		val x = randX + 1
		val randY = random.nextInt(10)
		val y = randY + 1
		val c = Coordinate(x,y)
		if(p.isShotGood(c) | p.isShotBad(c)) {
			chooseCoordinateToShoot(p, random)
		} else {
			return c
		}
	}
} 