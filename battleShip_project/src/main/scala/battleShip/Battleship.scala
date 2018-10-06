package battleShip

import battleShip.Coordinate
import battleShip.Ship
import battleShip.Player
import battleShip.Helpers
import battleShip.Grid

import scala.io.StdIn.readLine
import scala.annotation.tailrec
import scala.util.Try

object BattleShip extends App {

  print("\033[H\033[2J")
  println("********************************")
  println("********** BattleShip **********")
  println("********************************")
  println()

  chooseMode

  def chooseMode() : Unit = {
    println("Choose the game mode (1: Human vs Human / 2: Human vs IA) :")
    val gameMode = Try(readInt)
    gameMode.getOrElse(-1) match {
      case 1 => {
        //println()
        print("\033[H\033[2J")
        println("You choose the game Human Vs Human !")
        println("************************************")
        println()
        println("Player 1 :")
        println("Enter name of player :")
        val name1 = readLine
        val p1 = Player(name1,Nil,Nil,Nil,0)
        println()

        println("Player 2 :")
        println("Enter name of player :")
        val name2 = readLine
        val p2 = Player(name2,Nil,Nil,Nil,0)
        game(p1,p2)
      }
      case 2 => {
        print("\033[H\033[2J")
        println("You choose the game Human Vs IA")
      }
      case _ => {
        println("Error, you should enter 1 or 2")
        chooseMode
      } 
    }
  }

  def initialisePlayer(p : Player) : Player = {
    //val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil),Ship("cruiser",3,Nil),Ship("battleShip",4,Nil),Ship("carrier",5,Nil))
    val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil))
    print("\033[H\033[2J")
    println("******************************")
    println("Initialization Player : "+p.name)
    println("----------")
    return createFleet(p,listShip)
  }

  @tailrec
  def createFleet(p: Player, listShip : List[Ship]) : Player = {
    if (listShip.isEmpty) {
        println("Initialization Player finish !")
        println("Press any key to continue")
        readLine
        return p
    } else {
      println("Create "+listShip.head.name+" (Player : "+p.name+")")
      println()
      val c = Helpers.enterCoordinate
      val d = Helpers.enterDirection
      val s1 = p.createShip(listShip.head.name,listShip.head.size,c,d)
      s1 match {
        case Some(s) => {
          val p1 = p.addShip(s1.get)
          println()
          println(listShip.head.name+" create !")
          println("----------")
          createFleet(p1,listShip.tail) 
        }
        case None => {
          println("Bad Ship ! No create !")
          println("----------")
          createFleet(p,listShip)
        }
      }
    }
  }

  def round(p1 : Player, p2 : Player) : Player = {
    print("\033[H\033[2J")
    if (!p1.isDead) {
      if (!p2.isDead) {
        println("It's "+p1.name+ "'s turn :")
        println("Grid with your ships and shot's opponent (O --> Good / X --> Bad) :")
        Grid.printGrilleMyShips(1,1,p1,p2)
        println()
        println("Grid with your shoots (O --> Good / X --> Bad) :")
        Grid.printGrilleMyShoots(1,1,p1)
        val c1 = Helpers.enterCoordinate
        val res = p2.belongToOneShip(c1)
        res match {
          case None => {
            val newP1 = p1.addShot(c1, false)
            println()
            println("It's a miss !!!")
            println("Press any key to continue")
            readLine
            round(p2, newP1)
          }
          case Some(s) => {
            val newP1 = p1.addShot(c1,true)
            val b = res.get
            val newB = b.removeCoordinate(c1)
            if (newB.isSink) {
              val newP2 = p2.removeShip(b)
              println()
              println(newB.name+" sinked !!!")
              println("Press any key to continue")
              readLine
              round(newP2, newP1)
            } else {
              val indexP2 = p2.removeShip(b)
              val newP2 = indexP2.addShip(newB)
              println()
              println(newB.name+" touched !!!")
              println("Press any key to continue")
              readLine
              round(newP2, newP1)
            }
          } 
        }
      } else {
        println(p1.name+" win this game !!!!")
        println()
        val newP1 = p1.addScore
        println(newP1.name+" win "+newP1.score)
        println(p2.name+" win "+p2.score)
        return newP1
      }
    } else {
        println(p2.name+" win !!!!")
        val newP2 = p2.addScore
        println("----------------------")
        println(newP2.name+" win "+newP2.score+" games !")
        println(p1.name+" win "+p1.score+" games !")
        return newP2
    }
  }

  def game(p1: Player, p2: Player) : Unit = {
    val newP1 = initialisePlayer(p1)
    val newP2 = initialisePlayer(p2)
    val pWinner = round(newP1,newP2)
    println("Press q to quit the game or any key to play again !")
    val res = readLine
    res match {
      case "q" => {
        println("Good bye !!!") 
      }
      case _ => {
        if (newP1.name == pWinner.name) {
          val p1Reset = pWinner.reset()
          val p2Reset = newP2.reset()
          game(p2Reset,p1Reset)
        } else {
          val p1Reset = newP1.reset()
          val p2Reset = pWinner.reset()
          game(p2Reset,p1Reset)
        }
      }
    }
  }

}