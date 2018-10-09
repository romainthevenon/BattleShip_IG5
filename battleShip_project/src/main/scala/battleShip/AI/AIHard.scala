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
		val rand = random.nextInt(1)
		rand match {
			case 0 => "H"
			case 1 => "V"
		}
	}

	/**
      * This function returns the coordinate to shoot (check if the list of good shots is not empty, if not empty, take the first shot in the list (it is the last shot good) and play a coordinate around this shot, otherwise play a random --> random valid if (x and y are even) or (x and y are uneven))
      * @param p The player
      * @param random
      * @return Coordinate 
      */
	def chooseCoordinateToShoot(p: Player, random : Random) : Coordinate = {
		if (p.listShotGood.isEmpty) {
			val randX = random.nextInt(10)
			val x = randX + 1
			val randY = random.nextInt(10)
			val y = randY + 1
			val c=  Coordinate(x,y)
			if(p.isShotGood(c) | p.isShotBad(c)) {
				chooseCoordinateToShoot(p, random)
			} else if((x % 2 == 0) && (y % 2 ==0)) {
				return c
			} else if((x % 2 != 0) && (y % 2 !=0)) {
				return c
			} else {
				chooseCoordinateToShoot(p, random)
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
	}

}