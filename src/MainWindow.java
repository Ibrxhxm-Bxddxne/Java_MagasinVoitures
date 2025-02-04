import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setTitle("Garage des voitures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.ORANGE);
		panel1.setBounds(0, 0, 306, 399);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JButton ViewCars = new JButton("Client");
		ViewCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientPage00 Page03 = new ClientPage00();
				Page03.show();
			}
		});
		ViewCars.setFont(new Font("Arial Nova", Font.BOLD, 20));
		ViewCars.setBounds(39, 155, 234, 46);
		panel1.add(ViewCars);
		
		JButton AddCars = new JButton("Admin");
		AddCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage Login = new LoginPage();
				Login.show();
			}
		});
		AddCars.setFont(new Font("Arial Nova", Font.BOLD, 20));
		AddCars.setBounds(39, 243, 234, 46);
		panel1.add(AddCars);
		
		JLabel Garagelabel = new JLabel("Garage ");
		Garagelabel.setBackground(Color.WHITE);
		Garagelabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40));
		Garagelabel.setBounds(91, 73, 182, 53);
		panel1.add(Garagelabel);
		
		JLabel Label01 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/Img02.jpg")).getImage();
		Label01.setIcon(new ImageIcon("C:\\Users\\Ibrahim\\Desktop\\Projet_fr\\Img02.jpg"));
		Label01.setBounds(309, 0, 396, 399);
		Image imageScale = img.getScaledInstance(Label01.getWidth(),Label01.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imageScale);
	    Label01.setIcon(scaledIcon);
		contentPane.add(Label01);
	}
}
