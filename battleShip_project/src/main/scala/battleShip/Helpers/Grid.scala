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
    if(yCurrent>10) {
      return true
    } else if (xCurrent>10) {
      print("\n")
      return printGrilleMyShoots(1,yCurrent+1,p)
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
  def printGrilleMyShips(xCurrent : Int, yCurrent: Int, p1: Player, p2 : Player) : Boolean = {
    if(yCurrent>10) {
      return true
    } else if (xCurrent>10) {
      print("\n")
      return printGrilleMyShips(1,yCurrent+1,p1,p2)
    } else {
      val res = p1.isContainedInOneShip(Coordinate(xCurrent,yCurrent))
      res match {
        case Some(s) => {
          print(res.get.size+" ")
          return printGrilleMyShips(xCurrent+1,yCurrent,p1,p2)
        }
        case None => {
          if(p2.isShotGood(Coordinate(xCurrent,yCurrent))) {
            print("O ")
          } else if (p2.isShotBad(Coordinate(xCurrent,yCurrent))) {
            print("X ")
          } else {
            print("- ")
          }
          return printGrilleMyShips(xCurrent+1,yCurrent,p1,p2)
        }
      }
    }
  }
}