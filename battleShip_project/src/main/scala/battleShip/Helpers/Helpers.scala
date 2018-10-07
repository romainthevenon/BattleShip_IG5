package battleShip

import battleShip._
import scala.util.Try

object Helpers {

	/**
      * This function returns the coordinate enter by the user, check if the position X and Y enter by the user is valid
      * @return Coordinate : the coordinate enter by the user
      */
	private def enterCoordinate() : Coordinate = {
	    val letters = List("A","B","C","D","E","F","G","H","I","J")
	    println("Enter coordinate X (between A and J) :")
	    val x = readLine.toUpperCase
	    val res = letters.filter(letter => letter == x).size == 1
	    res match {
	      case true => {
	        val xInt = letters.indexOf(x) + 1
	        println("Enter coordinate Y (between 1 and 10) :")
	        var y = Try(readInt)
	        val c1 = Coordinate(xInt,y.getOrElse(-1))
	        c1.isValid match {
	          case true => {
	            println("Coordinate enter : "+x+y.get)
	            return c1
	          }
	          case false => {
	            println("You enter a bad coordinate Y")
	            enterCoordinate
	          } 
	        }
	      }
	      case false => {
	        println("You enter a bad coordinate X")
	        enterCoordinate
	      }
	    } 
 	}

	/**
      * This function returns the string enter by the user, check if the direction enter by the user is "H" or "V"
      * @return String : the direction enter by the user (it is "H" or "V")
      */
	private def enterDirection() : String = {
	    println("Enter direction (V for vertical or H for horizontal) :")
	    val direction = readLine.toUpperCase
	    if ((direction != "H") && (direction != "V")) {
	      println()
	      println("You enter a bad direction")
	      enterDirection
	    } else {
	      println("Direction : "+direction)
	      return direction
	    }
	}

	/**
      * This function returns the int enter by the user, i.e the mode of game (1,2 or 3)
      * @return Int : the mode game choose by the user 
      */
	def chooseMode() : Int = {
		val mode = Try(readInt)
		mode.getOrElse(-1) match {
			case 1 => 1
			case 2 => 2
			case 3 => 3
			case _ => {
				println("Error, you should enter 1, 2 or 3")
        		chooseMode
			}	
		}
	}

	/**
      * This function returns the coordinate to shot during the round (allow to check if the player is a AI or a Human, if it is a AI use the function AI, otherwise use the input console)
      * @param p The player 
      * @return Coordinate : the coordinate to shot during the round
      */
	def coordinateToShot(p :Player) : Coordinate = {
	    if (p.isAI == true) {
	      p.name match {
	        case "AILower" => AILower.chooseCoordinate
	        case "AIMedium" => AIMedium.chooseCoordinateToShot(p)
	        case "AIHard" => AIHard.chooseCoordinateToShot(p)
	      }
	    } else {
	      enterCoordinate
	    }
  	}

	/**
      * This function returns the coordinate to place one ship (allow to check if the player is a AI or a Human, if it is a AI use the function AI, otherwise use the input console)
      * @param p The player 
      * @return Coordinate : the coordinate to place the ship
      */
  	def coordinateToPlaceShip(p: Player) : Coordinate = {
    	if (p.isAI == true) {
      		p.name match {
        		case "AILower" => AILower.chooseCoordinate
        		case "AIMedium" => AIMedium.chooseCoordinateToPlaceShip
        		case "AIHard" => AIHard.chooseCoordinateToPlaceShip
      		}
    	} else {
      		enterCoordinate
    	}
  	}

	/**
      * This function returns the direction to place one ship (allow to check if the player is a AI or a Human, if it is a AI use the function AI, otherwise use the input console)
      * @param p The player 
      * @return String : the direction to place the ship
      */
  	def directionToPlaceShip(p : Player) : String = {
	    if (p.isAI == true) {
	      p.name match {
	        case "AILower" => AILower.chooseDirection
	        case "AIMedium" => AIMedium.chooseDirection
	        case "AIHard" => AIHard.chooseDirection
	      }
	    } else {
	      enterDirection
	    }
  	}

}