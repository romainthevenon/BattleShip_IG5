package battleShip

import battleShip._
import scala.annotation.tailrec

object Grid {

  /**
      * This function allows to print grid of the shoots to the player 
      * @param xCurrent
      * @param yCurrent
      * @param p The player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGrilleMyShoots(xCurrent : Int, yCurrent: Int, p: Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGrilleMyShoots(0,yCurrent+1,p)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGrilleMyShoots(xCurrent+1,yCurrent,p)
      } else {
        print("      ")
        return printGrilleMyShoots(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
        return printGrilleMyShoots(xCurrent+1,yCurrent,p)
      } else {
        print(yCurrent+"  || ")
        return printGrilleMyShoots(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent>10) {
      print("\n")
      return printGrilleMyShoots(0,yCurrent+1,p)
    } else {
      if (p.isShotGood(Coordinate(xCurrent,yCurrent))) {
        print("O ")
      } else if (p.isShotBad(Coordinate(xCurrent,yCurrent))) {
        print("X ")
      } else {
        print("- ")
      }
      return printGrilleMyShoots(xCurrent+1,yCurrent,p)
    }
  }

  /**
      * This function allows to print grid of the ships to the player and the shoots to the other player
      * @param xCurrent
      * @param yCurrent
      * @param p1 The player
      * @param p2 The opponent player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGrilleMyShipsAndShotsOpposing(xCurrent : Int, yCurrent: Int, p1: Player, p2 : Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGrilleMyShipsAndShotsOpposing(0,yCurrent+1,p1,p2)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      } else {
        print("      ")
        return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
        return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      } else {
        print(yCurrent+"  || ")
        return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
      }
    } else if (xCurrent>10) {
      print("\n")
      return printGrilleMyShipsAndShotsOpposing(0,yCurrent+1,p1,p2)
    } else {
      val res = p1.isContainedInOneShip(Coordinate(xCurrent,yCurrent))
      res match {
        case Some(s) => {
          print(res.get.size+" ")
          return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
        }
        case None => {
          if(p2.isShotGood(Coordinate(xCurrent,yCurrent))) {
            print("O ")
          } else if (p2.isShotBad(Coordinate(xCurrent,yCurrent))) {
            print("X ")
          } else {
            print("- ")
          }
          return printGrilleMyShipsAndShotsOpposing(xCurrent+1,yCurrent,p1,p2)
        }
      }
    }
  }

  /**
      * This function allows to print grid of the ships to the player and the shoots to the other player
      * @param xCurrent
      * @param yCurrent
      * @param p1 The player
      * @param p2 The opponent player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGrilleMyShips(xCurrent : Int, yCurrent: Int, p: Player) : Boolean = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    if(yCurrent>10) {
      return true
    } else if (yCurrent == 0) {
      if (xCurrent > 10) {
        print("\n")
        return printGrilleMyShips(0,yCurrent+1,p)
      } else if (xCurrent > 0) {
        print(letters(xCurrent-1)+" ")
        return printGrilleMyShips(xCurrent+1,yCurrent,p)
      } else {
        print("      ")
        return printGrilleMyShips(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent == 0) {
      if (yCurrent == 10) {
        print(yCurrent+" || ")
        return printGrilleMyShips(xCurrent+1,yCurrent,p)
      } else {
        print(yCurrent+"  || ")
        return printGrilleMyShips(xCurrent+1,yCurrent,p)
      }
    } else if (xCurrent>10) {
      print("\n")
      return printGrilleMyShips(0,yCurrent+1,p)
    } else {
      val res = p.isContainedInOneShip(Coordinate(xCurrent,yCurrent))
      res match {
        case Some(s) => {
          print(res.get.size+" ")
          return printGrilleMyShips(xCurrent+1,yCurrent,p)
        }
        case None => {
          print("- ")
          return printGrilleMyShips(xCurrent+1,yCurrent,p)
        }
      }
    }
  }

}