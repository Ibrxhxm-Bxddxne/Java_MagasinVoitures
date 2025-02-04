import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientConnection extends JFrame {
	
	private JTextField txtNom;
	private JButton btnSeConnecter;
	
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
					ClientConnection frame = new ClientConnection();
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
	public ClientConnection() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		setTitle("Connexion Client");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        JLabel lblNom = new JLabel("Entrez votre nom:");
        txtNom = new JTextField();
        
        btnSeConnecter = new JButton("Se connecter");
        btnSeConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seConnecter();
            }
        });

        add(lblNom);
        add(txtNom);
        
        add(new JLabel());
        add(btnSeConnecter);
	}
	private void seConnecter() {
        String nom = txtNom.getText();

        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer votre nom!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM client WHERE nom = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Connexion réussie!");
                new BuyCars02(rs.getInt("id_client")).setVisible(true);
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Client non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
