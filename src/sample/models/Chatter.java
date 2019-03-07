package sample.models;

import javafx.scene.control.TextField;
import sample.MainFX;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class Chatter {

	public void run(TextField textField, Socket client) throws IOException, ClassNotFoundException {

		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(client.getInputStream());

		while (true) {

			textField.setOnAction(e -> {
				try {
					out.reset();
					out.write(2);
					out.writeObject(new Message("ania", textField.getText()));
					textField.clear();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});


			if (in.available() > 0) {
				if (in.read() == 2) {
					Message message = (Message) in.readObject();
					MainFX.message = message;

				}
			}
		}
	}
}
