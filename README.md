# BattleShip_IG5
It is a simple battleship game, developed in Scala during my 5th year at Polytech Montpellier.  
  I used Scala (v2.12.6), SBT and ScalaTest.
## How to use it ?
//todo
## Test AI
This project use different AI (low, medium and hard). In order to check if hard > medium > low, it is possible to launch multiple games between each AI (default : 100 games).
  For example, I got the following results with 20 000 games played between each AI (low VS medium, low VS hard, medium VS hard)
  
|   | Score AI 1 | Score AI 2 |
| ------------- | ------------- | ------------ |
| AI Lower / AI Medium  | 3  | 19997 |
| AI Lower / AI Hard  | 0  | 20000 |
| AI Medium / AI Hard  | 2798  | 17202 |