# BattleShip_IG5
It is a simple battleship game, developed in functional programming (with Scala) during my 5th year at Polytech Montpellier.   
I used Scala (v2.12.6), SBT and ScalaTest.
## Installation
You also need to have scala and sbt installed on your computer. You can find a tutorial to install scala and sbt on their [official website](https://www.scala-lang.org/download/).

Then, to set up and run this project, you need to follow this instruction step by step :  
* step 1 : go to in the directory “battleShip_project” with the following command line : cd battleShip_project  
* step 2 : run sbt with the following command line : sbt  
* step 3 : compile the project with the following command line : compile  
* step 4 : run the project with the following command line : run
## Test AI
This project use different AI (low, medium and hard). In order to check if hard > medium > low, it is possible to launch multiple games between each AI (default : 100 games).
  For example, I got the following results with 50 000 games played between each AI (low VS medium, low VS hard, medium VS hard)
  
|   | Score AI 1 | Score AI 2 |
| ------------- | ------------- | ------------ |
| AI Lower / AI Medium  | 3071  | 46929 |
| AI Lower / AI Hard  | 1087  | 48913 |
| AI Medium / AI Hard  | 7397  | 42603 |