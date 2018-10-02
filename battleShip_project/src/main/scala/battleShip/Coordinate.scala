package battleShip

case class Coordinate(x : String, y : Int) {

	def isValid() : Boolean = {
		val letters = List("A","B","C","D","E","F","G","H","I","J")
		val xInt = letters.indexOf(x) + 1
		if((0 < xInt) && (xInt <= 10) && (0 < y) && (y <= 10)) {
			true
		} else {
			false
		}
	}

}

