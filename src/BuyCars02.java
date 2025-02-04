import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

public class BuyCars02 extends JFrame {
	private JTable voitureTable;
    private JButton btnAcheter;
    private int idClient;
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/Magasin_Voitures";
    private final String USER = "root";
    private final String PASSWORD = "system";


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyCars02 frame = new BuyCars02(1);
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
	public BuyCars02(int idClient) {
		setTitle("Commander une voiture");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.idClient = idClient;
        setTitle("Achat Voiture");
        setSize(600, 400);
        setLayout(new BorderLayout());

		setContentPane(contentPane);
		
		String[] columnNames = {"ID Voiture", "Marque", "Modèle", "Prix"};
        Vector<Vector<Object>> data = new Vector<>();
        try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD)) {
            String query = "SELECT * FROM voiture";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id_voiture"));
                row.add(rs.getString("marque"));
                row.add(rs.getString("modele"));
                row.add(rs.getDouble("prix"));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        voitureTable = new JTable(data, new Vector<>(Arrays.asList(columnNames)));
        add(new JScrollPane(voitureTable), BorderLayout.CENTER);

        btnAcheter = new JButton("Acheter");
        btnAcheter.addActionListener(e -> acheterVoiture());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnAcheter);
        add(bottomPanel, BorderLayout.SOUTH);
	}
	
	 private void acheterVoiture() {
	        int selectedRow = voitureTable.getSelectedRow();
	        if (selectedRow != -1) {
	            int idVoiture = (Integer) voitureTable.getValueAt(selectedRow, 0);

	            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
	                String query = "INSERT INTO commande (id_client, id_voiture, date_commande) VALUES (?, ?, ?)";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setInt(1, idClient);
	                stmt.setInt(2, idVoiture);
	                stmt.setDate(3, new Date(System.currentTimeMillis()));
	                stmt.executeUpdate();

	                JOptionPane.showMessageDialog(this, "Achat effectué avec succès!");
	            } catch (SQLException e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Erreur lors de l'achat de la voiture.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une voiture.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        }
	    }

}
