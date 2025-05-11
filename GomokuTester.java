import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A class to represent testing of the logic of the game Gomoku
 * @author Noah Leibowitz
 */
public class GomokuTester {
 
  /** Test the getter/setter methods */
  @Test
  public void testGettersSetters() {
    Gomoku g = new Gomoku();
    
    // Test getRows()
    assertEquals(19, g.getRows());
    
    // Test getCols()
    assertEquals(19, g.getCols());
    
    // Test isBlackTurn()
    assertEquals(true, g.isBlackTurn());
    
    // Test switchTurn()
    g.switchTurn(false);
    assertEquals(false, g.isBlackTurn());
    
    // Test isGameOver()
    assertEquals(false, g.isGameOver());
    
    // Test setGameOver()
    g.setGameOver(true);
    assertEquals(true, g.isGameOver());
    
    // Test getLengthToWin()
    assertEquals(5, g.getLengthToWin());
    
    // Test getBlackCountFourFour()
    assertEquals(0, g.getBlackCountFourFour());
    
    // Test getBlackCountThreeThree()
    assertEquals(0, g.getBlackCountThreeThree());
    
    // Test getWhiteCountFourFour()
    assertEquals(0, g.getWhiteCountFourFour());
    
    // Test getWhiteCountThreeThree()
    assertEquals(0, g.getWhiteCountThreeThree());
    
    // Test setBlackCountFourFour()
    g.setBlackCountFourFour(1);
    assertEquals(1, g.getBlackCountFourFour());
    
    // Test setBlackCountThreeThree()
    g.setBlackCountThreeThree(2);
    assertEquals(2, g.getBlackCountThreeThree());
    
    // Test setWhiteCountFourFour()
    g.setWhiteCountFourFour(3);
    assertEquals(3, g.getWhiteCountFourFour());
    
    // Test setWhiteCountThreeThree()
    g.setWhiteCountThreeThree(4);
    assertEquals(4, g.getWhiteCountThreeThree());
  }
  
  /** Test the helper methods that do not contain javafx components as parameters */
  @Test
  public void testHelpers() {
    Gomoku g = new Gomoku();
    
    // Test isValid(int row, int col)
    assertEquals(true, g.isValid(6, 7));
    assertEquals(false, g.isValid(19, 19));
  
    // Test hasWon(int row, int col)
    // This is the "logic" version of hasFiveInLine and numberInLine() in GomokuSample class
    
    // Test where the lengthToWin is exactly 5
    GomokuSample s = new GomokuSample();
    for (int i = 0; i < 5; i++) {
      s.setPiece(0, i);
    }
    assertEquals(true, s.hasWon(0, 4));
    
    // Test where the length to win is 7 and the board size is not the default
    GomokuSample s1 = new GomokuSample(7, 12, 15);
    for (int i = 0; i < 7; i++) {
      s1.setPiece(0, i);
    }
    assertEquals(true, s1.hasWon(0, 1));
    assertEquals(true, s1.hasWon(0, 2));
    assertEquals(true, s1.hasWon(0, 3));
    assertEquals(true, s1.hasWon(0, 4));
    assertEquals(true, s1.hasWon(0, 5));
    assertEquals(true, s1.hasWon(0, 6));
    assertEquals(false, s1.hasWon(1, 1));
    
    // Test where the length to win is 8
    GomokuSample s2 = new GomokuSample(8);
    for (int i = 0; i < 5; i++) {
      s2.setPiece(0, i);
    }
    assertEquals(false, s2.hasWon(0, 1));
    
    // Test if the amount in a row is greater than the length to win
    GomokuSample s3 = new GomokuSample();
    for (int i = 0; i < 3; i++) {
      s3.setPiece(0, i);
    } // 4 pieces in a row
    s3.setPiece(0, 5); // skip the fifth space and place a piece
    s3.setPiece(0, 4); // place a piece in between creating 6 in a row
    assertEquals(false, s3.hasWon(0, 1));
    
    // Test for wins in the vertical direction
    GomokuSample s4 = new GomokuSample();
    for (int i = 0; i < 5; i++) {
      s4.setPiece(i, 0);
    }
    assertEquals(true, s4.hasWon(1, 0));
    assertEquals(false, s4.hasWon(0, 1));
  
    // Test for wins in the diagonal direction
    GomokuSample s5 = new GomokuSample();
    for (int i = 0; i < 5; i++) {
      s5.setPiece(i, i);
    }
    assertEquals(true, s5.hasWon(1, 1));
  
    // Test when the color of the pieces switches each time
    GomokuSample s6 = new GomokuSample();
    for (int i = 0; i < 5; i++) {
      s6.setPiece(0, i);
      s6.switchTurn();
    }
    assertEquals(false, s6.hasWon(1, 1));
    
    // Test the violatesFourFour() method
    GomokuSample s7 = new GomokuSample();
    
    // First set of 4 (a row)
    s7.setPiece(0, 1);
    s7.setPiece(0, 2);
    s7.setPiece(0, 3);
    s7.setPiece(0, 4);
    
    // Next set of 4 (a column)
    s7.setPiece(1, 5);
    s7.setPiece(2, 5);
    s7.setPiece(3, 5);
    s7.setPiece(4, 5);
    
    // Place piece at the connecting point
    s7.setPiece(0, 5);
    assertEquals(true, s7.violatesFourFour(0, 5));
    
    // Test the violatesThreeThree() method
    GomokuSample s8 = new GomokuSample();
    
    // First set of 3 (open ends)
    s8.setPiece(0, 1);
    s8.setPiece(0, 2);
    s8.setPiece(0, 3);
    
    // Next set of 3 (open ends)
    s8.setPiece(1, 0);
    s8.setPiece(2, 0);
    s8.setPiece(3, 0);
    
    // Place piece on one of the open ends
    s8.setPiece(0, 4);
    assertEquals(true, s8.violatesThreeThree(0, 4));
  }
 
}