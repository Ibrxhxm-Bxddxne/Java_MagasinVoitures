import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

public class AddCars extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final String DB_URL = "jdbc:mysql://localhost:3306/Magasin_Voitures";
    private final String USER = "root";
    private final String PASSWORD = "system";
    private JTextField MarqueTxt;
    private JTextField ModelTxt;
    private JTextField AnneeTxt;
    private JTextField PrixTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCars frame = new AddCars();
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
	public AddCars() {
		setBounds(100, 100, 775, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(249, 236, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CarLabel = new JLabel("Marque :");
		CarLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		CarLabel.setBounds(128, 66, 124, 46);
		contentPane.add(CarLabel);
		
		MarqueTxt = new JTextField();
		MarqueTxt.setBounds(354, 66, 217, 37);
		contentPane.add(MarqueTxt);
		MarqueTxt.setColumns(10);
		
		JLabel lblModle = new JLabel("Modéle :");
		lblModle.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblModle.setBounds(128, 122, 124, 46);
		contentPane.add(lblModle);
		
		ModelTxt = new JTextField();
		ModelTxt.setColumns(10);
		ModelTxt.setBounds(354, 122, 217, 37);
		contentPane.add(ModelTxt);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblPrix.setBounds(128, 237, 124, 46);
		contentPane.add(lblPrix);
		
		JLabel lblDateDuSortie = new JLabel("Date du sortie :");
		lblDateDuSortie.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblDateDuSortie.setBounds(128, 181, 155, 46);
		contentPane.add(lblDateDuSortie);
		
		AnneeTxt = new JTextField();
		AnneeTxt.setColumns(10);
		AnneeTxt.setBounds(354, 181, 217, 37);
		contentPane.add(AnneeTxt);
		
		PrixTxt = new JTextField();
		PrixTxt.setColumns(10);
		PrixTxt.setBounds(354, 237, 217, 37);
		contentPane.add(PrixTxt);
		
		JButton AddButton = new JButton("Ajouter");
		AddButton.setFont(new Font("Gill Sans Nova", Font.BOLD, 20));
		AddButton.setBounds(447, 323, 124, 46);
		contentPane.add(AddButton);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage admin = new AdminPage();
				admin.show();
			}
		});
		btnRetour.setFont(new Font("Gill Sans Nova", Font.BOLD, 20));
		btnRetour.setBounds(128, 323, 124, 46);
		contentPane.add(btnRetour);
		AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterVoiture();
            }
        });
	}
	
	private void AjouterVoiture() {
		
		String marque = MarqueTxt.getText(); 
		String modele=ModelTxt.getText(); 
		String annee =AnneeTxt.getText();
		String prix = PrixTxt.getText();
		
		if (marque.isEmpty() || modele.isEmpty() ||annee.isEmpty() ||prix.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer tous les informations", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
		int annee0 = Integer.parseInt(annee);
		double prix0 = Double.parseDouble(prix);
		
		
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD)){
		
		String sql = "INSERT INTO voiture(marque, modele, annee, prix) VALUES(?,?,?,?)";
		
		 try (PreparedStatement statement = conn.prepareStatement(sql)) {
             statement.setString(1, marque);
             statement.setString(2, modele);
             statement.setInt(3, annee0);
             statement.setDouble(4, prix0);
             
             int rowsInserted = statement.executeUpdate();
             if (rowsInserted > 0) {
                 JOptionPane.showMessageDialog(this, "Une nouvelle voiture a été ajoutée !");
             }
		 }
		
		
		
		
	}catch (SQLException e) {
        e.printStackTrace();
	}
	}
}
