package battleShip 

import battleShip._

import scala.annotation.tailrec

class Ship(private val name : String, private var listCoordinate : List[Coordinate]) {

	def getListCoordinate() : List[Coordinate] = listCoordinate

	@tailrec
	private def l1containsl2(l1 : List[Coordinate], l2 : List[Coordinate]) : Boolean = {
		if (l2.isEmpty) {
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

	def removeCoordinate(c1 : Coordinate) = {
		listCoordinate = listCoordinate.filter(_ != c1)
	}

	def isSink() : Boolean = {
		listCoordinate.isEmpty
	}

}

object Ship {

	def isValid(firstCoordinate : Coordinate, size : Int, direction : String) : List[Coordinate] = {
		//Verifier si le bateau est valide --> regarder si le bateau rentre dans la grille + si ne superpose pas un autre bateau
		//up down left right
		direction match {
			case "up" if (Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x),firstCoordinate.y - (size-1))) => {
				return ajoutCoordinate(Nil, firstCoordinate, size, direction)
			}
			case "up" if (!Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x),firstCoordinate.y - (size-1))) => {
				return Nil
			}
			case "down" if (Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y + (size-1))) => {
				return ajoutCoordinate(Nil, firstCoordinate, size, direction)
			}
			case "down" if (!Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y + (size-1))) => {
				return Nil
			}  
			case "left" if (Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y - (size-1))) => {
				return ajoutCoordinate(Nil, firstCoordinate, size, direction)
			}
			case "left" if (!Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y - (size-1))) => {
				return Nil
			}
			case "right" if (Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y + (size-1))) => {
				return ajoutCoordinate(Nil, firstCoordinate, size, direction)
			}
			case "right" if (!Coordinate.isValid(Coordinate.getxInt(firstCoordinate.x), firstCoordinate.y + (size-1))) => {
				return Nil
			}
		}
	}

	private def ajoutCoordinate(l : List[Coordinate], currentCoordinate : Coordinate, size : Int, direction: String) : List[Coordinate] = {
		val letters : List[String] = List("Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
		var list : List[Coordinate] = List()
		size match {
			case 0 => return l
			case _ => {
				list = l:::currentCoordinate::Nil
				direction match {
					case "up" => return ajoutCoordinate(list, Coordinate(currentCoordinate.x, currentCoordinate.y - 1), size - 1, direction)
					case "down" => return ajoutCoordinate(list, Coordinate(currentCoordinate.x, currentCoordinate.y + 1), size - 1, direction)
					case "left" => {
						var index = letters.indexOf(currentCoordinate.x) 
						println(index) 
						index = index - 1
						return ajoutCoordinate(list, Coordinate(letters(index),currentCoordinate.y), size-1, direction)
					}
					case "right" => {
						var index = letters.indexOf(currentCoordinate.x)
						index = index + 1
						return ajoutCoordinate(list, Coordinate(letters(index),currentCoordinate.y), size-1, direction)		
					}
				}
			} 
		}
	} 
}
