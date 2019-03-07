package sample;


import javafx.scene.control.TextField;
import sample.models.Chatter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControllerServer extends Chatter {

	public void start(TextField textField) throws IOException, ClassNotFoundException {

		try (ServerSocket server = new ServerSocket(9000);
		     Socket client = server.accept()) {

			System.out.println("Connection established");

			run(textField, client);


		}


	}


}
