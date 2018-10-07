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
      * @return Coordinate 
      */
	def chooseCoordinate() : Coordinate = {
		val randX = (new Random).nextInt(10)
		val x = randX + 1
		val randY = (new Random).nextInt(10)
		val y = randY + 1
		Coordinate(x,y)
	}

	/**
      * This function returns the direction (random)
      * @return String : the direction
      */
	def chooseDirection() : String = {
		val rand = (new Random).nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

}