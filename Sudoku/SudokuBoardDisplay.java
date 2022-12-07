import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class SudokuBoardDisplay  extends JComponent {
    private static final int CELL_PIXELS = 50;  
    private static final int PUZZLE_SIZE = 9;   
    private static final int SUBSQUARE   = 3;   
    private static final int BOARD_PIXELS = CELL_PIXELS * PUZZLE_SIZE;
    private static final int TEXT_OFFSET = 15;  
    private static final Font TEXT_FONT  = new Font("Sansserif", Font.BOLD, 24);
    
    private SudokuModel _model;      
    
    public SudokuBoardDisplay(SudokuModel model) {
        setPreferredSize(new Dimension(BOARD_PIXELS + 2, BOARD_PIXELS + 2));
        setBackground(Color.WHITE);
        _model = model;
    }
    
    @Override public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        
        drawGridLines(g);
        drawCellValues(g);
    }
    
    private void drawGridLines(Graphics g) {
        
        for (int i = 0; i <= PUZZLE_SIZE; i++) {
            int acrossOrDown = i * CELL_PIXELS;
            g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
            g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            
            if (i % SUBSQUARE == 0) {
                acrossOrDown++;  
                g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
                g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            }
        }
    }
    
    private void drawCellValues(Graphics g) {
        g.setFont(TEXT_FONT);
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            int yDisplacement = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (_model.getVal(i, j) != 0) {
                    int xDisplacement = j * CELL_PIXELS + TEXT_OFFSET;
                    g.drawString("" + _model.getVal(i, j), xDisplacement, yDisplacement);
                }
            }
        }
    }
    
}