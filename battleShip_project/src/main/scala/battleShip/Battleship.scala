package battleShip

import battleShip.Coordinate
import battleShip.Ship
import battleShip.Player
import battleShip.Helpers
import battleShip.Grid
import battleShip.IALower
import battleShip.IAMedium

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

  println("Choose the game mode (1: Human vs Human / 2: Human vs IA / 3: IA vs IA) :")
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
      println("Choose the IA Level (1: Low / 2: Medium / 3:Hight) :")
      val res = Helpers.chooseMode
      println()
      println("Player 1 :")
      println("Enter name of player :")
      val name1 = readLine
      val p1 = Player(name1,Nil,Nil,Nil,0,false)
      res match {
        case 1 => {
          val p2 = Player("IALower",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
        case 2 => {
          val p2 = Player("IAMedium",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
        case 3 => {
          val p2 = Player("IAHard",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
      }
    }
    case 3 => {
      print("\033[H\033[2J")
      println("You choose the game IA Vs IA")
      val p1 = Player("IALower",Nil,Nil,Nil,0,true)
      val p2 = Player("IAMedium",Nil,Nil,Nil,0,true)
      val p3 = Player("IAHard",Nil,Nil,Nil,0,true)
      gameIAVsIA(p1,p2,0)
      gameIAVsIA(p1,p3,0)
      gameIAVsIA(p2,p3,0)
    } 
  }

  /**
      * This function return the new player, the same player but with the list of ships (all ships are created)  
      * @param p The player who should be initialized
      * @return Player The new player with all ships initialized
      */
  def initialisePlayer(p : Player) : Player = {
    val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil),Ship("cruiser",3,Nil),Ship("battleShip",4,Nil),Ship("carrier",5,Nil))
    //val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil))
    //val listShip = List(Ship("destroyer",2,Nil))
    if (p.isIA == false) {
      print("\033[H\033[2J")
      println("******************************")
      println("Initialization Player : "+p.name)
      println("----------")
    }
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
      if(p.isIA == false) {
        println("Initialization Player, "+p.name+", finish !")
        println("Press any key to continue")
        readLine
      }
      return p
    } else {
      if(p.isIA == false) {
        println("Create "+listShip.head.name+" (Player : "+p.name+")")
        println()
      }
      val coordinate = Helpers.coordinateToPlaceShip(p)
      val direction = Helpers.directionToPlaceShip(p)
      val ship = p.createShip(listShip.head.name,listShip.head.size,coordinate,direction)
      ship match {
        case Some(s) => {
          val newP = p.addShip(ship.get)
          if (p.isIA == false) {
            println()
            println(listShip.head.name+" create !")
            println("----------")
          }
          createFleet(newP,listShip.tail) 
        }
        case None => {
          if (p.isIA == false) {
            println("Bad Ship ! No create !")
            println("----------")
          }
          createFleet(p,listShip)
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
    val listP = round(newP1,newP2)
    print("\033[H\033[2J")
    println(listP(0).name+" win this game !")
    println()
    println(listP(0).name+" win "+listP(0).score+" games !")
    println(listP(1).name+" win "+listP(1).score+" games !")
    println("Press q to quit the game or any key to play again !")
    val res = readLine
    res match {
      case "q" => {
        println("Good bye !!!") 
      }
      case _ => {
        val p1Reset = listP(0).reset()
        val p2Reset = listP(1).reset()
        game(p2Reset,p1Reset)
      }
    }
  }

  def gameIAVsIA(p1 : Player, p2 : Player, nbRound : Int) : Unit = {
    val newP1 = initialisePlayer(p1)
    val newP2 = initialisePlayer(p2)
    val listP = round(newP1,newP2)
    nbRound match {
      case 1999 => {
        println("Game : "+newP1.name+" VS "+newP2.name)
        println("----------")
        println(listP(0).name+" win "+listP(0).score+" games !")
        println(listP(1).name+" win "+listP(1).score+" games !")
        println()
      }
      case _ => {
          val p1Reset = listP(0).reset()
          val p2Reset = listP(1).reset()
          gameIAVsIA(p2Reset,p1Reset,nbRound+1)
      }
    }
  }
    
  

  /**
      * This function return the player who win the game, while no player is dead, we call back the function
      * @param p The first player (it is the player who play the round)
      * @param p2 The second player
      * @return Player The player who win the game
      */
  def round(p1 : Player, p2 : Player) : List[Player] = {
    if (!p1.isDead) {
      if (!p2.isDead) {
        if (p1.isIA == false) {
          print("\033[H\033[2J")
          println("It's "+p1.name+ "'s turn :")
          println("Grid with your ships and shot's opponent (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShips(1,1,p1,p2)
          println()
          println("Grid with your shoots (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShoots(1,1,p1)
        }
        //val coordinate = Helpers.enterCoordinate
        val coordinate = Helpers.coordinateToShot(p1)
        val res = p2.isContainedInOneShip(coordinate)
        res match {
          case None => {
            val newP1 = p1.addShot(coordinate, false)
            if (p1.isIA == false) {
              println()
              println("It's a miss !!!")
              println("Press any key to continue")
              readLine
            }
            round(p2, newP1)
          }
          case Some(s) => {
            val newP1 = p1.addShot(coordinate,true)
            val ship = res.get
            val newShip = ship.removeCoordinate(coordinate)
            if (newShip.isSink) {
              val newP2 = p2.removeShip(ship)
              if (p1.isIA == false) {
                println()
                println(newShip.name+" sinked !!!")
                println("Press any key to continue")
                readLine
              }
              round(newP2, newP1)
            } else {
              val indexP2 = p2.removeShip(ship)
              val newP2 = indexP2.addShip(newShip)
              if (p1.isIA == false) {
                println()
                println(newShip.name+" touched !!!")
                println("Press any key to continue")
                readLine
              }
              round(newP2, newP1)
            }
          } 
        }
      } else {
        val newP1 = p1.addScore
        return List(newP1,p2)
      }
    } else {
        val newP2 = p2.addScore
        return List(newP2,p1)
    }
  }

}