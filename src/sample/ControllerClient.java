package sample;

import javafx.scene.control.TextField;
import sample.models.Chatter;

import java.io.IOException;
import java.net.Socket;

public class ControllerClient extends Chatter {


	public void start(TextField textField, String name) throws IOException, ClassNotFoundException {
		try (Socket client = new Socket("localhost", 9000)) {

			System.out.println("Connection established");

			run(textField, client, name);

		}
	}
}
