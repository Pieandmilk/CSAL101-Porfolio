import javax.swing.*;
import java.awt.event.*;

public class midtermGUI extends JDialog {
    private JPanel contentPane;

    private JButton bttnDivideAndConquer;
    private JButton bttnTransformAndConquer;
    private JButton bttnSpaceAndTimeTrade;

    public midtermGUI() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        bttnDivideAndConquer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDivideAndConquerProgram();
            }
        });

        bttnTransformAndConquer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTransformAndConquerProgram();
            }
        });

        bttnSpaceAndTimeTrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSpaceAndTimeTradeOffProgram();
            }
        });
    }

    // Method to display the divide and conquer program
    private void showDivideAndConquerProgram() {

        divideAndConquerProgram DivideAndConquerProgram = new divideAndConquerProgram();
        DivideAndConquerProgram.setTitle("DIVIDE AND CONQUER: Laundry");
        DivideAndConquerProgram.pack();
        DivideAndConquerProgram.setVisible(true);
    }

    private void showTransformAndConquerProgram(){
        transformAndConquerProgram TransformAndConquerProgram = new transformAndConquerProgram();
        TransformAndConquerProgram.setTitle("TRANSFORM AND CONQUER: Grocery Shopping");
        TransformAndConquerProgram.pack();
        TransformAndConquerProgram.setVisible(true);
    }

    private void showSpaceAndTimeTradeOffProgram(){
        spaceAndTimeTradeoffsProgram SpaceAndTimeTradeOffProgram = new spaceAndTimeTradeoffsProgram();
        SpaceAndTimeTradeOffProgram.setTitle("SPACE AND TIME TRADEOFF: Text Searcher");
        SpaceAndTimeTradeOffProgram.pack();
        SpaceAndTimeTradeOffProgram.setVisible(true);
    }


    public static void main(String[] args) {
        midtermGUI dialog = new midtermGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
