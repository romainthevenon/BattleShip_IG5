package battleShip

import battleShip._

import java.io.File

import scala.io.StdIn.readLine
import scala.annotation.tailrec
import com.github.tototoshi.csv.CSVWriter

object BattleShip extends App {

  //file to write score in csv (AI vs AI)
  val fileName: String = "ai_proof.csv"

  Helpers.clear
  println("********************************")
  println("********** BattleShip **********")
  println("********************************")
  println()

  println("Choose the game mode (1: Human vs Human / 2: Human vs AI / 3: AI vs AI) :")
  val res = Helpers.chooseMode
  res match {
    case 1 => {
      Helpers.clear
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
      Helpers.clear
      println("You choose the game Human Vs AI")
      println("************************************")
      println("Choose the AI Level (1: Low / 2: Medium / 3:Hight) :")
      val res = Helpers.chooseMode
      println()
      println("Player 1 :")
      println("Enter name of player :")
      val name1 = readLine
      val p1 = Player(name1,Nil,Nil,Nil,0,false)
      res match {
        case 1 => {
          val p2 = Player("AILower",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
        case 2 => {
          val p2 = Player("AIMedium",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
        case 3 => {
          val p2 = Player("AIHard",Nil,Nil,Nil,0,true)
          game(p1,p2)
        }
      }
    }
    case 3 => {
      Helpers.clear
      println("You choose the game AI Vs AI")
      val p1 = Player("AILower",Nil,Nil,Nil,0,true)
      val p2 = Player("AIMedium",Nil,Nil,Nil,0,true)
      val p3 = Player("AIHard",Nil,Nil,Nil,0,true)
      val res1 = gameAIVsAI(p1,p2,1,100)
      val res2 = gameAIVsAI(p1,p3,1,100)
      val res3 = gameAIVsAI(p2,p3,1,100)
      val file = new File(fileName)
      val writer = CSVWriter.open(file)
      writer.writeRow(List("AI Name", "score", "AI Name 2", "score 2"))
      writer.writeRow(List(res1(0).name,res1(0).score,res1(1).name,res1(1).score))
      writer.writeRow(List(res2(0).name,res2(0).score,res2(1).name,res2(1).score))
      writer.writeRow(List(res3(0).name,res3(0).score,res3(1).name,res3(1).score))
      writer.close()
    } 
  }

    /**
      * This function allows to start the game and start again while the players don't stop
      * @param p The first player (it is the first player to play)
      * @param p2 The second player
      */
  @tailrec
  def game(p1: Player, p2: Player) : Unit = {
    val newP1 = initialisePlayer(p1)
    val newP2 = initialisePlayer(p2)
    val listP = round(newP1,newP2)
    Helpers.clear
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

    /**
      * This function allows to start the game AI vs AI during nbGlobal games and return the list of players 
      * @param p The first player (it is the first player to play)
      * @param p2 The second player
      * @param nbRound Number of current round
      * @return list of player after nbGlobal games
      */
  @tailrec
  def gameAIVsAI(p1 : Player, p2 : Player, nbRound : Int, nbGlobal : Int) : List[Player] = {
    val newP1 = initialisePlayer(p1)
    val newP2 = initialisePlayer(p2)
    val listP = round(newP1,newP2)
    (nbRound == nbGlobal) match {
      case true => {
        println("Game : "+newP1.name+" VS "+newP2.name)
        println("----------")
        println(listP(0).name+" win "+listP(0).score+" games !")
        println(listP(1).name+" win "+listP(1).score+" games !")
        println()
        return listP
      }
      case false => {
          val p1Reset = listP(0).reset()
          val p2Reset = listP(1).reset()
          gameAIVsAI(p2Reset,p1Reset,nbRound+1,nbGlobal)
      }
    }
  }
    
  

  /**
      * This function return both players after one player win 
      * @param p The first player (it is the player who play the round)
      * @param p2 The second player
      * @return List(Player) Both players
      */
  @tailrec
  def round(p1 : Player, p2 : Player) : List[Player] = {
    if (!p1.isDead) {
      if (!p2.isDead) {
        if (p1.isAI == false) {
          Helpers.clear
          println("It's "+p1.name+ "'s turn :")
          println("Grid with your ships and shot's opponent (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShipsAndShotsOpposing(0,0,p1,p2)
          println()
          println("Grid with your shoots (O --> Good / X --> Bad) :")
          Grid.printGrilleMyShots(0,0,p1)
        }
        //val coordinate = Helpers.enterCoordinate
        val coordinate = Helpers.coordinateToShot(p1)
        val res = p2.isContainedInOneShip(coordinate)
        res match {
          case None => {
            val newP1 = p1.addShot(coordinate, false)
            if (p1.isAI == false) {
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
              if (p1.isAI == false) {
                println()
                println("You sank my "+newShip.name+" !!!")
                println("Press any key to continue")
                readLine
              }
              round(newP2, newP1)
            } else {
              val indexP2 = p2.removeShip(ship)
              val newP2 = indexP2.addShip(newShip)
              if (p1.isAI == false) {
                println()
                println("It's a hit !!!")
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

  /**
      * This function return the new player, the same player but with the list of ships (all ships are created)  
      * @param p The player who should be initialized
      * @return Player The new player with all ships initialized
      */
  def initialisePlayer(p : Player) : Player = {
    val listShip = List(Ship("destroyer",2,Nil),Ship("submarine",3,Nil),Ship("cruiser",3,Nil),Ship("battleShip",4,Nil),Ship("carrier",5,Nil))
    if (p.isAI == false) {
      Helpers.clear
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
      if(p.isAI == false) {
        println("Initialization Player, "+p.name+", finish !")
        println("Press any key to continue")
        readLine
      }
      return p
    } else {
      if(p.isAI == false) {
        Grid.printGrilleMyShips(0,0,p)
        println("Create "+listShip.head.name+"(size = "+listShip.head.size+") (Player : "+p.name+")")
        println()
      }
      val coordinate = Helpers.coordinateToPlaceShip(p)
      val direction = Helpers.directionToPlaceShip(p)
      val ship = p.createShip(listShip.head.name,listShip.head.size,coordinate,direction)
      ship match {
        case Some(s) => {
          val newP = p.addShip(ship.get)
          if (p.isAI == false) {
            Helpers.clear
            println(listShip.head.name+"(size = "+listShip.head.size+") create !")
            println("----------")
          }
          createFleet(newP,listShip.tail) 
        }
        case None => {
          if (p.isAI == false) {
            Helpers.clear
            println("Bad Ship ! No create !")
            println("----------")
          }
          createFleet(p,listShip)
        }
      }
    }
  }

}