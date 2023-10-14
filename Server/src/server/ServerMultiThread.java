package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiThread {

	public static void main(String[] args) {
		ServerSocket svs = null;

		int nb_clients = 0;
		try {
			svs = new ServerSocket(2009);

			while (true) {
				Socket s = svs.accept();
				System.out.println("connexion etablie");
				nb_clients++;
				System.out.println("nombre de clients est "+nb_clients);

				ThreadService th = new ThreadService(s);
				th.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
