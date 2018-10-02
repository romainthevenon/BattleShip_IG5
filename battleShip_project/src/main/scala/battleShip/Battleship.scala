package battleShip

import battleShip.Coordinate
//import battleShip.Ship

import scala.io.StdIn.readLine

object App {

  def main(args : Array[String]) {
    println("********** BattleShip ********** \n")

    val c1 = Coordinate("A",1)
    val c2 = Coordinate("K",9)
    val c3 = Coordinate("B",11)
    println(c1.isValid)
    println(c2.isValid)
    println(c3.isValid)
    /*val gameMode = chooseMode
    gameMode match {
      case 1 => {
        println("Enter the name of player 1 :")
        val namePlayer = readLine
        //a enlever
        var listShip = List()
        var listShot = List()
      }
      case 2 => {

      } 
    }*/

    /*val c = enterCoordinate
    val test = Ship.isValid(c,3,"down")
    println(test)
    var b3 = new Ship("bateau 3",test)
   
    val c1 = Coordinate("A",1)
    val c2 = Coordinate("A",2)
    val c3 = Coordinate("A",3)
    val c4 = Coordinate("A",4)

    var l1 = List(c1,c2,c3,c4)
    var l2 = List(c3,c4)

    var b1 = new Ship("bateau 1",l1)
    var b2 = new Ship("bateau 2",l2)

    println(b3.isSuperposed(b1))*/

    /*var res = b1.isSuperposed(b2)
    println(res)
    println(b1.getListCoordinate)
    b1.removeCoordinate(c2)
    println(b1.getListCoordinate)*/
  }

/*  def chooseMode() : Int = {
    println("Choose the game mode (1: Human vs Human / 2: Human vs IA) :")
    val gameMode = readInt
    gameMode match {
      case 1 => {
        println("You choose the game Human Vs Human !")
        return gameMode
      }
      case 2 => {
        println("You choose the game Human Vs IA")
        return gameMode
      }
      case _ => {
        println("Error, you should enter 1 or 2")
        return chooseMode
      } 
    }
  }

  def enterCoordinate() : Coordinate = {
    println("Enter coordinate : ")
    val coord = readLine
    val res = Coordinate.isValid(Coordinate.getxInt(coord.substring(0,1)),coord.substring(1).toInt) 
    res match {
      case true => Coordinate(coord.substring(0,1),coord.substring(1).toInt)
      case false => {
                      println("Mauvaise coordonnée")
                      enterCoordinate
                    }
    }
  } 

  def printGrille(xActuelle : Int, yActuelle: Int, xFin: Int, yFin: Int) : Boolean = {
    if(yActuelle>yFin) {
      return true
    } else if (xActuelle>xFin) {
      print("\n")
      return printGrille(1,yActuelle+1,xFin,yFin)
    } else {
      print("-")
      return printGrille(xActuelle+1,yActuelle,xFin,yFin)
    }
  }*/
}