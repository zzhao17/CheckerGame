# CheckerGame

The content of this README file:
  + The content of this project
  + How to run CheckerGame
  + CheckerGame rules
  + Known bugs
  + Authers
  + Lisence 



* The content of this project:
  Board.Java   
  Piece.Java 
  BombPiece.Java
  ShieldPiece.Java
  stdDrawPlus.Java
  img

* How to run CheckerGame:
  To run this program, simply clone all the files listed to local direcotry, open a java IDE and choose the the local direcotry as 
  the working space.

* CheckerGame rules:
  The board is an 8x8 board, with water and fire pieces on the top and bottom. Each player starts with pieces in the three rows closest to them. The front, middle-most row consists of Bomb Pieces, the second row consists of Shield Pieces, and the back, edge-most row consists of normal pieces. Only every other space on the Board is used, and all pieces can only move and capture diagonally. If a capture move is available, the player is not required to capture. The bottom left corner should have a "black" square, and should contain a normal fire piece.

  Movement of pieces are the same as classic Checkers, pieces can only move one square diagonally. In all new games, fire team makes the first move. Besides king pieces, fire pieces always move upwards (like flames) and water pieces always move downwards (like rain). Upon reaching the topmost row, fire pieces become "kinged" and are allowed to move not only diagonally forwards, but also diagonally backwards. The same can be said for water pieces that reach the bottommost row. Capturing a piece works exactly the same as in classic Checkers, by jumping over an opponent's piece. You may perform multi-captures like in classic Checkers.

  >>> Normal Piece
  Moves diagonally, and captures diagonally. Normal pieces can multi-capture, meaning if performing a capture lands the piece in a position ready to perform another capture, that piece may choose to do perform another capture in the same turn.

  >>> Bomb Piece
  A normal Checkers piece with a twist. Performing a capture with a bomb piece causes an explosion in the destination landing. Explosions kill all pieces adjacent to the landing (pieces within a 3x3 block centered at the bomb's final position), as well as the exploding bomb. Howevever shield pieces will not be killed by a bomb explosion. Bombs do not discriminate; all non-shield pieces in the blast zone, regardless of if they are water or fire, are killed in a bomb explosion.

  Chain reaction explosions do not occur. If one bomb destroys another bomb via explosion, a second explosion does not occur. Bomb pieces cannot perform multi-captures, because they explode after the first capture.

  >>> Shield Piece
  A normal checkers piece, except that it cannot be killed by bomb explosions. However, they can still be normally captured by any piece (including bombs).

  >>> King Piece
  Any piece can become "Kinged" upon reaching the furthest row from its origin side. King pieces can move and capture in four directions instead of only two. A piece may capture, promote to a king, and capture again in the same turn if the orientation of the Board permits.

* Known bugs:
  Depending on how quickly the players press the mouse, the program might not be able to capture the pressing. To minimize this problem, try pressing mouse as fast as possible or simply just press multiple times until the program grasp the motion. 

* Authers:

  Zhan Zhao: 
  >>> zzhao17@syr.edu
  Provided code for Board.java, Piece.java, ShieldPiece.java and BombPiece.java.

  Joseph Moghadam: 
  >>> jmoghadam (at) berkeley.edu
  Shortened/modified version of the original Std.Java. For the original, see: http://introcs.cs.princeton.edu/java/stdlib/StdDrawPlus.java.html.

 * License:
   Open source

(See the original project description: http://cs61bl.github.io/materials/proj/proj1/proj1.html)