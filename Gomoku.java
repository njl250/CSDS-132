import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;

/**
 * A class to represent the game Gomoku
 * The Objective is to get exactly five in a row if playing on a 19x19 grid
 * Can change the size of the grid which alters the rules of the game
 * @author Noah Leibowitz
 */
public class Gomoku extends Application {
  
  /** Stores the button references as a 2D array */
  private Button[][] gameButtons;
  
  /** Keeps track of which players goes next: true = black, false = white */
  private boolean blackTurn = true;
  
  /** Stores the current state of the game */
  private boolean gameOver = false;
  
  /** Stores the number needed in a row to win */
  private int lengthToWin = 5;
  
  /** Stores the number of rows to be in the grid */
  private int rows = 19;
  
  /** Stores the number of columns to be in the grid */
  private int cols = 19;
  
  /** Stores the count of black pieces for FourFour */
  private int blackCountFourFour;
  
  /** Stores the count of white pieces for FourFour */
  private int whiteCountFourFour;
  
  /** Stores the count of black pieces for ThreeThree */
  private int blackCountThreeThree;
  
  /** Stores the count of white pieces for ThreeThree */
  private int whiteCountThreeThree;
  
  /** Stores the label that displays either a welcome message or who won */
  private static Label message;
  
  /**
   * Getter method for gameButtons
   * @return gameButtons the button references as a 2D-array
   */
  public Button[][] getGameButtons() {
    return gameButtons;
  }
  
  /**
   * Getter method for blackTurn
   * @return the current player
   */
  public boolean isBlackTurn() {
    return blackTurn;
  }
  
  /**
   * Setter method that sets the next player
   * @param blackTurn the previous player
   */
  public void switchTurn(boolean blackTurn) {
    this.blackTurn = blackTurn;
  }
  
  /**
   * Getter method for gameOver
   * @return the state of the game
   */
  public boolean isGameOver() {
    return gameOver;
  }
  
  /**
   * Setter method that sets the new state of the game
   * @param gameOver the state of the game
   */
  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
  
  /**
   * Getter method for lengthToWin
   * @return the length to win based on the board size
   */
  public int getLengthToWin() {
    return lengthToWin;
  }
  
  /**
   * Getter method for rows
   * @return the amount of rows on the board
   */
  public int getRows() {
    return rows;
  }
  
  /**
   * Getter methods for cols
   * @return the amount of cols on the board
   */
  public int getCols() {
    return cols;
  }
  
  /**
   * Getter method for blackCountFourFour
   * @return the amount of distinct groups of four with the color black
   */
  public int getBlackCountFourFour() {
    return blackCountFourFour;
  }
  
  /**
   * Setter method for blackCountFourFour
   * @param blackCountFourFour the amount of distinct groups of four with the color black
   */
  public void setBlackCountFourFour(int blackCountFourFour) {
    this.blackCountFourFour = blackCountFourFour;
  }
  
  /**
   * Getter method for whiteCountFourFour
   * @return the amount of distinct groups of four with the color white
   */
  public int getWhiteCountFourFour() {
    return whiteCountFourFour;
  }
  
  /**
   * Setter method for whiteCountFourFour
   * @param whiteCountFourFour the amount of distinct groups of four with the color white
   */
  public void setWhiteCountFourFour(int whiteCountFourFour) {
    this.whiteCountFourFour = whiteCountFourFour;
  }
  
  /**
   * Getter method for blackCountThreeThree
   * @return the amount of distinct groups of three with empty spaces and the color black
   */
  public int getBlackCountThreeThree() {
    return blackCountThreeThree;
  }
  
  /**
   * Setter method for blackCountThreeThree
   * @param blackCountThreeThree the amount of distinct groups of three with empty spaces and the color black
   */
  public void setBlackCountThreeThree(int blackCountThreeThree) {
    this.blackCountThreeThree = blackCountThreeThree;
  }
  
    /**
   * Getter method for whiteCountThreeThree
   * @return the amount of distinct groups of three with empty spaces and the color white
   */
  public int getWhiteCountThreeThree() {
    return whiteCountThreeThree;
  }
  
  /**
   * Setter method for whiteCountThreeThree
   * @param whiteCountThreeThree the amount of distinct groups of three with empty spaces and the color white
   */
  public void setWhiteCountThreeThree(int whiteCountThreeThree) {
    this.whiteCountThreeThree = whiteCountThreeThree;
  }
  
  /**
   * Getter method for the message
   * @return the message
   */
  public Label getMessage() {
    return message;
  }
  
  /**
   * Setter method for the message
   * @param message the message to be written
   */
  public void setMessage(Label message) {
    this.message = message;
  }
  
  /**
   * Sets up the JavaFX GUI to create the Gomoku board
   *@param primaryStage the window of the GUI
   */
  public void start(Stage primaryStage) {
    
    // Passes the command line arguments into the start() method
    List<String> args = getParameters().getRaw();
    
    try {
      if (args.size() == 1) {
        rows = 19;
        cols = 19;
        
        // The length to win is the first argument passed in
        lengthToWin = Integer.parseInt(args.get(0));
        
        // The length to win must be at least 5
        if (lengthToWin < 5) {
          System.out.println("The length to win must be at least 5 in a row.");
          return;
        }
      }
      else if (args.size() == 2) {
        
        // If two arguments present, the first is the rows of the board
        rows = Integer.parseInt(args.get(0));
        
        // The second argument is the columns of the board
        cols = Integer.parseInt(args.get(1));
        lengthToWin = 5;
        
        // The size of the board must be at least 5x5
        if (rows < 5 || cols < 5) {
          System.out.println("The size of the board must be at least 5x5.");
          return;
        }
      }
      else if (args.size() == 3) {
        
        // First argument is the length to win
        lengthToWin = Integer.parseInt(args.get(0));
        
        // Second argument is the rows of the board
        rows = Integer.parseInt(args.get(1));
        
        // Third argument is the columns of the board
        cols = Integer.parseInt(args.get(2));
        
        /* The length to win must be at least 5
         * The size of the board must be at least 5x5 */
        if (rows < 5 || cols < 5 || lengthToWin < 5) {
          System.out.println("Board size and length to win must be at least 5.");
          return;
        }
      }
    }
    
    // If the user inputs something other than integers
    catch (NumberFormatException e) {
      System.out.println("Invalid input type used. Please enter valid integers or use the default board size and length to win.");
    }
    
    // Sets the layout of the board using a grid pane
    GridPane grid = new GridPane();
    
    // Adds an array of buttons to each box on the grid
    gameButtons = new Button[rows][cols];
    
    
    /* Creating the grid of buttons
     * Loops through each row and each column
     */
    for (int row = 0; row < rows; row ++) {
      for (int col = 0; col < cols; col ++) {
        
        // Adds a button to each box on the grid
        Button b = new Button();
        
        // Assigns each button to the array of game buttons
        gameButtons[row][col] = b;
        fillSquare(b);
        grid.add(b, col, row);
        b.setOnAction(e -> {
          if (gameOver)
            return;
          
          // Gets which button was clicked by the user
          Button b1 = (Button)e.getSource();
          
          // Stores the horizontal position of the piece being placed
          int rowIndex = GridPane.getRowIndex(b1);
          
          // Stores the vertical position of the piece being placed
          int colIndex = GridPane.getColumnIndex(b1);
          
          // Checks to make sure the box is empty
          if (isEmpty(b1)) {
            if (blackTurn) {
              placeCircle(b1, Color.BLACK);
              fillSquare(b1);
              if (violatesThreeThree(rowIndex, colIndex)) {
                return;
              }
              if (violatesFourFour(rowIndex, colIndex)) {
                return;
              }
            }
          else {
            placeCircle(b1, Color.WHITE);
            fillSquare(b1);
            if (violatesThreeThree(rowIndex, colIndex)) {
              return;
            }
            if (violatesFourFour(rowIndex, colIndex)) {
              return;
            }
          }
          whoWon(rowIndex, colIndex);
          switchTurn(!blackTurn);
        }
      });
    }
  }
    Button playAgain = new Button("Play Again!");
    grid.add(playAgain, 0, getRows(), getCols(), 1);
    playAgain.setOnAction(e -> resetGomoku());
    
    // Adds a label to display the welcome message on the board
    setMessage(new Label("Welcome! Let's play."));
    getMessage().setFont(new Font(24));
    grid.add(getMessage(), getCols() / 2, getRows(), getCols(), getCols());
    grid.setAlignment(Pos.BOTTOM_CENTER);
    
    // Adds the grid pane to the scene
    Scene scene = new Scene(grid);
    
    // Set the title of the primary stage to show the grid size as well as the amount needed to win
    primaryStage.setTitle("Gomoku " + getRows() + "x" + getCols() + " | " + getLengthToWin() + " needed to win.");
    
    // Adds the scene to the window
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Checks which player won the game
   * @param row the row position of this piece placed
   * @param col the column position of this piece placed
   */
  public void whoWon(int row, int col) {
    Label winner;
    if (hasFiveInLine(row, col)) {
      if (isBlackTurn()) {
        getMessage().setText("Black won!");;
      }
      else {
        getMessage().setText("White won!");;
      }
      setGameOver(true);
    }
  }
  
  /**
   * Creates the style of the button to represent a green square
   *@param button the button to fill with green
   */
  public void fillSquare(Button button) {
    button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), new Insets(1))));
    button.setPrefSize(50, 50);
  }
  
  /**
   * Places either a black or white circle on the button
   * @param button the button where this circle goes
   * @param color the color of the piece (black or white)
   */
  public void placeCircle(Button button, Color color) {
    
    // Creates the piece to be placed as a circle of appropriate size
    Circle circle = new Circle(16);
    circle.setFill(color);
    button.setGraphic(circle);
  }
  
  /**
   * Determines whether the button clicked has already been occupied
   * @param button the button clicked by the player
   * @return true if the button is empty, false if occupied
   */
  public boolean isEmpty(Button button) {
    return button.getGraphic() == null;
  }
  
  /**
   * Returns the color of the piece placed
   * @param button the button the piece is placed on
   * @return the color of the piece placed
   */
  public Color getPieceColor(Button button) {
    if (button.getGraphic() instanceof Circle) {
      Circle c = (Circle) button.getGraphic();
      return (Color) c.getFill();
    }
    return null;
  }
  
  /**
   * Checks if a move creates five pieces in a row
   * Includes the placement of this piece
   * The pieces can be arranged horizontally, vertically, or diagonally
   * More than five is not a win and the game continues
   * @param row the row coordinate of the player's position
   * @param col the column coordinate of the player's position
   * @return true if exactly 5 pieces placed in a row, otherwise false
   */
  public boolean hasFiveInLine(int row, int col) {
    
    // The color of the piece to be placed
    Color color = getPieceColor(gameButtons[row][col]);
    
    // Checks how many pieces of the same color appear in horizontal, vertical, or diagonal rows
    int horizontal = numberInLine(gameButtons, row, col, 1, 0, color) + numberInLine(gameButtons, row, col, -1, 0, color) + 1;
    int vertical = numberInLine(gameButtons, row, col, 0, 1, color) + numberInLine(gameButtons, row, col, 0, -1, color) + 1;
    int diagonalUp = numberInLine(gameButtons, row, col, 1, 1, color) + numberInLine(gameButtons, row, col, -1, -1, color) + 1;
    int diagonalDown = numberInLine(gameButtons, row, col, 1, -1, color) + numberInLine(gameButtons, row, col, -1, 1, color) + 1;
    return horizontal == getLengthToWin() || vertical == getLengthToWin() || diagonalUp == getLengthToWin() || diagonalDown == getLengthToWin();
  }
  
  /**
   * Checks if a move violates the Four-Four rule
   * Checks if a player's move creates two distinct rows of 4
   * @param row the row coordinate of the player's position
   * @param col the column coordinate of the player's position
   * @return true if the Four-Four rule is violated, false otherwise
   */
  public boolean violatesFourFour(int row, int col) {
    
    // Keeps track of the number of pieces of the same color in a row
    int count = 0;
    Color color = getPieceColor(gameButtons[row][col]);
    int horizontal = numberInLine(gameButtons, row, col, 1, 0, color) + numberInLine(gameButtons, row, col, -1, 0, color) + 1;
    int vertical = numberInLine(gameButtons, row, col, 0, 1, color) + numberInLine(gameButtons, row, col, 0, -1, color) + 1;
    int diagonalUp = numberInLine(gameButtons, row, col, 1, 1, color) + numberInLine(gameButtons, row, col, -1, -1, color) + 1;
    int diagonalDown = numberInLine(gameButtons, row, col, 1, -1, color) + numberInLine(gameButtons, row, col, -1, 1, color) + 1;
    if (horizontal == getLengthToWin() - 1)
      count ++;
    if (vertical == getLengthToWin() - 1)
      count ++;
    if (diagonalUp == getLengthToWin() - 1)
      count ++;
    if (diagonalDown == getLengthToWin() - 1)
      count ++;
    if (color.equals(Color.BLACK) && count > 0) {
      setBlackCountFourFour(getBlackCountFourFour() + 1);
    }
    if (color.equals(Color.WHITE) && count > 0) {
      setWhiteCountFourFour(getWhiteCountFourFour() +1);
    }
    if (getBlackCountFourFour() >= 2 || getWhiteCountFourFour() >= 2) {
      System.out.println("Violates the Four-Four rule; invalid move.");
      gameButtons[row][col].setGraphic(null);
      fillSquare(gameButtons[row][col]);
      setBlackCountFourFour(0);
      setWhiteCountFourFour(0);
      return true;
    }
    return false;
  }
  
  /**
   * Checks if a move violates the Three-Three rule
   * Checks if a player's move creates two distinct rows of 3 such that both ends have empty squares
   * @param row the row coordinate of the player's position
   * @param col the column coordinate of the player's position
   * @return true if the Three-Three rule is violated, false otherwise
   */
  public boolean violatesThreeThree(int row, int col) {
    
    // Keeps track of the number of pieces of the same color in a row
    int count = 0;
    Color color = getPieceColor(gameButtons[row][col]);
    if (isOpenThree(row, col, 1, 0, color))
      count ++;
    if (isOpenThree(row, col, 0, 1, color))
      count ++;
    if (isOpenThree(row, col, 1, 1, color))
      count ++;
    if (isOpenThree(row, col, 1, -1, color))
      count ++;
    if (color.equals(Color.BLACK) && count > 0) {
      setBlackCountThreeThree(getBlackCountThreeThree() +1);
    }
    if (color.equals(Color.WHITE) && count > 0) {
      setWhiteCountThreeThree(getWhiteCountThreeThree() + 1);
    }
    if (getBlackCountThreeThree() >= 2 || getWhiteCountThreeThree() >= 2) {
      System.out.println("Violates the Three-Three rule; invalid move.");
      gameButtons[row][col].setGraphic(null);
      fillSquare(gameButtons[row][col]);
      setBlackCountThreeThree(0);
      setWhiteCountThreeThree(0);
      return true;
    }
    return false;
  }
  
  /**
   * Helper method to determine if the group of three contains an empty space on either end
   * @param row the row coordinate of the player's position
   * @param col the column coordinate of the player's position
   * @param dx the change in the horizontal position of the player
   * @param dy the change in the vertical position of the player
   * @param color the color of the placed piece
   * @return true if there is an open end, otherwise false
   */
  public boolean isOpenThree(int row, int col, int dx, int dy, Color color) {
    
    // Checks for open pieces at the end of a chunk of cotiguous pieces in the forward direction
    int forward = numberInLine(gameButtons, row, col, dx, dy, color);
    
    // Checks for open pieces at the end of a chunk of cotiguous pieces in the backwards direction
    int backward = numberInLine(gameButtons, row, col, -1 * dx, -1 * dy, color);
    
    // The total is the sum of forward, backward, and this piece to be placed
    int total = 1 + forward + backward;
    if (total != getLengthToWin() - 2) {
      return false;
    }
    
    // Checks the end of the row in the forward direction
    int endRowForward = row + (forward + 1) * dx;
    
    // Checks the end of the column in the forward direction
    int endColForward = col + (forward + 1) * dy;
    
    // Checks the end of the row in the backward direction
    int endRowBackward = row + (backward + 1) * -1 * dx;
    
    // Checks the end of the row in the backward direction
    int endColBackward = col + (backward + 1) * -1 * dy;
    
    // Checks if one end is open
    boolean forwardOpen = (isValid(endRowForward, endColForward) && isEmpty(gameButtons[endRowForward][endColForward]));
    
    // Checks if the other end is open
    boolean backwardOpen = (isValid(endRowBackward, endColBackward) && isEmpty(gameButtons[endRowBackward][endColBackward]));
    return forwardOpen || backwardOpen;
  }
  
  /**
   * Determines whether the button has a black or white circle on it
   * Ensures that it matches the current player's color
   * @param button the button that was clicked
   * @param color the color to be placed after the click
   * @return true if the colors are equal, otherwise false
   */
  public boolean hasColor(Button button, Color color) {
    if (button.getGraphic() instanceof Circle) {
      Circle c = (Circle) button.getGraphic();
      return c.getFill().equals(color); 
    }
    return false;
  }
  
  /**
   * Checks whether the player's move is valid based on the rules of the game
   * @param row the row coordinate of the player's location
   * @param col the column coordinate of the player's location
   * @return true if valid, otherwise false
   */
  public boolean isValid(int row, int col) {
    return row >= 0 && row < getRows() && col >= 0 && col < getCols();
  }
  
  /**
   * Handles the directionality of the game
   * Can have horizontal, vertical, or diagonal directions
   * @param board the game board
   * @param row represents the initial horizontal position
   * @param col represents the initial vertical position
   * @param dx the move of the player in the horizontal direction
   * @param dy the move of the player in the vertical direction
   * @param color the color of the pieces to count
   * @return the number of pieces in a row
   */
  public int numberInLine(Button[][] board, int row, int col, int dx, int dy, Color color) {
    
    // stores the number of contiguous pieces in a given direction
    int count = 0;
    
    // The new horizontal position after this piece is placed
    int x = row + dx;
    
    // The new vertical position after this piece is placed
    int y = col + dy;
    
    /* Loops through each button placed
     * If the move is valid and the color matches, increment count and move to the next piece until the end is reached */
    while (isValid(x, y) && hasColor(gameButtons[x][y], color)) {
      count ++;
      x += dx;
      y += dy;
    }
    return count;
  }
  
/**
 * Resets the Gomoku game to its initial state.
 * Clears the board, resets all rule counters, flags, and sets the starting turn to Black.
 */
public void resetGomoku() {
  setGameOver(false);
  switchTurn(true);
  setBlackCountThreeThree(0);
  setWhiteCountThreeThree(0);
  setBlackCountFourFour(0);
  setWhiteCountFourFour(0);
  for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        gameButtons[row][col].setGraphic(null);
        fillSquare(gameButtons[row][col]);
    }
  }
  Gomoku.message.setText("Welcome! Let's play again.");
}
  
  /**
   * Launches the Gomoku board game
   *@param args the command line arguments
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
}