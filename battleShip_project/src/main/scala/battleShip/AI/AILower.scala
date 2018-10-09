package battleShip

import battleShip._
import scala.util.Random

object AILower {

/*
For this AI :
- place Ship --> Random
- Shot --> Random 
*/

	/**
      * This function returns the coordinate (random)
      * @param random
      * @return Coordinate 
      */
	def chooseCoordinate(random : Random) : Coordinate = {
		val randX = random.nextInt(10)
		val x = randX + 1
		val randY = random.nextInt(10)
		val y = randY + 1
		Coordinate(x,y)
	}

	/**
      * This function returns the direction (random)
      * @param random
      * @return String : the direction
      */
	def chooseDirection(random : Random) : String = {
		val rand = random.nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

}