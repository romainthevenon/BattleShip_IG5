package battleShip

import battleShip._

import scala.annotation.tailrec

/**
  * This class represent a player
 *
  * @param _name The name of the ship
  * @param _listShip The list of ships 
  * @param _listShotGood The list of shots good of the player (shots that touch or sink a ship)
  * @param _listShotBad The list of shots bad of the player (shots that no touch or sink a ship)
  * @param _score The score of the player
  * @param _isIA true if the player is IA, otherwise it equals to false
  */
case class Player(_name : String, _listShip : List[Ship], _listShotGood : List[Coordinate], _listShotBad : List[Coordinate], _score : Int, _isIA : Boolean) {

	//Getter
	def name: String = _name
	def listShip: List[Ship] = _listShip
	def listShotGood: List[Coordinate] = _listShotGood
	def listShotBad: List[Coordinate] = _listShotBad
	def score: Int = _score
	def isIA: Boolean = _isIA

	/**
      * This function returns the list of coordinate from the first coordinate, the size and the direction (allow to recover all coordinates of a ship)
      * @param l1 The list of coordinates
      * @param c1 The first coordinate
      * @param size The size of the ship
      * @param direction The direction of the ship
      * @return List[Coordinate] : the list of all coordinates 
      * Contraint: the coordinate should be valid and the direction should be "H" or "V"
      */
    @tailrec
	private def getAllCoordinateShip(l1 : List[Coordinate], c1 : Coordinate, size : Int, direction : String) : List[Coordinate] = {
		if (!c1.isValid) {
			return l1
		} else {
			size match {
				case 0 => return l1
				case _ => {
					direction match {
						case "V" => return getAllCoordinateShip(c1 :: l1, Coordinate(c1.x, (c1.y + 1)), size-1, direction)
						case "H" => return getAllCoordinateShip(c1 :: l1, Coordinate((c1.x + 1), c1.y), size-1, direction)
					}
				}
			}
		}
	}

	/**
      * This function returns a Option[Ship], i.e Some(ship) if the ship is created or None if the ship can't be created
      * @param name The name of the ship
      * @param size The size of the ship
      * @param firstCoordinate The first coordinate of the ship
      * @param direction The direction of the ship
      * @return Option[Ship], i.e Some(Ship) if the ship is valid, None if the ship can't be created
      * Contraint: the firstCoordinate should be valid, the direction should be "H" or "V"
      */
	def createShip(name : String, size: Int, firstCoordinate : Coordinate, direction : String) : Option[Ship] = {
		if (!firstCoordinate.isValid) {
			return None
		} else {
			direction match {
				case "V" if (Coordinate(firstCoordinate.x, (firstCoordinate.y + (size-1))).isValid) => {
					val ship = Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction))
					val ListSuperposed = listShip.map(s => s.isSuperposed(ship))
					if (ListSuperposed.filter(bool => bool == true).size >= 1) {
						return None
					} else {
						return Some(ship)
					}
				}
				case "H" if (Coordinate((firstCoordinate.x + (size-1)), firstCoordinate.y).isValid) => {
					val ship = Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction))
					val listSuperposed = listShip.map(s => s.isSuperposed(ship))
					if (listSuperposed.filter(bool => bool == true).size >= 1) {
						return None
					} else {
						return Some(ship)
					}
				}
				case _ => return None
			}
		}
	}

	/**
      * This function returns a option of ship, i.e returns some(ship) if the coordinate is contained in this ship, None if the coordinate isn't contained in all ships of player
      * @param c1 The coordinate has to check
      * @return Option[Ship] : Some(ship) or None
      * Contraint: the coordinate should be valid
      */
	def isContainedInOneShip(c1 : Coordinate) : Option[Ship] = {
		val listContained = listShip.map(s => s.isContained(c1))
		if (listContained.filter(bool => bool == true).size == 1) {
			return Some(listShip(listContained.indexOf(true)))
		} else {
			None
		}
	}

	/**
      * This function returns a new player, the same player but with the ship added in the list of ships
      * @param s1 The ship has to add
      * @return Player : the new player with the ship
      * Contraint: the ship should be valid
      */
	def addShip(s1 : Ship) : Player = {
		return copy(_listShip = s1 :: listShip)
	}

	/**
      * This function returns a new player, the same player but with the coordinate added in the listShotGood (if isGood is true) or in the listShotBad (if isGood is bad)
      * @param c1 The coordinate has to add
      * @param isGood A boolean (true if shot is good, i.e touch or sunk a ship, or false if shot is bad)
      * @return Player : the new player with the coordinate
      * Contraint: the coordinate should be valid
      */
	def addShot(c1 : Coordinate, isGood : Boolean) : Player = {
		isGood match {
			case true => {
				return copy(_listShotGood = c1 :: listShotGood)
			}
			case false => {
				return copy(_listShotBad = c1 :: listShotBad)
			}
		}
	}

	/**
      * This function returns a new player, the same player but without the ship given as parameter in the listShip
      * @param s1 The ship has to remove
      * @return Player : the new player without the ship in the listShip
      * Contraint: the ship should be valid and present in the listShip of the player
      */
	def removeShip(s1 : Ship) : Player = {
		val newListShip = listShip.filter(ship => !ship.equals(s1))
		return copy(_listShip = newListShip)
	}

	/**
      * This function returns if the coordinate given as parameter is contained in the list of shots good
      * @param c1 The coordinate to be check
      * @return Boolean : true if the coordinate is contained in the list of shots good, otherwise it equals to false
      * Contraint: the coordinate should be valid
      */
	def isShotGood(c1 : Coordinate) : Boolean = {
		return listShotGood.filter(c => c.x == c1.x && c.y == c1.y).size >= 1
	}

	/**
      * This function returns if the coordinate given as parameter is contained in the list of shots bad
      * @param c1 The coordinate to be check
      * @return Boolean : true if the coordinate is contained in the list of shots bad, otherwise it equals to false
      * Contraint: the coordinate should be valid
      */
	def isShotBad(c1 : Coordinate) : Boolean = {
		return listShotBad.filter(c => c.x == c1.x && c.y == c1.y).size >= 1
	}

	/**
      * This function returns a new player, the same player but the list of ship, list of shot (good and bad) are empty
      * @return Player : the new player with listShip, listShotGood and listShotBad empty
      */
	def reset() : Player = {
		return copy(_listShip = Nil, _listShotGood = Nil, _listShotBad = Nil)
	}

	/**
      * This function returns a new player, the same player but the score is increased by 1
      * @return Player : the new player with a score increased by 1
      */
	def addScore() : Player = {
		val newScore = _score + 1
		return copy(_score = newScore)
	}

	/**
      * This function returns if the player id dead or not (i.e if the list of ships is empty)
      * @return Boolean : the new ship without the coordinate in the listCoordinate
      * Contraint: true if the list of ships is empty, otherwise it equals to false
      */
	def isDead() : Boolean = {
		listShip.isEmpty
	}

}