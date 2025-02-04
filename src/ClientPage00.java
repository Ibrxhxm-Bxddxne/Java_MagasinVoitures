import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientPage00 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientPage00 frame = new ClientPage00();
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
	public ClientPage00() {
		setTitle("Client Page");
		setBounds(100, 100, 533, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Signin = new JButton("Inscription");
		Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientPage Page01 = new ClientPage();
				Page01.show();
			}
		});
		Signin.setFont(new Font("Arial Nova", Font.BOLD | Font.ITALIC, 15));
		Signin.setBounds(176, 137, 135, 61);
		contentPane.add(Signin);
		
		JButton Login = new JButton("Deja inscrit");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				ClientConnection Page02 = new ClientConnection();
				Page02.show();
			}
		});
		Login.setFont(new Font("Arial Nova", Font.BOLD | Font.ITALIC, 15));
		Login.setBounds(176, 259, 135, 61);
		contentPane.add(Login);
	}

}
