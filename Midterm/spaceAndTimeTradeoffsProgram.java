import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class spaceAndTimeTradeoffsProgram extends JDialog {
    private JPanel contentPane;
    private JTextArea txtInput;
    private JRadioButton radNaiveStringMatching;
    private JRadioButton radKMP;
    private JButton bttnSearch;
    private JTextField txtFind;
    private JTextArea txtResult;


    public int searchOption;

    public spaceAndTimeTradeoffsProgram() {
        setContentPane(contentPane);
        setModal(true);

        radNaiveStringMatching.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOption=1;
            }
        });
        radKMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOption=2;
            }
        });

        bttnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });
    }

    public static List<Integer> naiveStringMatch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) { // If pattern[0...m-1] is identical to text[i...i+m-1]
                matches.add(i);
            }
        }

        return matches;
    }

    public static List<Integer> knuthMorrisPratt(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);

        int i = 0; // index for text[]
        int j = 0; // index for pattern[]

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                matches.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return matches;
    }

    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }

        return lps;
    }

    public void onSearch(){
        String inputText = txtInput.getText();
        String findText = txtFind.getText();
        if (inputText.isEmpty() || findText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Text and pattern cannot be empty.", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Integer> matches = new ArrayList<>();
        long startTime, endTime;

        switch (searchOption) {
            case 1:
                startTime = System.nanoTime();
                matches = naiveStringMatch(inputText, findText);
                endTime = System.nanoTime();
                txtResult.append("Naive String Matching:\n");
                break;
            case 2:
                startTime = System.nanoTime();
                matches = knuthMorrisPratt(inputText, findText);
                endTime = System.nanoTime();
                txtResult.append("Knuth-Morris-Pratt (KMP):\n");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + searchOption);
        }

        if (matches.isEmpty()) {
            txtResult.append("No matches found.\n");
        } else {
            txtResult.append("Matches found at indices: " + matches+ "\n");
        }

        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
        txtResult.append("Execution time: " + duration + " milliseconds\n\n");
    }

    public static void main(String[] args) {
        spaceAndTimeTradeoffsProgram dialog = new spaceAndTimeTradeoffsProgram();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
