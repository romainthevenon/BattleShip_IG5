/*package battleShip

class Player(private val name : String, private var listShip : List[Ship], private var listShot : List[Coordinate]) {

	def getListShip() : List[Ship] = {
		return listShip
	}

	def getListShot() : List[Coordinate] = {
		return listShot
	}

	def addShot(c1 : Coordinate) = {
		listShot ::: c1 :: Nil
	}

	//A reflechir
	def addShip(s1 : Ship) = {
		listShip ::: s1 :: Nil
	}

	def removeShip(s1 : Ship) = {
		listShip = listShip.filter(_ != s1)
	}

	def isDead() : Boolean = {
		listShip.isEmpty
	}
}*/