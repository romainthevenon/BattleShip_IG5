package battleShip

import battleShip._
import scala.annotation.tailrec

object Grid {

  /**
      * This function allows to print grid of the shots to the player 
      * @param xCurrent
      * @param yCurrent
      * @param p The player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGridMyShots(xCurrent : Int, yCurrent: Int, p: Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGridMyShots(0,yCurrent+1,p)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGridMyShots(xCurrent+1,yCurrent,p)
      } else {
        print("      ")
        return printGridMyShots(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
      } else {
        print(yCurrent+"  || ")
      }
      return printGridMyShots(xCurrent+1,yCurrent,p)
    } else if (xCurrent>10) {
      print("\n")
      return printGridMyShots(0,yCurrent+1,p)
    } else {
      if (p.isShotGood(Coordinate(xCurrent,yCurrent))) {
        print(Console.RED+"O "+Console.RESET)
      } else if (p.isShotBad(Coordinate(xCurrent,yCurrent))) {
        print(Console.WHITE+"X "+Console.RESET)
      } else {
        print("- ")
      }
      return printGridMyShots(xCurrent+1,yCurrent,p)
    }
  }

  /**
      * This function allows to print grid of the ships to the player and the shots to the other player
      * @param xCurrent
      * @param yCurrent
      * @param p1 The player
      * @param p2 The opponent player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGridMyShipsAndShotsOpposing(xCurrent : Int, yCurrent: Int, p1: Player, p2 : Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGridMyShipsAndShotsOpposing(0,yCurrent+1,p1,p2)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGridMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      } else {
        print("      ")
        return printGridMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
      } else {
        print(yCurrent+"  || ")
      }
      return printGridMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
    } else if (xCurrent>10) {
      print("\n")
      return printGridMyShipsAndShotsOpposing(0,yCurrent+1,p1,p2)
    } else {
      val res = p1.isContainedInOneShip(Coordinate(xCurrent,yCurrent))
      res match {
        case Some(s) => {
          print(Console.BLUE+res.get.size+" "+Console.RESET)
          return printGridMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
        }
        case None => {
          if(p2.isShotGood(Coordinate(xCurrent,yCurrent))) {
            print(Console.RED+"O "+Console.RESET)
          } else if (p2.isShotBad(Coordinate(xCurrent,yCurrent))) {
            print(Console.WHITE+"X "+Console.RESET)
          } else {
            print("- ")
          }
          return printGridMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
        }
      }
    }
  }

  /**
      * This function allows to print grid of the ships to the player
      * @param xCurrent
      * @param yCurrent
      * @param p The player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGridMyShips(xCurrent : Int, yCurrent: Int, p: Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGridMyShips(0,yCurrent+1,p)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGridMyShips(xCurrent+1,yCurrent,p)
      } else {
        print("      ")
        return printGridMyShips(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
      } else {
        print(yCurrent+"  || ")
      }
      return printGridMyShips(xCurrent+1,yCurrent,p)
    } else if (xCurrent>10) {
      print("\n")
      return printGridMyShips(0,yCurrent+1,p)
    } else {
      val res = p.isContainedInOneShip(Coordinate(xCurrent,yCurrent))
      res match {
        case Some(s) => {
          print(Console.BLUE+res.get.size+" "+Console.RESET)
          return printGridMyShips(xCurrent+1,yCurrent,p)
        }
        case None => {
          print("- ")
          return printGridMyShips(xCurrent+1,yCurrent,p)
        }
      }
    }
  }

}