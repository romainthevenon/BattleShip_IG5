package battleShip 

import battleShip._

import scala.annotation.tailrec

case class Ship(name : String, size : Int, listCoordinate : List[Coordinate]) {

	def isSink() : Boolean = {
		listCoordinate.isEmpty
	}

	@tailrec
	private def l1containsl2(l1 : List[Coordinate], l2 : List[Coordinate]) : Boolean = {
		if (l2.isEmpty) {
			false
		} else if (l1.isEmpty) {
			false
		} else if (l1.contains(l2.head)) {
			true
		} else {
			l1containsl2(l1, l2.tail)
		}
	}

	def isSuperposed(ship2 : Ship) : Boolean = {
		l1containsl2(listCoordinate, ship2.listCoordinate)
	}

	def removeCoordinate(c1 : Coordinate) : Ship = {
		val newListCoordinate = listCoordinate.filter(coordinate => !coordinate.equals(c1))
		val newShip = copy(listCoordinate = newListCoordinate)
		return newShip
	}

	def belongTo(c1 : Coordinate) : Boolean = {
		return listCoordinate.filter(c => c.x == c1.x && c.y == c1.y).size == 1
	}

}