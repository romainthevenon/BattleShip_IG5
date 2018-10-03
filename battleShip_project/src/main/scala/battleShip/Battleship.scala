package battleShip

import battleShip.Coordinate
import battleShip.Ship
import battleShip.Player

import scala.io.StdIn.readLine
import scala.annotation.tailrec

object App {

  def main(args : Array[String]) {
    println("********************************")
    println("********** BattleShip **********")
    println("********************************")
    println()

    chooseMode
  }

  def chooseMode() = {
    println("Choose the game mode (1: Human vs Human / 2: Human vs IA) :")
    val gameMode = readInt
    gameMode match {
      case 1 => {
        println()
        println("You choose the game Human Vs Human !")
        println("************************************")
        println()
        println("Player 1 :")
        val p1 = initialisePlayer
        println()
        println("Player 2 :")
        val p2 = initialisePlayer
        game(p1,p2)
      }
      case 2 => {
        println()
        println("You choose the game Human Vs IA")
      }
      case _ => {
        println("Error, you should enter 1 or 2")
      } 
    }
  }

  

  def enterCoordinate() : Coordinate = {
    val letters = List("A","B","C","D","E","F","G","H","I","J")
    println("Enter coordinate X (between A and J) :")
    val x = readLine
    val res = letters.filter(letter => letter == x).size == 1
    res match {
      case true => {
        val xInt = letters.indexOf(x) + 1
        println("Enter coordinate Y (between 1 and 10) :")
        var y = readInt
        val c1 = Coordinate(xInt,y)
        c1.isValid match {
          case true => {
            println("Coordinate enter : "+x+y)
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
    println("Enter direction (up,down,left or right) :")
    val direction = readLine
    if ((direction != "up") && (direction != "down") && (direction != "left") && (direction != "right")) {
      println("You enter a bad direction")
      enterDirection
    } else {
      println("Direction : "+direction)
      return direction
    }
  }

  def createShip(name : String, size : Int, p1 : Player) : Ship = {
    val c = enterCoordinate
    val d = enterDirection
    val res = p1.createShip(name,size,c,d)
    res match {
      case Some(s) => {
        if(p1.shipIsGood(res.get)) {
          return res.get
        } else {
          createShip(name, size, p1)
        }
      }
      case None => {
        println("Bad ship !")
        createShip(name, size, p1)
      }  
    }
  }

  def initialisePlayer() : Player = {
    println("Enter name of player :")
    val name = readLine
    val p = Player(name,Nil,Nil)
    println()
    println("******************************")
    println("Initialization Player : "+name)
    return createFleet(p,1)
  }

//destroyer --> 2 cases
//submarine --> 3 cases
//cruiser --> 3 cases
// Battleship --> 4 cases
//carrier --> 5 cases

  @tailrec
  def createFleet(p: Player, index : Int) : Player = {
    index match {
      case 1 => {
        println("----------")
        println("Create Destroyer (Player : "+p.name+")")
        println()
        val s1 = createShip("destroyer",2,p)
        val p1 = p.addShip(s1)
        println()
        println("Destroyer create !")
        createFleet(p1,index + 1)
      }
/*
      case 2 => {
        println("----------")
        println("Create Submarine (Player : "+p.name+")")
        println()
        val s2 = createShip("submarine",3,p)
        val p2 = p.addShip(s2)
        println()
        println("Submarine create !")
        createFleet(p2,index + 1)
      }
      case 3 => {
        println("----------")
        println("Create Cruiser (Player : "+p.name+")")
        println()
        val s3 = createShip("cruiser",3,p)
        val p3 = p.addShip(s3)
        println()
        println("Cruiser create !")
        createFleet(p3,index + 1)
      }
      case 4 => {
        println("----------")
        println("Create Battleship (Player : "+p.name+")")
        println()
        val s4 = createShip("battleShip",4,p)
        val p4 = p.addShip(s4)
        println()
        println("BattleShip create !")
        createFleet(p4,index + 1)
      }
      case 5 => {
        println("----------")
        println("Create Carrier (Player : "+p.name+")")
        println()
        val s5 = createShip("carrier",5,p)
        val p5 = p.addShip(s5)
        println()
        println("Carrier create !")
        createFleet(p5,index + 1)
      }
*/
      case 2 => {
        println()
        println("Initialization Player finish !")
        return p
      }
    }
  }

  def game(p1 : Player, p2 : Player) {
    if (!p1.isDead) {
      if (!p2.isDead) {
        println()
        println(p1.name+ " à ton tour :")
        println("*******************************")
        println()
        printGrilleMyShips(1,1,10,10,p1,p2)
        println()
        println("*******************************")
        println()
        printGrilleMyShoots(1,1,10,10,p1)
        println()
        val c1 = enterCoordinate
        val newP1 = p1.addShot(c1)
        val res = p2.belongToOneShip(c1)
        res match {
          case None => {
            println()
            println("A l'eau !!!")
            game(p2, newP1)
          }
          case Some(s) => {
            val b = res.get
            val newB = b.removeCoordinate(c1)
            if (newB.isSink) {
              val newP2 = p2.removeShip(b)
              println()
              println(newB.name+" coulé !!!")
              game(newP2, newP1)
            } else {
              val indexP2 = p2.removeShip(b)
              val newP2 = indexP2.addShip(newB)
              println()
              println(newB.name+" touché !!!")
              game(newP2, newP1)
            }
          } 
        }
      } else {
        println(p1.name+" win !!!!")
      }
    } else {
      println(p2.name+" win !!!!!!")
    }
  }

  def printGrilleMyShoots(xActuelle : Int, yActuelle: Int, xFin: Int, yFin: Int, p: Player) : Boolean = {
    if(yActuelle>yFin) {
      return true
    } else if (xActuelle>xFin) {
      print("\n")
      return printGrilleMyShoots(1,yActuelle+1,xFin,yFin,p)
    } else {
      if (p.isShoot(Coordinate(xActuelle,yActuelle))) {
        print("X")
      } else {
        print("-")
      }
      return printGrilleMyShoots(xActuelle+1,yActuelle,xFin,yFin,p)
    }
  }

  def printGrilleMyShips(xActuelle : Int, yActuelle: Int, xFin: Int, yFin: Int, p1: Player, p2 : Player) : Boolean = {
    if(yActuelle>yFin) {
      return true
    } else if (xActuelle>xFin) {
      print("\n")
      return printGrilleMyShips(1,yActuelle+1,xFin,yFin,p1,p2)
    } else {
      val res = p1.belongToOneShip(Coordinate(xActuelle,yActuelle))
      res match {
        case Some(s) => {
          print(res.get.size)
          return printGrilleMyShips(xActuelle+1,yActuelle,xFin,yFin,p1,p2)
        }
        case None => {
          if(p2.isShoot(Coordinate(xActuelle,yActuelle))) {
            print("X")
          } else {
            print("-")
          }
          return printGrilleMyShips(xActuelle+1,yActuelle,xFin,yFin,p1,p2)
        } 
      }
    }
  }


}