/*BombPiece.java*/

/**
 *  Represents a BombPiece ins Checkers61bl
 * @author 
 */

public class BombPiece extends Piece {
 
  /**
   *  Define any variables associated with a BombPiece object here.  These
   *  variables MUST be private or package private.
   */
    private String patrn;
  /**
   * Constructs a new BombPiece
   * @param  side what side this BombPiece is on
   * @param  b    Board that this BombPiece belongs to
   */
  public BombPiece(int s, Board b) {
	  super(s, b);
	  if (super.side() == 0) {
		  patrn = "img/bomb-fire.png";
		  if (super.isKing()) patrn = "img/bomb-fire-king.png";

	  }
	  else if (super.side() == 1) {
		  patrn = "img/bomb-water.png";
		  if (super.isKing()) patrn = "img/bomb-fire-king.png";
	  }
  }
  public void crowned() {
	  super.kinged();
	  if (super.side() == 0) patrn = "img/bomb-fire-crowned.png";
	  else if (super.side() == 1) patrn = "img/bomb-water-crowned.png";
  }
  
  public String patrn() {
	  return patrn;
  }

}
