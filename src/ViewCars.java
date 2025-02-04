import java.awt.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
public class ViewCars extends JFrame {
	private final String DB_URL = "jdbc:mysql://localhost:3306/Magasin_Voitures";
    private final String USER = "root";
    private final String PASSWORD = "system";
    
    private JPanel panel;
    private JTable table;
    private DefaultTableModel model;
    private Connection connection;

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCars frame = new ViewCars();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewCars() {
		setTitle("Tableau SQL");
        setBounds(100, 100, 500, 400);

        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);
        
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        model = new DefaultTableModel();
        model.addColumn("id_voiture");
        model.addColumn("marque");
        model.addColumn("modele");
        model.addColumn("annee");
        model.addColumn("prix");
        
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton deleteButton = new JButton("Supprimer");
        panel.add(deleteButton, BorderLayout.SOUTH);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                deleteItem(id);
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un élément à supprimer.");
            }
        });
        loadData();

	}
	 private void loadData() {
	        try (Statement stmt = connection.createStatement()) {
	            ResultSet rs = stmt.executeQuery("SELECT * FROM voiture");

	            while (rs.next()) {
	                int id = rs.getInt("id_voiture");
	                String marque = rs.getString("marque");
	                String modele = rs.getString("modele");
	                int annee = rs.getInt("annee");
	                Double prix = rs.getDouble("prix");
	                model.addRow(new Object[]{id, marque,modele,annee,prix});
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 private void deleteItem(int id) {
	        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM voiture WHERE id_voiture = ?")) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


}