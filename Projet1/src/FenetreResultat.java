import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class FenetreResultat {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreResultat window = new FenetreResultat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreResultat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPhotoCentrale = new JLabel("Photo centrale");
		lblPhotoCentrale.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotoCentrale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhotoCentrale.setBounds(128, 6, 556, 356);
		frame.getContentPane().add(lblPhotoCentrale);
		//frame.setComponentZOrder(lblPhotoCentrale, 10);
		
		JButton btnNewButton = new JButton(new ImageIcon("Images/Nord.png"));
		btnNewButton.setBounds(135, 86, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("images/a.jpg"));
		btnNewButton_1.setBounds(589, 86, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(new ImageIcon("images/a.jpg"));
		btnNewButton_2.setBounds(135, 241, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(new ImageIcon("images/a.jpg"));
		btnNewButton_3.setBounds(589, 241, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton(new ImageIcon("images/a.jpg"));
		btnNewButton_4.setBounds(366, 34, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton(new ImageIcon("images/a.jpg"));
		btnNewButton_5.setBounds(366, 302, 89, 23);
		frame.getContentPane().add(btnNewButton_5);
	}

}
