import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {

		setBounds(100, 100, 795, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Admin Page");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ViewCars = new JButton("Voitures Disponibles");
		ViewCars.setFont(new Font("Franklin Gothic Book", Font.BOLD, 20));
		ViewCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCars viewcars = new ViewCars();
				viewcars.show();
			}
		});
		ViewCars.setBounds(266, 118, 234, 53);
		contentPane.add(ViewCars);
		
		JButton AddCars = new JButton("Ajouter Voitures");
		AddCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCars add = new AddCars();
				add.show();
			}
		});
		AddCars.setFont(new Font("Franklin Gothic Book", Font.BOLD, 20));
		AddCars.setBounds(266, 249, 234, 53);
		contentPane.add(AddCars);
	}
}
