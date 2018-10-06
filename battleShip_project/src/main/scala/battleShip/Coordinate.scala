package battleShip

case class Coordinate(_x : Int, _y : Int) {

	//getter
	def x: Int = _x
	def y: Int = _y


	def isValid() : Boolean = {
		if((0 < x) && (x <= 10) && (0 < y) && (y <= 10)) {
			true
		} else {
			false
		}
	}

}

