import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddMedicine extends JFrame {
    JTextField nameField, priceField, quantityField;
    JButton addBtn;

    public AddMedicine() {
        setTitle("Add Medicine");
        setSize(300, 250);
        setLayout(null);

        JLabel nameLabel = new JLabel("Medicine Name:");
        nameLabel.setBounds(20, 20, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(140, 20, 120, 25);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 60, 120, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(140, 60, 120, 25);
        add(priceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 120, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(140, 100, 120, 25);
        add(quantityField);

        addBtn = new JButton("Add");
        addBtn.setBounds(90, 150, 100, 30);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO medicines(name, price, quantity) VALUES (?, ?, ?)");
                ps.setString(1, nameField.getText());
                ps.setDouble(2, Double.parseDouble(priceField.getText()));
                ps.setInt(3, Integer.parseInt(quantityField.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medicine added!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddMedicine();
    }
}
