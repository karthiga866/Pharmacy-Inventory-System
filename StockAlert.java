import javax.swing.*;
import java.sql.*;

public class StockAlert extends JFrame {
    JTextArea alertArea;

    public StockAlert() {
        setTitle("Stock Alerts - Low Quantity");
        setSize(400, 300);
        alertArea = new JTextArea();
        alertArea.setEditable(false);
        add(new JScrollPane(alertArea));

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM medicines WHERE quantity < 5");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alertArea.append("⚠️ " + rs.getString("name") + " is low (" + rs.getInt("quantity") + " left)\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StockAlert();
    }
}
