# BattleShip_IG5
It is a simple battleship game, developed in Scala during my 5th year at Polytech Montpellier.  
  I used Scala (v2.12.6), SBT and ScalaTest.
## Installation
First, this project requires the least scala version (v2.12.16) and SBT. TO install this, go to on their [official website](https://www.scala-lang.org/download/)
  Then, clone this project, go to in the directory "battleShip_Project" and enter the command "sbt". After, you should be to compile the project with this command "compile" and start the project with this command "run".

## Test AI
This project use different AI (low, medium and hard). In order to check if hard > medium > low, it is possible to launch multiple games between each AI (default : 100 games).
  For example, I got the following results with 50 000 games played between each AI (low VS medium, low VS hard, medium VS hard)
  
|   | Score AI 1 | Score AI 2 |
| ------------- | ------------- | ------------ |
| AI Lower / AI Medium  | 3071  | 46929 |
| AI Lower / AI Hard  | 1087  | 48913 |
| AI Medium / AI Hard  | 7397  | 42603 |