package battleShip

import battleShip._

case class Player(name : String, listShip : List[Ship], listShot : List[Coordinate]) {

	def isDead() : Boolean = {
		listShip.isEmpty
	}

	private def getAllCoordinateShip(l1 : List[Coordinate], c1 : Coordinate, size : Int, direction : String) : List[Coordinate] = {
		if (!c1.isValid) {
			return l1
		} else {
			size match {
				case 0 => return l1
				case _ => {
					val l2 = c1 :: l1
					direction match {
						case "up" => return getAllCoordinateShip(l2, Coordinate(c1.x, (c1.y - 1)), size-1, direction)
						case "down" => return getAllCoordinateShip(l2, Coordinate(c1.x, (c1.y + 1)), size-1, direction)
						case "left" => return getAllCoordinateShip(l2, Coordinate((c1.x - 1),c1.y), size-1, direction)
						case "right" => return getAllCoordinateShip(l2, Coordinate((c1.x + 1), c1.y), size-1, direction)
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
				case "up" if (Coordinate(firstCoordinate.x, (firstCoordinate.y - (size-1))).isValid) => Some(Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction)))
				case "down" if (Coordinate(firstCoordinate.x, (firstCoordinate.y + (size-1))).isValid) => Some(Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction)))
				case "left" if (Coordinate((firstCoordinate.x - (size-1)), firstCoordinate.y).isValid) => Some(Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction)))
				case "right" if (Coordinate((firstCoordinate.x + (size-1)), firstCoordinate.y).isValid) => Some(Ship(name,size,getAllCoordinateShip(Nil,firstCoordinate,size,direction)))
				case _ => None
			}
		}
	}

	def shipIsGood(s1 : Ship) : Boolean = {
		val res = listShip.map(s => s.isSuperposed(s1))
		return !(res.filter(bool => bool == true).size >= 1)
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
		val newPlayer = copy(listShip = newListShip)
		return newPlayer
	}

	def addShot(c1 : Coordinate) : Player = {
		val newListShot = c1 :: listShot
		val newPlayer = copy(listShot = newListShot)
		return newPlayer
	}

	def removeShip(s1 : Ship) : Player = {
		val newListShip = listShip.filter(ship => !ship.equals(s1))
		val newPlayer = copy(listShip = newListShip)
		return newPlayer
	}

	def isShoot(c1 : Coordinate) : Boolean = {
		return listShot.filter(c => c.x == c1.x && c.y == c1.y).size == 1
	}

}