import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FenetreWB {

	private JFrame frmVisioscope;
	private JTextField txtTapezVotreRecherche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreWB window = new FenetreWB();
					window.frmVisioscope.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreWB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisioscope = new JFrame();
		frmVisioscope.getContentPane().setBackground(Color.DARK_GRAY);
		frmVisioscope.setFont(new Font("Arial", Font.BOLD, 12));
		frmVisioscope.setTitle("VisioScope");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmVisioscope.getContentPane().setLayout(gridBagLayout);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 14;
		gbc_separator.gridy = 0;
		frmVisioscope.getContentPane().add(separator, gbc_separator);

		JLabel lblVisioscope = new JLabel("VisioScope");
		lblVisioscope.setForeground(Color.GREEN);
		lblVisioscope.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		GridBagConstraints gbc_lblVisioscope = new GridBagConstraints();
		gbc_lblVisioscope.gridheight = 2;
		gbc_lblVisioscope.gridwidth = 6;
		gbc_lblVisioscope.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisioscope.gridx = 20;
		gbc_lblVisioscope.gridy = 1;
		frmVisioscope.getContentPane().add(lblVisioscope, gbc_lblVisioscope);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("/Users/thunderbzh/Desktop/roller.jpg"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		Dimension taille = new Dimension( 1000, 650);
		lblNewLabel.setMinimumSize(new Dimension(1050, 650));
		//lblNewLabel.setMaximumSize(taille);
		//lblNewLabel.setPreferredSize(taille);
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridheight = 19;
		gbc_lblNewLabel.gridwidth = 31;
		gbc_lblNewLabel.gridx = 16;
		gbc_lblNewLabel.gridy = 4;
		frmVisioscope.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtTapezVotreRecherche = new JTextField();
		txtTapezVotreRecherche.setText("Tapez votre recherche");
		GridBagConstraints gbc_txtTapezVotreRecherche = new GridBagConstraints();
		gbc_txtTapezVotreRecherche.gridwidth = 7;
		gbc_txtTapezVotreRecherche.insets = new Insets(0, 0, 5, 5);
		gbc_txtTapezVotreRecherche.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTapezVotreRecherche.gridx = 5;
		gbc_txtTapezVotreRecherche.gridy = 5;
		frmVisioscope.getContentPane().add(txtTapezVotreRecherche,
				gbc_txtTapezVotreRecherche);
		txtTapezVotreRecherche.setColumns(10);

		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 6;
		frmVisioscope.getContentPane().add(comboBox, gbc_comboBox);
		comboBox.addItem("Visite intérieure");
		comboBox.addItem("Visite extérieure");
		comboBox.addItem("Photos aléatoires");

		JButton btnNewButton_2 = new JButton("Ok!");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 12;
		gbc_btnNewButton_2.gridy = 6;
		frmVisioscope.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

		JTextArea txtrBienvenueSurVisioscope = new JTextArea();
		txtrBienvenueSurVisioscope
				.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtrBienvenueSurVisioscope
				.setText("Bienvenue sur VisioScope, \nune application vous\npermettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!\"");
		txtrBienvenueSurVisioscope.setForeground(Color.GREEN);
		txtrBienvenueSurVisioscope.setEditable(false);
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_txtrBienvenueSurVisioscope = new GridBagConstraints();
		gbc_txtrBienvenueSurVisioscope.gridheight = 11;
		gbc_txtrBienvenueSurVisioscope.gridwidth = 9;
		gbc_txtrBienvenueSurVisioscope.insets = new Insets(0, 0, 5, 5);
		gbc_txtrBienvenueSurVisioscope.fill = GridBagConstraints.BOTH;
		gbc_txtrBienvenueSurVisioscope.gridx = 5;
		gbc_txtrBienvenueSurVisioscope.gridy = 8;
		frmVisioscope.getContentPane().add(txtrBienvenueSurVisioscope,
				gbc_txtrBienvenueSurVisioscope);

		JButton btnHautGauche = new JButton("Haut Gauche");
		btnHautGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
				JButton btnNewButton_1 = new JButton("Précédent");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_1.gridx = 17;
				gbc_btnNewButton_1.gridy = 23;
				frmVisioscope.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		GridBagConstraints gbc_btnHautGauche = new GridBagConstraints();
		gbc_btnHautGauche.gridwidth = 3;
		gbc_btnHautGauche.insets = new Insets(0, 0, 5, 5);
		gbc_btnHautGauche.gridx = 26;
		gbc_btnHautGauche.gridy = 23;
		frmVisioscope.getContentPane().add(btnHautGauche, gbc_btnHautGauche);

		JButton btnHaut = new JButton("Haut");
		btnHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnHaut = new GridBagConstraints();
		gbc_btnHaut.gridwidth = 3;
		gbc_btnHaut.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaut.gridx = 29;
		gbc_btnHaut.gridy = 23;
		frmVisioscope.getContentPane().add(btnHaut, gbc_btnHaut);

		JButton btnHautDroite = new JButton("Haut Droite");
		btnHautDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnHautDroite = new GridBagConstraints();
		gbc_btnHautDroite.gridwidth = 3;
		gbc_btnHautDroite.insets = new Insets(0, 0, 5, 5);
		gbc_btnHautDroite.gridx = 32;
		gbc_btnHautDroite.gridy = 23;
		frmVisioscope.getContentPane().add(btnHautDroite, gbc_btnHautDroite);
		
				JButton btnNewButton = new JButton(" Suivant ");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 45;
				gbc_btnNewButton.gridy = 23;
				frmVisioscope.getContentPane().add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_3 = new JButton("    Gauche    ");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridwidth = 3;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 26;
		gbc_btnNewButton_3.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnNewButton_4 = new JButton(" Bas ");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.gridwidth = 3;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 29;
		gbc_btnNewButton_4.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_5 = new JButton("    Droite    ");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridwidth = 3;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 32;
		gbc_btnNewButton_5.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);

	}

}
