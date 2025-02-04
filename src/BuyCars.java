import java.awt.*;
import java.sql.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BuyCars extends JFrame {
	
	private JTable voitureTable;
	private JComboBox<String> clientComboBox;
	private JButton acheterButton;
	private final String DB_URL = "jdbc:mysql://localhost:3306/Magasin_Voitures";
    private final String USER = "root";
    private final String PASSWORD = "system";
    
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel model;
    private Connection connection;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyCars frame = new BuyCars();
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
	public BuyCars() {
		setTitle("Acheter une voiture");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String[] columnNames = {"id_voiture", "marque", "modele", "prix"};
        Vector<Vector<Object>> data = new Vector<>();
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String query = "SELECT * FROM voiture";
            Statement stmt = connection.createStatement();
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
       
        clientComboBox = new JComboBox<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM client";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                clientComboBox.addItem(rs.getString("nom") + " " + rs.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        acheterButton = new JButton("Acheter");
        acheterButton.addActionListener(e -> acheterVoiture());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Sélectionnez un client:"));
        bottomPanel.add(clientComboBox);
        bottomPanel.add(acheterButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void acheterVoiture() {
        int selectedRow = voitureTable.getSelectedRow();
        if (selectedRow != -1) {
            int idVoiture = (Integer) voitureTable.getValueAt(selectedRow, 0);
            String selectedClient = (String) clientComboBox.getSelectedItem();
            String[] clientNames = selectedClient.split(" ");
            String clientNom = clientNames[0];
            String clientPrenom = clientNames[1];
            
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            	String query = "SELECT id_client FROM client WHERE nom = ? AND prenom = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, clientNom);
                stmt.setString(2, clientPrenom);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int idClient = rs.getInt("id_client");
                    String insertQuery = "INSERT INTO commande (id_client, id_voiture, date_commande) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setInt(1, idClient);
                    insertStmt.setInt(2, idVoiture);
                    insertStmt.setDate(3, new Date(System.currentTimeMillis()));
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Commande enregistrée avec succès!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'achat de la voiture.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une voiture.");
        }
    
            


        
        

	}
	 
	 



}
