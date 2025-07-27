import javax.swing.*;
import java.sql.*;

public class Purchase extends JFrame {
    JTextField idField, qtyField;

    public Purchase() {
        setTitle("Purchase - Add Stock");
        setSize(300, 200);
        setLayout(null);

        JLabel idLabel = new JLabel("Medicine ID:");
        idLabel.setBounds(30, 30, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(130, 30, 100, 25);
        add(idField);

        JLabel qtyLabel = new JLabel("Add Quantity:");
        qtyLabel.setBounds(30, 70, 100, 25);
        add(qtyLabel);

        qtyField = new JTextField();
        qtyField.setBounds(130, 70, 100, 25);
        add(qtyField);

        JButton addBtn = new JButton("Add Stock");
        addBtn.setBounds(90, 120, 100, 30);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                int id = Integer.parseInt(idField.getText());
                int qty = Integer.parseInt(qtyField.getText());

                PreparedStatement ps = conn.prepareStatement("UPDATE medicines SET quantity = quantity + ? WHERE id = ?");
                ps.setInt(1, qty);
                ps.setInt(2, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Stock updated!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Purchase();
    }
}
