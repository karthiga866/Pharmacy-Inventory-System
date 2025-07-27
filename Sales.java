import javax.swing.*;
import java.sql.*;

public class Sales extends JFrame {
    JTextField idField, qtyField;

    public Sales() {
        setTitle("Sales - Sell Medicine");
        setSize(300, 200);
        setLayout(null);

        JLabel idLabel = new JLabel("Medicine ID:");
        idLabel.setBounds(30, 30, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(130, 30, 100, 25);
        add(idField);

        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(30, 70, 100, 25);
        add(qtyLabel);

        qtyField = new JTextField();
        qtyField.setBounds(130, 70, 100, 25);
        add(qtyField);

        JButton sellBtn = new JButton("Sell");
        sellBtn.setBounds(90, 120, 100, 30);
        add(sellBtn);

        sellBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                int id = Integer.parseInt(idField.getText());
                int qty = Integer.parseInt(qtyField.getText());

                PreparedStatement ps = conn.prepareStatement("UPDATE medicines SET quantity = quantity - ? WHERE id = ?");
                ps.setInt(1, qty);
                ps.setInt(2, id);
                int result = ps.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Sale recorded!");
                } else {
                    JOptionPane.showMessageDialog(null, "Medicine not found!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Sales();
    }
}
