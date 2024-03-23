import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class divideAndConquerProgram extends JDialog {
    private JPanel contentPane;
    private JButton bttnAdd;
    private JTextField txtName;
    private JTextField txtColor;
    private JComboBox cbFabricType;
    private JTextArea listClothes;
    private JButton bttnWash;
    private JTextArea txtWash;
    private JComboBox cbYesNo;

    private List<LaundryItem> laundryItems;

    public divideAndConquerProgram() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bttnAdd);

        bttnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        bttnWash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onWash();
            }
        });

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

        laundryItems = new ArrayList<>();
    }

    class LaundryItem {
        private String name;
        private String color;
        private String fabricType;
        private boolean isDirty;

        public LaundryItem(String name, String color, String fabricType, boolean isDirty) {
            this.name = name;
            this.color = color;
            this.fabricType = fabricType;
            this.isDirty = isDirty;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

        public String getFabricType() {
            return fabricType;
        }

        public boolean isDirty() {
            return isDirty;
        }

        public void setDirty(boolean dirty) {
            isDirty = dirty;
        }
    }

    public void divideAndConquer(List<LaundryItem> items) {
        List<LaundryItem> whites = new ArrayList<>();
        List<LaundryItem> colored = new ArrayList<>();
        List<LaundryItem> delicate = new ArrayList<>();

        // Divide the laundry items into subsets
        for (LaundryItem item : items) {
            if (item.getFabricType().equalsIgnoreCase("Silk") ||
                    item.getFabricType().equalsIgnoreCase("Linen") ||
                    item.getFabricType().equalsIgnoreCase("Lace") ||
                    item.getFabricType().equalsIgnoreCase("Chiffon")) {
                delicate.add(item);
            }
            else if (item.getColor().equalsIgnoreCase("White")) {
                whites.add(item);
            }  else {
                colored.add(item);
            }
        }

        // Perform washing/cleaning operation on subsets
        washSubset(whites);
        washSubset(colored);
        dryCleaningSubset(delicate);
    }


    public void washSubset(List<LaundryItem> subset) {
        txtWash.append("Washing subset...\n");
        for (LaundryItem item : subset) {
            if (item.isDirty()) {
                txtWash.append("Washing " + item.getName() + "...\n");
                item.setDirty(false); // Mark item as clean after washing
            } else {
                txtWash.append("No need to wash " + item.getName() + "...\n");
            }
        }
        txtWash.append("Subset washed successfully!\n\n");
    }

    public void dryCleaningSubset(List<LaundryItem> subset) {
        txtWash.append("Dry Cleaning subset...\n");
        for (LaundryItem item : subset) {
            if (item.isDirty()) {
                txtWash.append("Dry Cleaning " + item.getName() + "...\n");
            } else {
                txtWash.append("No need to clean " + item.getName() + "...\n");
            }
        }
        txtWash.append("Subset cleaned successfully!\n\n");
    }

    private void onAdd() {
        boolean isDirty = cbYesNo.getSelectedItem().equals("Yes");
        LaundryItem item = new LaundryItem(txtName.getText(), txtColor.getText(), (String) cbFabricType.getSelectedItem(), isDirty);
        laundryItems.add(item);

        listClothes.append(txtName.getText() + "\t" + txtColor.getText() + "\t" + cbFabricType.getSelectedItem() + "\tIs it dirty? " + cbYesNo.getSelectedItem() + "\n");
    }

    private void onWash() {
        divideAndConquer(new ArrayList<>(laundryItems)); // Pass a copy of the list
    }

    public static void main(String[] args) {
        divideAndConquerProgram dialog = new divideAndConquerProgram();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
