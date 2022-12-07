import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sudoku extends JFrame {
    ///
    private static final String INITIAL_BOARD =
            "8156....4/" +
            "6...75.8./" +
            "....9..../" +
            "9...417../" +
            ".4.....2./" +
            "..623...8/" +
            "....5..../" +
            ".5.91...6/" +
            "1....7895";
    
    //=================================================================== fields
    private SudokuModel        _sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay _sudokuBoard = new SudokuBoardDisplay(_sudokuLogic);
    
    private JTextField _rowTF = new JTextField(2);
    private JTextField _colTF = new JTextField(2);
    private JTextField _valTF = new JTextField(2);
    
    //============================================================== constructor
    public Sudoku() {
        // 1... Create/initialize components
        JButton moveBtn = new JButton("Move");
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Row (1-9):"));
        controlPanel.add(_rowTF);
        controlPanel.add(new JLabel("Col (1-9):"));
        controlPanel.add(_colTF);
        controlPanel.add(new JLabel("Val:"));
        controlPanel.add(_valTF);
        controlPanel.add(moveBtn);
        
        //... Add listener
        moveBtn.addActionListener(new MoveListener());
        
        // 2... Create content panel, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        // 3... Add the components to the content panel.
        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_sudokuBoard, BorderLayout.CENTER);
        
        // 4... Set this window's attributes, and pack it.
        setContentPane(content);
        setTitle("Sudoku 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);               // Don't let user resize it.
        pack();
        setLocationRelativeTo(null);       // Center it.
    }
    
    
    ///////////////////////////////////////////////////////////// MoveListener
    class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                //... Translate row and col to zero-based indexes.
                int row = Integer.parseInt(_rowTF.getText().trim()) - 1;
                int col = Integer.parseInt(_colTF.getText().trim()) - 1;
                int val = Integer.parseInt(_valTF.getText().trim());
                if (_sudokuLogic.isLegalMove(row, col, val)) {
                    _sudokuLogic.setVal(row, col, val);
                    _sudokuBoard.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                }
                
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
        }
    }
    
    //===================================================================== main
    public static void main(String[] args) {
        new Sudoku().setVisible(true);
    }
}