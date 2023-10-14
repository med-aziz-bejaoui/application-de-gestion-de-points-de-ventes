package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	int RefProdVL;
	int QteDesireeVL;
	int Validationvente;

	static Socket socket;
	static InterfaceTest interfaceTest;
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream ;

	public void vendre_produit() throws IOException {

		InterfaceVendre Interface1 = new InterfaceVendre();
		Interface1.setVisible(true);

		Controleur controleur = new Controleur(Interface1);

		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());

		Object Produit1 = "vendre_produit";
		outputStream.writeObject(Produit1);
		outputStream.flush();

		// ProduitSession Produit1 = new ProduitSession();
		// Controleur controleur = new Controleur(Interface1);

		Interface1.getBtnNewButton2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object validation = "annuler";
					try {
						outputStream.writeObject(validation);
						outputStream.flush();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Interface1.getRefproduit().setEnabled(true);
					Interface1.getRefproduit().setText("");
					Interface1.removeLabels();
					Interface1.repaint();
					Interface1.getRefproduit().requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		Interface1.getRefproduit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefProdVL = Interface1.DemandeSaisieRefProduit();
				System.out.println(RefProdVL);

				Object RefProd = RefProdVL;
				try {
					outputStream.writeObject(RefProd);
					outputStream.flush();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}

				// Recevoir la réponse du serveur
				boolean verifExactitudeProd = false;
				try {
					verifExactitudeProd = (boolean) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				if (!verifExactitudeProd) {
					JOptionPane.showMessageDialog(Interface1.getRefproduit(), "Produit n'est pas disponible");
					try {
						// Interface1.getRefproduit().setEnabled(true);
						// Interface1.getQtevendu().setEnabled(true);
						Interface1.removeLabels();
						Interface1.repaint();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				} else {
					boolean verifDispoProd = false;
					try {
						verifDispoProd = (boolean) inputStream.readObject();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (!verifDispoProd) {
						JOptionPane.showMessageDialog(Interface1.getQtevendu(),
								"Quantité du produit n'est pas disponible");
					} else {

						Interface1.getRefproduit().setEnabled(false);
						Interface1.addFieldQuantite();
						Interface1.repaint();

						Interface1.getQtevendu().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// VenteSession Vente1 = new VenteSession(Produit1);
								int QteDesireeVL = Interface1.demandesaisieQtedesireeparleclient();

								Object QteDesire = QteDesireeVL;
								try {
									outputStream.writeObject(QteDesire);
									outputStream.flush();
								} catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								// System.out.println("qte desirer" + QteDesireeVL);
								boolean verifPossVente = false;
								try {
									verifPossVente = (boolean) inputStream.readObject();
								} catch (ClassNotFoundException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								if (!verifPossVente) {

									JOptionPane.showMessageDialog(Interface1.getQtevendu(),
											"Quantite demande n'est pas disponible");
									Interface1.removeLabelsValid();
									Interface1.repaint();

								} else {
									Interface1.getQtevendu().setEnabled(false);
									Interface1.addLabels();
									Interface1.repaint();

									// Vente1.CalculerMontantVente(QteDesireeVL);
									// Vente1.AfficherMontantVente(controleur);
									int montant = 0;
									try {
										montant = (int) inputStream.readObject();
									} catch (ClassNotFoundException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									controleur.AfficherMontantVente(montant);

									Interface1.getBtnNewButton1().addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Object validation = "valider";
											try {
												outputStream.writeObject(validation);
												outputStream.flush();
											} catch (IOException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}

											boolean validationVente = false;
											try {
												validationVente = (boolean) inputStream.readObject();
											} catch (ClassNotFoundException | IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}

											if (!validationVente) {
												JOptionPane.showMessageDialog(Interface1.getRefproduit(),
														"erreur d'enregistrement de vente");

											} else {
												JOptionPane.showMessageDialog(Interface1.getRefproduit(),
														"Vente du produit est ajouté avec success");
												Interface1.getRefproduit().setEnabled(true);
												Interface1.getRefproduit().setText("");
												Interface1.removeLabels();
												Interface1.repaint();
												Interface1.dispose();
												interfaceTest.setVisible(true);

											}
										}
									});

								}
							}

						});

					}
				}

			}

		});

	}

	public void ajouter_produit() throws IOException {
		InterfaceAjout ajout = new InterfaceAjout();
		ajout.setVisible(true);

		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());

		Object Produit2 = "ajouter_produit";
		outputStream.writeObject(Produit2);
		outputStream.flush();

		ajout.getBtnNewButton1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ProduitDAO dao = new ProduitDAO();

				List<Object> values = new ArrayList<>();

				values.add(ajout.getIntituleproduit().getText());
				values.add(ajout.getRefproduit().getText());
				values.add(ajout.getPrixunitaire().getText());
				values.add(ajout.getQuantitedispo().getText());
				values.add(ajout.getTauxremise().getText());

				try {
					outputStream.writeObject(values);
					outputStream.flush();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				int test_ajout = 0;
				try {
					test_ajout = (int) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (test_ajout == 0) {
					JOptionPane.showMessageDialog(ajout.getRefproduit(), "Reference de produit existe deja!");
				} else {
					JOptionPane.showMessageDialog(ajout.getRefproduit(), "Produit est ajouté avec success");
					ajout.clearLabels();
					ajout.dispose();
					interfaceTest.setVisible(true);
				}
			}
		});

		ajout.getBtnNewButton2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajout.clearLabels();
			}
		});

	}

	public void modifier_produit() throws IOException {
		InterfaceModifier modif = new InterfaceModifier();
		modif.setVisible(true);
		// ProduitDAO Produit2 = new ProduitDAO();
		// Controleur controleur = new Controleur(modif, Produit2);

		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());

		Object Produit3 = "modifier_produit";
		outputStream.writeObject(Produit3);
		outputStream.flush();

		modif.getBtnNewButton2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object validation = "annuler";
				try {
					outputStream.writeObject(validation);
					outputStream.flush();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					modif.getRefproduit().setEnabled(true);
					modif.getRefproduit().setText("");
					modif.removeLabels();
					modif.repaint();
					modif.getRefproduit().requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		modif.getRefproduit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefProdVL = modif.DemandeSaisieRefProduit();

				Object RefProd = RefProdVL;
				try {
					outputStream.writeObject(RefProd);
					outputStream.flush();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				// Recevoir la réponse du serveur
				boolean verifExactitudeProd = false;
				try {
					verifExactitudeProd = (boolean) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				if (!verifExactitudeProd) {
					JOptionPane.showMessageDialog(modif.getRefproduit(), "Produit n'est pas disponible");

				} else {

					modif.getRefproduit().setEnabled(false);
					modif.addLabels();

					List<Object> values;
					try {
						values = (List<Object>) inputStream.readObject();

						modif.setLabelsModifier(values.get(0).toString(), Integer.parseInt(values.get(1).toString()),
								Integer.parseInt(values.get(2).toString()), Integer.parseInt(values.get(3).toString()));
						modif.repaint();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		modif.getBtnNewButton1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Object validation = "valider";
				try {
					outputStream.writeObject(validation);
					outputStream.flush();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				List<Object> Set_values = new ArrayList<>();

				Set_values.add(modif.getIntituleproduit().getText());
				Set_values.add(modif.getPrixunitaire().getText());
				Set_values.add(modif.getTauxremise().getText());
				Set_values.add(modif.getQuantitedispo().getText());

				try {
					outputStream.writeObject(Set_values);
					outputStream.flush();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				int test_modif = 0;
				try {
					test_modif = (int) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (test_modif == 0) {
					JOptionPane.showMessageDialog(modif.getRefproduit(), "erreur de modification du produit");

				} else {
					JOptionPane.showMessageDialog(modif.getRefproduit(), "produit est mis a jour avec success");
					modif.getRefproduit().setEnabled(true);
					modif.getRefproduit().setText("");
					modif.removeLabels();
					modif.repaint();
					modif.dispose();
					interfaceTest.setVisible(true);

				}
			}
		});
	}

	public void consulter_produit() throws IOException {
		InterfaceConsulter cons = new InterfaceConsulter();
		cons.setVisible(true);
		// ProduitDAO Produit4 = new ProduitDAO();
		// Controleur controleur = new Controleur(cons, Produit4);
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());

		Object Produit4 = "consulter_produit";
		outputStream.writeObject(Produit4);
		outputStream.flush();
		cons.getBtnNewButton2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cons.dispose();
				interfaceTest.setVisible(true);
			}
		});

		cons.getRefproduit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefProdVL = cons.DemandeSaisieRefProduit();
				Object RefProd = RefProdVL;
				try {
					outputStream.writeObject(RefProd);
					outputStream.flush();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				// Recevoir la réponse du serveur
				boolean verifExactitudeProd = false;
				try {
					verifExactitudeProd = (boolean) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				if (!verifExactitudeProd) {
					JOptionPane.showMessageDialog(cons.getRefproduit(), "Produit n'est pas disponible");

				} else {

					cons.getRefproduit().setEnabled(false);
					cons.addLabels();
					List<Object> values;
					try {
						values = (List<Object>) inputStream.readObject();

						cons.setLabelsConsulter(values.get(0).toString(), Integer.parseInt(values.get(1).toString()),
								Integer.parseInt(values.get(2).toString()), Integer.parseInt(values.get(3).toString()));
						cons.repaint();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					cons.setLabelsConsulte();
					cons.repaint();

				}
			}
		});

	}

	public void supprimer_produit() throws IOException {
		InterfaceSupprimer supprimer = new InterfaceSupprimer();
		supprimer.setVisible(true);
		// ProduitDAO Produit3 = new ProduitDAO();
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());

		Object Produit3 = "supprimer_produit";
		outputStream.writeObject(Produit3);
		outputStream.flush();

		supprimer.getRefproduit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefProdVL = supprimer.DemandeSaisieRefProduit();
				Object RefProd = RefProdVL;
				try {
					outputStream.writeObject(RefProd);
					outputStream.flush();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				// Recevoir la réponse du serveur
				boolean verifExactitudeProd = false;
				try {
					verifExactitudeProd = (boolean) inputStream.readObject();
				} catch (ClassNotFoundException | IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				if (!verifExactitudeProd) {
					JOptionPane.showMessageDialog(supprimer.getRefproduit(), "Produit n'est pas disponible");

				} else {

					int confirmed = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr(e) de vouloir supprimer ce produit ?", "Delete Product",
							JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						// Produit3.supprimer();
						Object validation = "valider";
						try {
							outputStream.writeObject(validation);
							outputStream.flush();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						JOptionPane.showMessageDialog(supprimer.getRefproduit(), "Produit est supprimer avec succes");

					} else {
						Object validation = "annuler";
						try {
							outputStream.writeObject(validation);
							outputStream.flush();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						JOptionPane.showMessageDialog(supprimer.getRefproduit(), "Votre produit est protégé.");

					}
					/*
					 * supprimer.getRefproduit().setText(""); supprimer.repaint();
					 */
					supprimer.dispose();
					interfaceTest.setVisible(true);

				}
			}
		});

	}

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//InetAddress adr = InetAddress.getLocalHost();
		InetAddress adr = InetAddress.getByName("192.168.1.14");
		socket = new Socket(adr, 2009);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// app.vendre_produit();

					// Initialize input/output streams
					/**
					 * ObjectOutputStream outputStream = new
					 * ObjectOutputStream(socket.getOutputStream()); outputStream.flush();
					 * ObjectInputStream inputStream = new
					 * ObjectInputStream(socket.getInputStream());
					 */

					Application app = new Application();
					interfaceTest = new InterfaceTest(app);
					
				

					/**
					 * Send client request to the server Object clientRequest = "Client request";
					 * outputStream.writeObject(clientRequest); outputStream.flush();
					 */

					// Receive server response
					/** Object serverResponse = inputStream.readObject(); */

					// Close input/output streams and socket
					/**
					 * outputStream.close(); inputStream.close(); socket.close();
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}