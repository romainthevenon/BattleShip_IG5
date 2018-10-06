package battleShip

import battleShip.Coordinate
import battleShip.Ship

case class Player(_name : String, _listShip : List[Ship], _listShotGood : List[Coordinate], _listShotBad : List[Coordinate], _score : Int) {

	//Getter
	def name: String = _name
	def listShip: List[Ship] = _listShip
	def listShotGood: List[Coordinate] = _listShotGood
	def listShotBad: List[Coordinate] = _listShotBad
	def score: Int = _score

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

	def isContainedInOneShip(c1 : Coordinate) : Option[Ship] = {
		val listContained = listShip.map(s => s.isContained(c1))
		if (listContained.filter(bool => bool == true).size == 1) {
			return Some(listShip(listContained.indexOf(true)))
		} else {
			None
		}
	}

	def addShip(s1 : Ship) : Player = {
		return copy(_listShip = s1 :: listShip)
	}

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

	def removeShip(s1 : Ship) : Player = {
		val newListShip = listShip.filter(ship => !ship.equals(s1))
		return copy(_listShip = newListShip)
	}

	def isShotGood(c1 : Coordinate) : Boolean = {
		return listShotGood.filter(c => c.x == c1.x && c.y == c1.y).size >= 1
	}

	def isShotBad(c1 : Coordinate) : Boolean = {
		return listShotBad.filter(c => c.x == c1.x && c.y == c1.y).size >= 1
	}

	def reset() : Player = {
		return copy(_listShip = Nil, _listShotGood = Nil, _listShotBad = Nil)
	}

	def addScore() : Player = {
		val newScore = _score + 1
		return copy(_score = newScore)
	}

	def isDead() : Boolean = {
		listShip.isEmpty
	}

}