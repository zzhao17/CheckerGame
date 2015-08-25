/*Piece.java*/

/**
 *  Represents a Normal Piece in Checkers61bl
 * @author 
 */

public class Piece {
  
  /**
   *  Define any variables associated with a Piece object here.  These
   *  variables MUST be private or package private.
   */
	private int side;
	private boolean isKing;
	private Board brd;
	private String patrn;
	private int active;
	private int capture;
	private boolean has_capture = false; 
  /**
   * Returns the side that the piece is on
   * @return 0 if the piece is fire and 1 if the piece is water
   */
  public int side() {
    //YOUR CODE HERE
    return side;
  }

  public boolean isKing() {
    //YOUR CODE HERE
	  return isKing;
  }
  
  public void crowned() {
	  isKing = true;
	  if (side == 0) patrn = "img/pawn-fire-crowned.png";
	  else if (side == 1) patrn = "img/pawn-water-crowned.png";
  }
  
  public void kinged() {
	  isKing = true;
  }
  
  public String patrn() {
	  return patrn;
  }
  
  public int active(){
	  return active;
  }
  
  public void change_active(){
	  if (active == 0) active = 1;
	  else active = 0;
  }
  
  public void reset_act(){
	  active = 0;
  }
  
  public void turn_on(){
	  active = 1;
  }
  /**
   * Initializes a Piece
   * @param  side The side of the Piece
   * @param  b    The Board the Piece is on
   */
  Piece(int s, Board b) {
    //YOUR CODE HERE
	  side = s;
	  isKing = false;
	  brd = b;
	  active = 0;
	  capture = 0;
	  if (side == 0) patrn = "img/pawn-fire.png";
	  else if (side == 1) patrn = "img/pawn-water.png";
	  else patrn = "";

  }

  /**
   * Destroys the piece at x, y. ShieldPieces do not blow up
   * @param x The x position of Piece to destroy
   * @param y The y position of Piece to destroy
   */
  void blowUp(int x, int y) {
    //YOUR CODE HERE
	  String patn = brd.pieceAt(x, y).patrn();
	  if (!(patn.equals("img/shield-water.png")||patn.equals("img/shield-fire.png")||
			  patn.equals("img/shield-fire-crowned.png")||
			  patn.equals("img/shield-water-crowned.png"))) brd.remove(x, y); 
  }

  /**
   * Does nothing. For bombs, destroys pieces adjacent to it
   * @param x The x position of the Piece that will explode
   * @param y The y position of the Piece that will explode
   */
  void explode(int x, int y) {
    //YOUR CODE HERE
	  String patn = brd.pieceAt(x, y).patrn();
	  if (patn.equals("img/bomb-water.png")||patn.equals("img/bomb-fire.png")||
			  patn.equals("img/bomb-fire-crowned.png")||
			  patn.equals("img/bomb-water-crowned.png")) {
		  blowUp(x, y);
	      if (x < 7 && x > 0 && y < 7 && y > 0){
		      blowUp(x+1, y+1);
		      blowUp(x-1, y+1);
		      blowUp(x-1, y-1);
		      blowUp(x+1, y-1);
	      }
	      else {
	    	  if (x == 0) {
	    		  blowUp(1, y+1);
	    		  if (y != 0) blowUp(1, y-1);
	    	  }		  
	    	  else if (x == 7) {
	    		  blowUp(6, y-1);
	    		  if (y != 7) blowUp(6, y+1);
	    	  }
	    	  else if (y == 0) {
	    		  blowUp(x-1, 1);
	    		  blowUp(x+1, 1);
	    	  }
	    	  else if ( y == 7) {
	    		  blowUp(x-1, 6);
	    		  blowUp(x+1, 6);
	    	  }
	      }
	  }
  }

  /**
   * Signals that this Piece has begun to capture (as in it captured a Piece)
   */
  public void startCapturing() {
    //YOUR CODE HERE
	  capture = 1;
	  has_capture = true;
  }
  
  public int capture(){
	  return capture;
  }
  /**
   * Returns whether or not this piece has captured this turn
   * @return true if the Piece has captured
   */
  public boolean hasCaptured() {
    //YOUR CODE HERE
	  return has_capture;
  }

  /**
   * Resets the Piece for future turns
   */
  public void finishCapturing() {
    //YOUR CODE HERE
	  capture = 0;
  }
  public String toString(){
	  return this.side() + "";
  }

}