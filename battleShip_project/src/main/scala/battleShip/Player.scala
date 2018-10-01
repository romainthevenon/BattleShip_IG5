package battleShip

class Player(private val name : String, private var listShip : List[Ship], private var listShot : List[Coordinate]) {

	def removeShip(s1 : Ship) = {
		listShip = listShip.filter(_ != s1)
	}

	def isDead() : Boolean = {
		listShip.isEmpty
	}
}