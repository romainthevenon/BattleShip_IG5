package battleShip

case class Coordinate(x : String, y : Int) {

}

object Coordinate {

	def getxInt(x : String) : Int = {
		val letters: List[String] = List("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
		return letters.indexOf(x) + 1
	}

	def isValid(x : Int, y: Int) : Boolean = {
		if ((0 < x) && (x <= 10) && (0 < y) && (y <= 10)) { 
			true
		} else {
			false
		}
	}

}

