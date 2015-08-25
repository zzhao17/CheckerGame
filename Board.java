/*Board.java*/

/**
 * Represents a Board configuration of a game of Checkers61bl
 * @author
 */

public class Board {

  /**
   *  Define any variables associated with a Board object here.  These
   *  variables MUST be private.
   */
    private Piece[][] pieces;
    private int board_side;
    private int board_active;
    private boolean can_end = false;
  /**
   * Constructs a new Board
   * @param  shouldBeEmpty if true, add no pieces
   * 
   */
    public Board(){
  	  pieces = new Piece[8][8];
  	  board_side = 0;
  	  board_active = 0;
  	  for (int i = 0; i < 8; i++) {
  		  for (int j = 0; j < 8; j++) {
                if (j < 4) pieces[i][j] = new Piece(0, this);
                else pieces[i][j] = new Piece(1, this);
            }
        }
    }
    
  public Board(int s){
	  pieces = new Piece[8][8];
	  board_side = 0;
	  board_active = 0;
	  for (int i = 0; i < 8; i++) {
		  for (int j = 0; j < 8; j++) {
              pieces[i][j] = new Piece(s, this);
              pieces[i][j].crowned();
          }
      }
  }
  
  
  public Board(boolean shouldBeEmpty) {
	  pieces = new Piece[8][8];
	  board_side = 0;
	  board_active = 0;
	  for (int i = 0; i < 8; i++) {
		  for (int j = 0; j < 8; j++) {
              pieces[i][j] = new Piece(-1, this);
          }
        }
          if (!shouldBeEmpty){
              for (int c = 0; c < 8; c += 2){
            	  pieces[c][0] = new Piece(0, this);
            	  pieces[c+1][7] = new Piece(1, this);
            	  pieces[c+1][1] = new ShieldPiece(0, this);
            	  pieces[c][2] = new BombPiece(0, this);
            	  pieces[c+1][5] = new BombPiece(1, this);
            	  pieces[c][6] = new ShieldPiece(1, this);
              }
          }
	  }    
  public void change_b_side(){
	  if (board_side == 0) board_side = 1;
	  else board_side = 0;
  }
  public void b_fire(){
	  this.board_side = 0;
  }
  public void b_water(){
	  this.board_side = 1;
  }
  public void b_status_reset(){
	  this.board_active = 0;
  }
private void drawBoard() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) {
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        } else {
          StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
        }
        if (pieces[i][j].active() == 1) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.picture(i + .5, j + .5, pieces[i][j].patrn(), 1, 1);
      }
    }
}

  /**
   * gets the Piece at coordinates (x, y)
   * @param  x X-coordinate of Piece to get
   * @param  y Y-coordinate of Piece to get
   * @return   the Piece at (x, y)
   */
  public Piece pieceAt(int x, int y) {
    // YOUR CODE HERE
	return pieces[x][y];  
  }

  /**
   * Places a Piece at coordinate (x, y)
   * @param p Piece to place
   * @param x X coordinate of Piece to place
   * @param y Y coordinate of Piece to place
   */
  public void place(Piece p, int x, int y) {
    // YOUR CODE HERE
	  pieces[x][y] = p;
	  drawBoard();
  }

  /**
   * Removes a Piece at coordinate (x, y)
   * @param  x X coordinate of Piece to remove
   * @param  y Y coordinate of Piece to remove
   * @return   Piece that was removed
   */
  public Piece remove(int x, int y) {
    // YOUR CODE HERE
	  pieces[x][y] = new Piece(-1, this);
	  drawBoard();
	  return pieces[x][y];
  }

  /**
   * Determines if a Piece can be selected
   * @param  x X coordinate of Piece
   * @param  y Y coordinate of Piece to select
   * @return   true if the Piece can be selected
   */
  public boolean canSelect(int x, int y) {
    // YOUR CODE HERE
	  boolean can;
	  if (board_active == 0){
		  can = (pieces[x][y].side() == board_side);
	  } 
	  else{
		  int i = 0;
		  int j = 0;
		  outerloop:
		  for (i = 0; i < 8; i++) {
			  for (j = 0; j < 8; j++) {
				  if (pieces[i][j].active() == 1) {
					  break outerloop;
				  }
			  }
		  }
		  if (pieces[i][j].isKing()) {
		      can = (Math.abs(x-i) == 1 && Math.abs(y-j) == 1 && 
				     pieces[i][j].capture() == 0);
		      if (Math.abs(x-i) == 2 && Math.abs(y-j) == 2){
			      if ((pieces[(x+i)/2][(y+j)/2].side() != pieces[i][j].side())
					      && (pieces[(x+i)/2][(y+j)/2].side() != -1)){
				      can = true;
				      pieces[i][j].startCapturing();
				      drawBoard();
			      }
			      else can = false;
		      }
		  }
		  else {
			  if (pieces[i][j].side() == 0) {
				  can = (Math.abs(x-i) == 1 && y-j == 1 && pieces[i][j].capture() == 0);
				  if (Math.abs(x-i) == 2 && y-j == 2){
				      if ((pieces[(x+i)/2][(y+j)/2].side() != pieces[i][j].side())
						      && (pieces[(x+i)/2][(y+j)/2].side() != -1)){
					      can = true;
					      pieces[i][j].startCapturing();
					      drawBoard();
				      }
				      else can = false;
			      } 
			  }
			  else {
				  can = (Math.abs(x-i) == 1 && j-y == 1 && pieces[i][j].capture() == 0);
				  if (Math.abs(x-i) == 2 && (y-j) == -2){
				      if ((pieces[(x+i)/2][(y+j)/2].side() != pieces[i][j].side())
						      && (pieces[(x+i)/2][(y+j)/2].side() != -1)){
					      can = true;
					      pieces[i][j].startCapturing();
					      drawBoard();
				      }
				      else can = false;
			      }
			  }
		  }
	  }
	  return can;
  }

  /**
   * Selects a square. If no Piece is active, selects the Piece and
   * makes it active. If a Piece is active, performs a move if an empty
   * place is selected. Else, allows you to reselect Pieces
   * @param x X coordinate of place to select
   * @param y Y coordinate of place to select
   */
  public void select(int x, int y) {
    // YOUR CODE HERE
	  if(canSelect(x,y)){
	      if (board_active == 0) {
		      pieces[x][y].turn_on();
		      board_active = 1;
		      drawBoard();
	      } else{
		      int i = 0;
		      int j = 0;
		      outerloop:
		      for (i = 0; i < 8; i++) {
			      for (j = 0; j < 8; j++) {
				      if (pieces[i][j].active() == 1) {
					      break outerloop;
				      }
			      }
		      }
		      move(pieces[i][j], i, j, x, y);
	      }
	  }
	  else {
		  if (board_active == 1){
			  board_active = 0;
			  int i = 0;
		      int j = 0;
		      for (i = 0; i < 8; i++) {
			      for (j = 0; j < 8; j++) {
				      if (pieces[i][j].active() == 1) {
					      pieces[i][j].reset_act();
					      pieces[i][j].finishCapturing();
				      }
			      }
		      }
		      drawBoard(); 
		  }
	  }
  }

  /**
   * Moves the active piece to coordinate (x, y)
   * @param p Piece to move
   * @param x1 Original X coordinate of p
   * @param y1 Origin Y coordinate of p
   * @param x X coordinate to move to
   * @param y Y coordinate to move to
   */
  public void move(Piece p, int x1, int y1, int x2, int y2) {
    // YOUR CODE HERE
	  int if_blow = pieces[x1][y1].capture();
	  int sideOfPiece = pieces[x1][y1].side();
	  if (pieces[x1][y1].capture() == 0){
	      pieces[x1][y1].reset_act();
	      board_active = 0;
	      drawBoard();
	  }
	  if (pieceAt(x2, y2).side() == -1){
	      place(p, x2, y2);
	      remove(x1, y1); 
	      drawBoard();
	      if (if_blow == 1){
	    	  pieces[x2][y2].explode(x2, y2);
	    	  remove((x1+x2)/2, (y1+y2)/2);
	          drawBoard();
	          }
	      if ((sideOfPiece == 0 && y2 == 7) || (sideOfPiece == 1 
	    		  && y2 == 0)) {
	    	  pieces[x2][y2].crowned();
	    	  drawBoard();
	      }
	      can_end = true;
	  }
  }

  /**
   * Determines if the turn can end
   * @return true if the turn can end
   */
  public boolean canEndTurn() {
    // YOUR CODE HERE
	  return can_end;
  }

  /**
   * Ends the current turn. Changes the player.
   */
  public void endTurn() {
    // YOUR CODE HERE
	  change_b_side();
	  board_active = 0;
	  can_end = false;
	  int i = 0;
      int j = 0;
      for (i = 0; i < 8; i++) {
	      for (j = 0; j < 8; j++) {
		      if (pieces[i][j].active() == 1) {
			      pieces[i][j].reset_act();
		      }
	      }
      }
	  drawBoard();
  }

  /**
   * Returns the winner of the game
   * @return The winner of this game
   */
  public String winner() {
    // YOUR CODE HERE
	  String winner = null;
	  int water_num = 0;
	  int fire_num = 0;
	  for (int i = 0; i < 8; i++){
		  for(int j = 0; j < 8; j++){
			  if(pieces[i][j].side() == 0) fire_num++;
			  else if(pieces[i][j].side() == 1) water_num++;
		  }
	  }
	  if (fire_num == 0 && water_num > 0) winner = "Water won!";
	  else if (fire_num > 0 && water_num == 0) winner = "Fire won!";
	  else if (fire_num == 0 && water_num == 0) winner = "Tie!";
	  return winner;
  }

  /**
   * Starts a game
   */
  public static void main(String[] args) {
    // YOUR CODE HERE
	  StdDrawPlus.setScale(0, 8);
      Board b = new Board(false);
      b.drawBoard();
      boolean game = true;

      while (game) {
    	  try{
              b.drawBoard();
              if (StdDrawPlus.mousePressed()) {
                  int x = (int) StdDrawPlus.mouseX();
                  int y = (int) StdDrawPlus.mouseY();
                  b.canSelect(x, y);
                  b.select(x, y);
                  if (b.winner() != null){
                	  game = false;
                	  if (b.winner().equals("Water won!")) b = new Board(1);
                	  else if (b.winner().equals("Fire won!")) b = new Board(0);
                	  else if (b.winner().equals("Tie!")) b = new Board();
                	  b.drawBoard();
                  }
              }
              if (StdDrawPlus.isSpacePressed()) {
                  if (b.canEndTurn()) b.endTurn();
              }
              StdDrawPlus.show(10);
           }
           catch (IndexOutOfBoundsException e){}
      }
  }
  
  /*public void board_status() {
	  for (int i = 0; i < 8; i++) {
		  for (int j = 0; j < 8; j++){
			  String p = "";
			  if (pieces[i][j].patrn().equals("img/bomb-fire.png")) p = "bf";
			  else if (pieces[i][j].patrn().equals("img/pawn-fire.png")) p = "pf";
			  else if (pieces[i][j].patrn().equals("img/shield-fire.png")) p = "sf";
			  else if (pieces[i][j].patrn().equals("img/bomb-water.png")) p = "bw";
			  else if (pieces[i][j].patrn().equals("img/pawn-water.png")) p = "pw";
			  else if (pieces[i][j].patrn().equals("img/shield-water.png")) p = "sw";
			  String crn = "";
			  if (pieces[i][j].isKing()) crn = "ik";
			  else crn = "nk";
			  System.out.print(p + " s" + pieces[i][j].side() + " a" + pieces[i][j].active() + 
					  " c" + pieces[i][j].capture() + " "+ crn + "\t");
		  }
		  System.out.println();
	  }
  }*/
}