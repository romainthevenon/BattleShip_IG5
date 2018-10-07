package battleShip

import battleShip._
import scala.annotation.tailrec

object Grid {

  /**
      * This function allows to print grid of the shoots to the player 
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGrilleMyShoots(xActuelle : Int, yActuelle: Int, p: Player) : Boolean = {
    if(yActuelle>10) {
      return true
    } else if (xActuelle>10) {
      print("\n")
      return printGrilleMyShoots(1,yActuelle+1,p)
    } else {
      if (p.isShotGood(Coordinate(xActuelle,yActuelle))) {
        print("O ")
      } else if (p.isShotBad(Coordinate(xActuelle,yActuelle))) {
        print("X ")
      } else {
        print("- ")
      }
      return printGrilleMyShoots(xActuelle+1,yActuelle,p)
    }
  }

  /**
      * This function allows to print grid of the ships to the player and the shoots to the other player
      * @return Boolean : true when the grid is print
      */
  @tailrec
  def printGrilleMyShips(xActuelle : Int, yActuelle: Int, p1: Player, p2 : Player) : Boolean = {
    if(yActuelle>10) {
      return true
    } else if (xActuelle>10) {
      print("\n")
      return printGrilleMyShips(1,yActuelle+1,p1,p2)
    } else {
      val res = p1.isContainedInOneShip(Coordinate(xActuelle,yActuelle))
      res match {
        case Some(s) => {
          print(res.get.size+" ")
          return printGrilleMyShips(xActuelle+1,yActuelle,p1,p2)
        }
        case None => {
          if(p2.isShotGood(Coordinate(xActuelle,yActuelle))) {
            print("O ")
          } else if (p2.isShotBad(Coordinate(xActuelle,yActuelle))) {
            print("X ")
          } else {
            print("- ")
          }
          return printGrilleMyShips(xActuelle+1,yActuelle,p1,p2)
        }
      }
    }
  }
}