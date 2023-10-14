package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceTest extends JFrame {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private JPanel parentPanel;
	private JButton frame1Button;
	private JComboBox<String> frameSelector;
	private String[] frameNames = { "Ajouter Un Produit", "Modifier Un Produit", "Supprimer Un Produit",
			"Consulter un produit" };
	private Application app;

	public InterfaceTest(Application app) {
		setTitle("Parent Child Example");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		this.app = app;
		// Create parent panel and buttons
		parentPanel = new JPanel();
		frame1Button = new JButton("Vendre Un Produit");
		JLabel lblVendreProduit = new JLabel("Gerer Produits: ");
		frameSelector = new JComboBox<String>(frameNames);
		frameSelector.setName("listeDeroulante");

		// Add components to parent panel
		parentPanel.add(frame1Button);

		// lblVendreProduit.setBounds(362, 52, 325, 50);
		parentPanel.add(lblVendreProduit);
		parentPanel.add(frameSelector);

		// Add action listeners to components
		frame1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openChildFrame1();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frameSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openSelectedChildFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Add a window listener to the frame
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Close the server's input and output streams
				//serverManager.closeStreams();

				// Exit the application
				
				try {
					  if (app.inputStream != null) {
			                try {
			                    app.inputStream.close();
			                } catch (IOException e1) {
			                    e1.printStackTrace();
			                }
			            }

			            // Close the output stream
			            if (app.outputStream != null) {
			                try {
			                    app.outputStream.close();
			                } catch (IOException e2) {
			                    e2.printStackTrace();
			                }
			            }
					app.socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}System.exit(0);
			}
		});

		// Add parent panel to frame
		add(parentPanel);

		setVisible(true);
	}

	private void openChildFrame1() throws IOException {
		setVisible(false);
		this.app.vendre_produit();
	}

	private void openSelectedChildFrame() throws IOException {
		int selectedIndex = frameSelector.getSelectedIndex();
		switch (selectedIndex) {
		case 0:
			setVisible(false);
			this.app.ajouter_produit();

			break;
		case 1:
			setVisible(false);
			this.app.modifier_produit();
			break;
		case 2:
			setVisible(false);
			this.app.supprimer_produit();
			break;
		case 3:
			setVisible(false);
			this.app.consulter_produit();
			break;
		default:
			break;
		}
	}
}
