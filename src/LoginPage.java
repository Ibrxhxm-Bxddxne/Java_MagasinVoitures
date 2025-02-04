import java.awt.EventQueue;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameTxt;
	private JTextField PasswordText;
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/Magasin_Voitures";
    private final String USER = "root";
    private final String PASSWORD = "system";
    private JButton LoginButton;
    

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}		



	public LoginPage() {
		setTitle("LoginPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,845, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(394, 204, 236, 46);
		contentPane.add(UsernameTxt);
		UsernameTxt.setColumns(10);
		
		JLabel UserLabel = new JLabel("Username :");
		UserLabel.setFont(new Font("Arial Nova", Font.BOLD, 20));
		UserLabel.setBounds(160, 199, 121, 46);
		contentPane.add(UserLabel);
		
		PasswordText = new JTextField();
		PasswordText.setColumns(10);
		PasswordText.setBounds(394, 296, 236, 46);
		contentPane.add(PasswordText);
		
		JLabel PasswordLabel = new JLabel("Password  :");
		PasswordLabel.setFont(new Font("Arial Nova", Font.BOLD, 20));
		PasswordLabel.setBounds(160, 296, 121, 46);
		
		contentPane.add(PasswordLabel);
		JLabel Label01 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/Img03.png")).getImage();
		Label01.setIcon(new ImageIcon("C:\\Users\\Ibrahim\\Desktop\\Projet_fr\\Img02.jpg"));
		Label01.setBounds(307, 10, 176, 138);
		Image imageScale = img.getScaledInstance(Label01.getWidth(),Label01.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imageScale);
	    Label01.setIcon(scaledIcon);
		contentPane.add(Label01);
		
		LoginButton = new JButton("Se Connecter");
		LoginButton.setFont(new Font("Arial Nova", Font.PLAIN, 20));
		LoginButton.setBounds(323, 386, 160, 46);
		contentPane.add(LoginButton);
		LoginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                seConnecter();
	            }
	        });
		

	  }
	
	private void seConnecter() {
		String enteredUsername = UsernameTxt.getText();
	    String enteredPassword = PasswordText.getText();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer votre nom et mot de passe!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD)) {

	        String query = "SELECT * FROM ADMIN WHERE Username = ? AND Password = ?";

	        
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, enteredUsername);
	            stmt.setString(2, enteredPassword);

	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    
	                	JOptionPane.showMessageDialog(this, "Connexion réussie ! Bienvenue, " + enteredUsername);
	                    AdminPage Admin = new AdminPage();
	                    Admin.show();
	                    this.dispose();
	                } else {
	                    
	                    
	                    JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
	}

    
	

	

