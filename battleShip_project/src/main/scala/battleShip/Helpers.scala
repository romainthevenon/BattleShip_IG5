package battleShip

import scala.util.Try

object Helpers {

	def enterCoordinate() : Coordinate = {
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

	def enterDirection() : String = {
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

}