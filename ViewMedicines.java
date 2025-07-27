import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewMedicines extends JFrame {
    JTable table;

    public ViewMedicines() {
        setTitle("View Medicines");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Qty"}, 0);
        table.setModel(model);

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medicines");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table));
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewMedicines();
    }
}
