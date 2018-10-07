package battleShip

/**
  * This class represent a coordinate from the grid
 *
  * @param _x The position x of the coordinate
  * @param _y The position y of the coordinate
  */
case class Coordinate(_x : Int, _y : Int) {

	//getter
	def x: Int = _x
	def y: Int = _y

	/**
      * This function returns if a coordinate is inside the grid or not
      * @param x The position x of the coordinate
      * @param y The position y of the coordinate
      * @return Boolean : true if the coordinate is inside the grid, otherwise it equals to false
      */
	def isValid() : Boolean = {
		if((0 < x) && (x <= 10) && (0 < y) && (y <= 10)) {
			true
		} else {
			false
		}
	}

}

