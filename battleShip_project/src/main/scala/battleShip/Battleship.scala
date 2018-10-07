package battleShip

import battleShip.Coordinate
import battleShip.Ship
import battleShip.Player
import battleShip.Helpers
import battleShip.Grid

import scala.io.StdIn.readLine
import scala.annotation.tailrec
import scala.util.Try
import scala.util.Random

object BattleShip extends App {

  print("\033[H\033[2J")
  println("********************************")
  println("********** BattleShip **********")
  println("********************************")
  println()

  println("Choose the game mode (1: Human vs Human / 2: Human vs IA) :")
  val res = Helpers.chooseMode
  res match {
    case 1 => {
      print("\033[H\033[2J")
      println("You choose the game Human Vs Human !")
      println("************************************")
      println()
      println("Player 1 :")
      println("Enter name of player :")
      val name1 = readLine
      val p1 = Player(name1,Nil,Nil,Nil,0,false)
      println()

      println("Player 2 :")
      println("Enter name of player :")
      val name2 = readLine
      val p2 = Player(name2,Nil,Nil,Nil,0,false)
      game(p1,p2)
    }
    case 2 => {
      print("\033[H\033[2J")
      println("You choose the game Human Vs IA")
      println("************************************")
      println()
      println("Player 1 :")
      println("Enter name of player :")
      val name1 = readLine
      val p1 = Player(name1,Nil,Nil,Nil,0,false)
      val p2 = Player("IALower",Nil,Nil,Nil,0,true)
      game(p1,p2)
    }
    case 3 => {
      print("\033[H\033[2J")
      println("You choose the game IA Vs IA")
    } 
  }

  /**
      * This function return the new player, the same player but with the list of ships (all ships are created)  
      * @param p The player who should be initialized
      * @return Player The new player with all ships initialized
      */
  def initialisePlayer(p : Player) : Player = {
    //val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil),Ship("cruiser",3,Nil),Ship("battleShip",4,Nil),Ship("carrier",5,Nil))
    val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil))
    print("\033[H\033[2J")
    println("******************************")
    println("Initialization Player : "+p.name)
    println("----------")
    return createFleet(p,listShip)
  }

  /**
      * This function return the new player, the same player but with the list of ships (all ships are created) 
      * @param p The player who should be initialized
      * @param listShip The list of ships whose must be created
      * @return Player The new player with all ships initialized
      */
  @tailrec
  def createFleet(p: Player, listShip : List[Ship]) : Player = {
    if (listShip.isEmpty) {
        println("Initialization Player, "+p.name+", finish !")
        println("Press any key to continue")
        readLine
        return p
    } else {
      p.isIA match {
        case false => {
          println("Create "+listShip.head.name+" (Player : "+p.name+")")
          println()
          val coordinate = Helpers.enterCoordinate
          val direction = Helpers.enterDirection
          val ship = p.createShip(listShip.head.name,listShip.head.size,coordinate,direction)
          ship match {
            case Some(s) => {
              val newP = p.addShip(ship.get)
              println()
              println(listShip.head.name+" create !")
              println("----------")
              createFleet(newP,listShip.tail) 
            }
            case None => {
              println("Bad Ship ! No create !")
              println("----------")
              createFleet(p,listShip)
            }
          }
        }
        case true => {
          val coordinate = IALower.chooseCoordinate
          val direction = IALower.chooseDirection
          val ship = p.createShip(listShip.head.name,listShip.head.size,coordinate,direction)
          ship match {
            case Some(s) => {
              val newP = p.addShip(ship.get)
              createFleet(newP,listShip.tail) 
            }
            case None => {
              createFleet(p,listShip)
            }
          }
        }
      }
    }
  }

    /**
      * This function allows to start the game and start again while the players don't stop
      * @param p The first player (it is the first player to play)
      * @param p2 The second player
      */
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

  /**
      * This function return the player who win the game, while no player is dead, we call back the function
      * @param p The first player (it is the player who play the round)
      * @param p2 The second player
      * @return Player The player who win the game
      */
  def round(p1 : Player, p2 : Player) : Player = {
    print("\033[H\033[2J")
    if (!p1.isDead) {
      if (!p2.isDead) {
        println("It's "+p1.name+ "'s turn :")
        if (p1.isIA == false) {
          println("Grid with your ships and shot's opponent (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShips(1,1,p1,p2)
          println()
          println("Grid with your shoots (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShoots(1,1,p1)
        }
        //val coordinate = Helpers.enterCoordinate
        val coordinate = coord(p1)
        val res = p2.isContainedInOneShip(coordinate)
        res match {
          case None => {
            val newP1 = p1.addShot(coordinate, false)
            println()
            println("It's a miss !!!")
            println("Press any key to continue")
            readLine
            round(p2, newP1)
          }
          case Some(s) => {
            val newP1 = p1.addShot(coordinate,true)
            val ship = res.get
            val newShip = ship.removeCoordinate(coordinate)
            if (newShip.isSink) {
              val newP2 = p2.removeShip(ship)
              println()
              println(newShip.name+" sinked !!!")
              println("Press any key to continue")
              readLine
              round(newP2, newP1)
            } else {
              val indexP2 = p2.removeShip(ship)
              val newP2 = indexP2.addShip(newShip)
              println()
              println(newShip.name+" touched !!!")
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

  def coord(p :Player) : Coordinate = {
    if (p.isIA == true) {
      IALower.chooseCoordinate
    } else {
      Helpers.enterCoordinate
    }
  }

}