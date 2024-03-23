import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class transformAndConquerProgram extends JDialog {
    private JPanel contentPane;
    private JTextArea txtGroceryList;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtValue;
    private JButton bttnAdd;
    private JButton bttnSort;
    private JTextArea txtDisplayPurchases;
    private JComboBox cbGroceryItems;
    private JTextField txtDisplayPrice;
    private JTextField txtDisplayValue;
    private JTextField txtCash;
    private JButton bttnBuy;
    public boolean ifAdd=false;
    public boolean ifBuy=false;
    ArrayList<GroceryItem> shoppingList = new ArrayList<>();
    public double cash;


    public transformAndConquerProgram() {
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



        bttnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        bttnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSort();
            }
        });

        cbGroceryItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCBGroceryItem();
            }
        });

        bttnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBuy();
            }
        });
    }


    class GroceryItem{
        private String name;
        private double price;
        private int value;

        public GroceryItem(String name, double price, int value) {
            this.name = name;
            this.price = price;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getValue() {
            return value;
        }

        public double getPriceValueRatio() {
            return price / value;
        }
    }


    private void onAdd(){
        GroceryItem item = new GroceryItem(txtName.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtValue.getText()));
        shoppingList.add(item);
        if(!ifAdd){
            txtGroceryList.setText("Name\tPrice\tValue\n");
            ifAdd=true;
        }
        txtGroceryList.append(txtName.getText()+"\t$"+ Double.parseDouble(txtPrice.getText())+"\t"+ Integer.parseInt(txtValue.getText()) + "\n");
    }

    private void onSort(){
        Collections.sort(shoppingList, Comparator.comparing(GroceryItem::getPriceValueRatio));
        for (int i = 0; i < shoppingList.size(); i++){
            GroceryItem item= shoppingList.get(i);

            if (i==0){
                txtGroceryList.append("\n\nPRE-SORTED LIST\n" +
                        "Name\tPrice\tValue\n");
            }
            txtGroceryList.append((i + 1) + ". " + item.getName() + "\t$" + item.getPrice() + "\t" + item.getValue() + "\n");
            cbGroceryItems.addItem(item.getName());
        }

    }

    private void onCBGroceryItem(){
        for (int i = 0; i < shoppingList.size(); i++) {
            GroceryItem item = shoppingList.get(i);

            if (item.getName() ==cbGroceryItems.getSelectedItem()){
                txtDisplayPrice.setText(String.valueOf(item.getPrice()));
                txtDisplayValue.setText(String.valueOf(item.getValue()));
            }
        }
    }

    private void onBuy(){

        String cashText = txtCash.getText().trim();
        if (cashText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your available cash first!", "Missing Cash", JOptionPane.WARNING_MESSAGE);
            return;
        }


        cash= Double.parseDouble(cashText);


        for (int i = 0; i < shoppingList.size(); i++) {
            GroceryItem item = shoppingList.get(i);

            if (cash <= 0) {
                JOptionPane.showMessageDialog(null, "Warning: Your cash is empty!", "Cash Empty", JOptionPane.WARNING_MESSAGE);
                break;
            }

            if (item.getName() ==cbGroceryItems.getSelectedItem()){
                txtCash.setText(String.valueOf((cash-item.getPrice())));
                txtDisplayPurchases.append("You purchased: "+item.getName()+"\n");
                cbGroceryItems.removeItem(item.getName());

                break;
            }
        }
    }

    public static void main(String[] args) {
        transformAndConquerProgram dialog = new transformAndConquerProgram();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
