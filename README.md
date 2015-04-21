Scalac chess solver

**Problem**

The problem is to find all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N where none of the pieces is in a position to take any of the others. Assume the colour of the piece does not matter, and that there are no pawns among the pieces.


Write a program which takes as input:

- The dimensions of the board: M, N
- The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the board.

As output, the program should list all the unique configurations to the console for which all of the pieces can be placed on the board without threatening each other.
When returning your solution, please provide with your answer the total number of unique configurations for a **7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight. Also provide the time it took to get the final score. Needless to say, the lower the time, the better.**

**This solution**

We sort the provided figures by *influence*(how many fields it can threaten in comparison to other figures). The order is:

- Queen
- Bishop
- Rook
- Knight
- King

We take the next strongest figure and put it on all possible places on the field. First iteration will generate 49 boards with a queen on each field. Those board will have only fields available which the queen doesn't threaten.
For each of these 49 we put the next figure - the queen again - each safe place for each of those boards.

If the fields or currently placed figure would put the board in a conflicted state, we discard it.

**How to run**

Preferably, first run *sbt assembly* to create a jar. The run the jar using *java -jar scalac-chess-solver-assembly-1.0.jar*.

First 2 orguments are width and height of the board.
After that you list all pieces.
For example for the target solution your call

*java -jar scalac-chess-solver-assembly-1.0.jar 7 7 k k q q b b n*.

If no args will be provided, it will attempt to run the target example by default.


