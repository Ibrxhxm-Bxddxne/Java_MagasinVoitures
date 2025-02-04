import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientPage extends JFrame {
	
	private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JButton btnInscrire;
    
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
					ClientPage frame = new ClientPage();
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
	public ClientPage() {

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		 setTitle("Inscription Client");
	        setSize(400, 300);

	        setLayout(new GridLayout(5, 2));

	        JLabel lblNom = new JLabel("Nom:");
	        txtNom = new JTextField();
	        
	        JLabel lblPrenom = new JLabel("Prénom:");
	        txtPrenom = new JTextField();
	        
	        JLabel lblEmail = new JLabel("Email:");
	        txtEmail = new JTextField();
	        
	        JLabel lblPhone = new JLabel("Telephone :");
	        txtPhone = new JTextField();

	        
	        btnInscrire = new JButton("S'inscrire");
	        btnInscrire.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                enregistrerClient();
	            }
	        });

	        
	        add(lblNom);
	        add(txtNom);
	        
	        add(lblPrenom);
	        add(txtPrenom);
	        
	        add(lblEmail);
	        add(txtEmail);
	        
	        add(lblPhone);
	        add(txtPhone);
	        
	        add(new JLabel());  
	        add(btnInscrire);
	}
	private void enregistrerClient() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO client (nom, prenom, email,telephone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Client inscrit avec succès!");
                txtNom.setText("");
                txtPrenom.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}
