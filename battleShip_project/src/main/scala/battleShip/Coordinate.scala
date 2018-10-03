package battleShip

case class Coordinate(x : Int, y : Int) {

	def isValid() : Boolean = {
		if((0 < x) && (x <= 10) && (0 < y) && (y <= 10)) {
			true
		} else {
			false
		}
	}

}

