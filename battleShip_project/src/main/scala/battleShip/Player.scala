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
					val l2 = c1 :: l1
					direction match {
						case "V" => return getAllCoordinateShip(l2, Coordinate(c1.x, (c1.y + 1)), size-1, direction)
						case "H" => return getAllCoordinateShip(l2, Coordinate((c1.x + 1), c1.y), size-1, direction)
					}
				}
			}
		}
	}


	def createShip(name : String, size: Int, firstCoordinate : Coordinate, direction : String) : Option[Ship] = {
		if (!firstCoordinate.isValid) {
			None
		} else {
			direction match {
				case "V" if (Coordinate(firstCoordinate.x, (firstCoordinate.y + (size-1))).isValid) => {
					val s = Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction))
					val ListSuperposed = listShip.map(ship => ship.isSuperposed(s))
					if (ListSuperposed.filter(bool => bool == true).size >= 1) {
						return None
					} else {
						return Some(s)
					}
				}
				case "H" if (Coordinate((firstCoordinate.x + (size-1)), firstCoordinate.y).isValid) => {
					val s = Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction))
					val ListSuperposed = listShip.map(ship => ship.isSuperposed(s))
					if (ListSuperposed.filter(bool => bool == true).size >= 1) {
						return None
					} else {
						return Some(s)
					}
				}
				case _ => return None
			}
		}
	}

	def belongToOneShip(c1 : Coordinate) : Option[Ship] = {
		val res = listShip.map(s => s.belongTo(c1))
		if (res.filter(bool => bool == true).size == 1) {
			val index = res.indexOf(true)
			return Some(listShip(index))
		} else {
			None
		}
	}

	def addShip(s1 : Ship) : Player = {
		val newListShip = s1 :: listShip
		val newPlayer = copy(_listShip = newListShip)
		return newPlayer
	}

	def addShot(c1 : Coordinate, isGood : Boolean) : Player = {
		isGood match {
			case true => {
				val newListShotGood = c1 :: listShotGood
				val newPlayer = copy(_listShotGood = newListShotGood)
				return newPlayer
			}
			case false => {
				val newListShotBad = c1 :: listShotBad
				val newPlayer = copy(_listShotBad = newListShotBad)
				return newPlayer
			}
		}
	}

	def removeShip(s1 : Ship) : Player = {
		val newListShip = listShip.filter(ship => !ship.equals(s1))
		val newPlayer = copy(_listShip = newListShip)
		return newPlayer
	}

	def isShootGood(c1 : Coordinate) : Boolean = {
		return listShotGood.filter(c => c.x == c1.x && c.y == c1.y).size >= 1
	}

	def isShootBad(c1 : Coordinate) : Boolean = {
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