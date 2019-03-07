package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Message;

import java.io.IOException;

public class MainFX extends Application {

	public static Message message = new Message("","");

	Label label;
	TextField textField;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Stage window = primaryStage;
		window.setTitle("NetChatFX");

		label = new Label();
		textField = new TextField();

//		setServer(textField);
		setClient(textField);

		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, textField);


		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.show();

		initInputThread();
	}

	private void initInputThread() {

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() {
				while (true) {
					if (!label.getText().equals(message.toString())) {
						Platform.runLater(() -> label.setText(message.toString()));
					}
				}
			}
		};

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}


	private void setServer(TextField textField) {
		Thread t1 = new Thread(() -> {
			ControllerServer server = new ControllerServer();
			try {
				server.start(textField);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		t1.start();
	}

	private void setClient(TextField textField) {
		Thread t2 = new Thread(() -> {
			ControllerClient client = new ControllerClient();
			try {
				client.start(textField);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		t2.start();
	}
}
