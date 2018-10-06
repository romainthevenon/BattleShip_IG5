package battleShip 

import battleShip.Coordinate
import scala.annotation.tailrec

/**
  * This class represent a ship
 *
  * @param _name The name of the ship
  * @param _size The size of the ship
  * @param _listCoordinate All of coordinate that compose the ship
  */
case class Ship(_name : String, _size : Int, _listCoordinate : List[Coordinate]) {

	//getter
	def name: String = _name
	def size: Int = _size
	def listCoordinate: List[Coordinate] = _listCoordinate


	/**
      * This function returns if a ship is superposed with the ship given as parameter
      * @param s2 The second ship
      * @return Boolean : true if the ship is superposed with the ship given as parameter, otherwise it equals to false
      */
    @tailrec
	def isSuperposed(ship2 : Ship) : Boolean = {
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

	/**
      * This function returns a new ship, the same ship but without the coordinate given as parameter in the listCoordinate
      * @param c1 The coordinate has to remove
      * @return Ship : the new ship without the coordinate in the listCoordinate
      * Contraint: the coordinate should be present in the listCoordinate of the ship
      */
	def removeCoordinate(c1 : Coordinate) : Ship = {
		val newListCoordinate = listCoordinate.filter(coordinate => !coordinate.equals(c1))
		return copy(_listCoordinate = newListCoordinate)
	}

	/**
      * This function returns if a coordinate is contained in the ship
      * @param c1 The coordinate
      * @return Boolean : true if the coordinate is contained in the ship, otherwise it equals to false
      */
	def isContained(c1 : Coordinate) : Boolean = {
		return listCoordinate.filter(c => c.x == c1.x && c.y == c1.y).size == 1
	}

	/**
      * This function returns if a ship is sink or not, i.e the list of coordinate is empty
      * @return Boolean : true if the list of coordinate is empty, otherwise it equals to false
      */
	def isSink() : Boolean = {
		listCoordinate.isEmpty
	}

}