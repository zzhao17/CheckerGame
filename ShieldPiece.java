/*ShieldPiece.java*/

/**
 * Represents a ShieldPiece in Checkers61bl
 * @author 
 */

public class ShieldPiece extends Piece {

  /**
   *  Define any variables associated with a ShieldPiece object here.  These
   *  variables MUST be private or package private.
   */
	
  private String patrn;  
  /**
   * Constructs a new ShieldPiece
   * @param  side what side this ShieldPiece is on
   * @param  b    Board that this ShieldPiece belongs to
   */
  public ShieldPiece(int s, Board b) {
	  super(s, b);
	  if (super.side() == 0) {
		  patrn = "img/shield-fire.png";
	  }
	  else if (super.side() == 1) {
		  patrn = "img/shield-water.png";
		  }
  }
  public void crowned() {
	  super.kinged();
	  if (super.side() == 0) patrn = "img/shield-fire-crowned.png";
	  else if (super.side() == 1) patrn = "img/shield-water-crowned.png";
  }
  public String patrn() {
	  return patrn;
  }

}