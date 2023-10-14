package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{

	public static void main(String[] args) throws ClassNotFoundException {
		ServerSocket svs = null;
		ObjectOutputStream outputStream=null;
		ObjectInputStream inputStream=null;
		try {
			svs = new ServerSocket(2009); 
			Socket s = svs.accept();
			System.out.println("connexion etablie");
			while (true) {
				
				// Créez les flux d'entrée et de sortie pour recevoir et envoyer des objets
				outputStream = new ObjectOutputStream(s.getOutputStream());
				inputStream = new ObjectInputStream(s.getInputStream());
				 
				// Recevez l'objet du client 
				Object actionProduit = inputStream.readObject();
				String actionProd = (String) actionProduit;
				System.out.println(actionProd);
				if (actionProd.equals("vendre_produit")) {
					Object validationTest;
					boolean test_validation_vente=false;
					do {

						ProduitSession Produit1 = new ProduitSession();
						int RefProdVL;

						do {
							do {
								RefProdVL = (int) inputStream.readObject();
								System.out.println(RefProdVL);
								outputStream.writeObject(
										Produit1.VerificationExactitudeRefProdETRechercheDonneesProduit(RefProdVL));
								outputStream.flush();
							} while (!Produit1.VerificationExactitudeRefProdETRechercheDonneesProduit(RefProdVL));

							outputStream.writeObject(Produit1.VerificationDisponibiliteProduit(RefProdVL));
							outputStream.flush();

						} while (!Produit1.VerificationDisponibiliteProduit(RefProdVL));

						VenteSession Vente1;
						int QteDesire;
						do {
							Vente1 = new VenteSession(Produit1);
							QteDesire = (int) inputStream.readObject();
							System.out.println(QteDesire);

							outputStream.writeObject(Vente1.VerificationPossibiliteVente(QteDesire));
							outputStream.flush();
						} while (!Vente1.VerificationPossibiliteVente(QteDesire));

						outputStream.writeObject(Vente1.CalculerMontantVente());
						outputStream.flush();

						validationTest = inputStream.readObject();
						String validation = (String) validationTest;
						if (validation.equals("valider")) {
							test_validation_vente=Vente1.Validationvente();
							outputStream.writeObject(test_validation_vente);
							outputStream.flush();


						}

					} while (validationTest.equals("annuler")||(!test_validation_vente));

				} else if (actionProd.equals("ajouter_produit")) {
					int test_ajout_produit=0;
					do {
						ProduitDAO dao = new ProduitDAO();

						List<Object> values = (List<Object>) inputStream.readObject();
						test_ajout_produit=dao.ajouter_produit(values.get(0).toString(),
								Integer.parseInt(values.get(1).toString()), Integer.parseInt(values.get(2).toString()),
								Integer.parseInt(values.get(3).toString()),
								Integer.parseInt(values.get(4).toString()));
						outputStream.writeObject(test_ajout_produit);
						outputStream.flush();
					} while (test_ajout_produit==0);

				} else if (actionProd.equals("modifier_produit")) {
					Object modificationTest;
					int test_modification_produit=0;
					do {

						ProduitDAO Produit2 = new ProduitDAO();
						int RefProdVL;

						do {
							RefProdVL = (int) inputStream.readObject();
							System.out.println(RefProdVL);
							outputStream.writeObject(Produit2.verificationExactitudeProd(RefProdVL));
							outputStream.flush();
						} while (!Produit2.verificationExactitudeProd(RefProdVL));

						List<Object> values = new ArrayList<>();

						values.add(Produit2.entity.getIntitule());
						values.add(Produit2.entity.getPrixUn());
						values.add(Produit2.entity.getTauxRemise());
						values.add(Produit2.entity.getQtedispo());
						try {
							outputStream.writeObject(values);
							outputStream.flush();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						modificationTest = inputStream.readObject();
						String validation = (String) modificationTest;
						if (validation.equals("valider")) {
							List<Object> Set_values = (List<Object>) inputStream.readObject();
							test_modification_produit=Produit2.modifierproduit(Set_values.get(0).toString(), Integer.parseInt(Set_values.get(1).toString()),
									Integer.parseInt(Set_values.get(2).toString()),
							        Integer.parseInt(Set_values.get(3).toString()));
							outputStream.writeObject(test_modification_produit);
							outputStream.flush();

						}
					} while (modificationTest.equals("annuler")||(test_modification_produit==0));

				}else if (actionProd.equals("supprimer_produit")) {
						ProduitDAO Produit3 = new ProduitDAO();

						int RefProdVL;

						do {
							RefProdVL = (int) inputStream.readObject();
							System.out.println(RefProdVL);
							outputStream.writeObject(Produit3.verificationExactitudeProd(RefProdVL));
							outputStream.flush();
						} while (!Produit3.verificationExactitudeProd(RefProdVL));
						
						Object validationTest = inputStream.readObject();
						String validation = (String) validationTest;
						if (validation.equals("valider")) {
							
							Produit3.supprimer();

						}

				}else if (actionProd.equals("consulter_produit")) {
						ProduitDAO Produit4 = new ProduitDAO();

						int RefProdVL;

						do {
							RefProdVL = (int) inputStream.readObject();
							System.out.println(RefProdVL);
							outputStream.writeObject(Produit4.verificationExactitudeProd(RefProdVL));
							outputStream.flush();
						} while (!Produit4.verificationExactitudeProd(RefProdVL));
						
						List<Object> values = new ArrayList<>();

						values.add(Produit4.entity.getIntitule());
						values.add(Produit4.entity.getPrixUn());
						values.add(Produit4.entity.getTauxRemise());
						values.add(Produit4.entity.getQtedispo());
						try {
							outputStream.writeObject(values);
							outputStream.flush();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						

				}else {
					/*inputStream.close();
					outputStream.close();
					s.close();*/
				}
			}
		}  catch (EOFException e) {
            // Client closed the connection unexpectedly
             // Exit the loop and terminate the server
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
