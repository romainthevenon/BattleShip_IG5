package battleShip

import battleShip.Coordinate
import battleShip.Ship

import scala.io.StdIn.readLine

object App {

  def main(args : Array[String]) {
    println("********** BattleShip ********** \n")
    val c = enterCoordinate
    val test = Ship.isValid(c,3,"down")
    println(test)
    var b3 = new Ship("bateau 3",test)
    /*var l : List[Coordinate] = Nil
    l =  c :: l
    println(l)*/
    /*printGrille(1,1,10,10)*/
   
    val c1 = Coordinate("A",1)
    val c2 = Coordinate("A",2)
    val c3 = Coordinate("A",3)
    val c4 = Coordinate("A",4)

    var l1 = List(c1,c2,c3,c4)
    var l2 = List(c3,c4)

    var b1 = new Ship("bateau 1",l1)
    var b2 = new Ship("bateau 2",l2)

    println(b3.isSuperposed(b1))

    /*var res = b1.isSuperposed(b2)
    println(res)
    println(b1.getListCoordinate)
    b1.removeCoordinate(c2)
    println(b1.getListCoordinate)*/
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
  }
}