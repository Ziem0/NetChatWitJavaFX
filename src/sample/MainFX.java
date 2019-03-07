package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.CommunicationBox;
import sample.models.Message;
import sample.models.WelcomeBox;

import java.io.IOException;

public class MainFX extends Application {

	public static Message message = new Message("","");

	Scene scene1;
	Scene scene0;
	Label label;
	TextField textField;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		//first settings
		Stage window = primaryStage;
		window.setTitle("NetChatFX");
		window.setOnCloseRequest(e -> {
			e.consume();
			if (CommunicationBox.display("Do you really wanto to exit?")) {
				window.close();
				System.exit(0);
			}
		});

		//scene1
		ToggleGroup colorGroup = new ToggleGroup();
		RadioMenuItem black = new RadioMenuItem("black");
		black.setToggleGroup(colorGroup);
		black.setOnAction(e->{
			if (black.isSelected()) {
				textField.getStyleClass().add("blackB");
			}
		});
		RadioMenuItem white = new RadioMenuItem("white");
		white.setToggleGroup(colorGroup);
		white.setSelected(true);
		white.setOnAction(e->{
			if (white.isSelected()) {
				textField.getStyleClass().add("whiteB");
			}
		});
		RadioMenuItem blue = new RadioMenuItem("blue");
		blue.setToggleGroup(colorGroup);
		blue.setOnAction(e->{
			if (blue.isSelected()) {
				textField.getStyleClass().add("blueB");
			}
		});
		Menu color = new Menu("_Color");
		color.getItems().addAll(black, white, blue);

		MenuItem exit = new MenuItem("Close program");
		exit.setOnAction(e->{
			e.consume();
			if (CommunicationBox.display("Do you really want to exit?")) {
				window.close();
				System.exit(0);
			}
		});
		MenuItem editName = new MenuItem("Edit name");
		editName.setOnAction(e->{

		});
		Menu edit = new Menu("_Edit");
		edit.getItems().add(editName);
		edit.getItems().add(new SeparatorMenuItem());
		edit.getItems().add(exit);
		MenuBar bar = new MenuBar();
		GridPane.setConstraints(bar,0,0);
		bar.getMenus().addAll(edit, color);

		label = new Label();
		GridPane.setConstraints(label, 1,1);
		textField = new TextField();
		GridPane.setConstraints(textField,1,5);
		GridPane layout1 = new GridPane();
		layout1.setPadding(new Insets(20,20,20,20));
		layout1.setVgap(10);
		layout1.setHgap(10);
		layout1.getChildren().addAll(bar, label, textField);
		scene1 = new Scene(layout1, 500, 150);
		scene1.getStylesheets().add("style.css");

		//scene0
		Button login = new Button("login");
		login.setOnAction(e -> {
			String[] data = WelcomeBox.start();
			if (data[0].length()>0) {
				if (data[1].equals("true")) {
					setServer(textField, data[0]);
					window.setScene(scene1);
				} else {
					setClient(textField, data[0]);
					window.setScene(scene1);
				}
			}
		});
		VBox layout0 = new VBox(30);
		layout0.getChildren().add(login);
		layout0.setAlignment(Pos.CENTER);
		scene0 = new Scene(layout0, 150, 150);
//		scene0.getStylesheets().add("style.css");

		//settings
		window.setScene(scene0);
		window.show();

		//LabelReader
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

	private void setServer(TextField textField, String name) {
		Thread t1 = new Thread(() -> {
			ControllerServer server = new ControllerServer();
			try {
				server.start(textField, name);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		t1.start();
	}

	private void setClient(TextField textField, String name) {
		Thread t2 = new Thread(() -> {
			ControllerClient client = new ControllerClient();
			try {
				client.start(textField, name);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		t2.start();
	}
}
