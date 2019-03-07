package sample.models;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WelcomeBox {

	public static String[] start() {

		Stage window = new Stage();
		window.setTitle("Welcome!");

		String[] data = new String[2];
		Label label2 = new Label("Do you want to create your own channel?");
		label2.setDisable(true);
		Button yes = new Button("yes");
		yes.setDisable(true);
		yes.setOnAction(e -> {
			data[1] = "true";
			window.close();
		});
		Button no = new Button("no");
		no.setDisable(true);
		no.setOnAction(e -> {
			data[1] = "false";
			window.close();
		});

		GridPane layout = new GridPane();
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setHgap(10);
		layout.setVgap(10);

		Label label1 = new Label("Please type your name: ");
		GridPane.setConstraints(label1,1,0);
		TextField name = new TextField();
		name.setPromptText("enter your name..");
		GridPane.setConstraints(name,1,1);
		Button add = new Button("add");
		GridPane.setConstraints(add,2,1);
		add.setOnAction(e -> {
			data[0] = name.getText();
			name.setDisable(true);
			label2.setDisable(false);
			yes.setDisable(false);
			no.setDisable(false);
		});

		GridPane.setConstraints(label2,1,2);
		GridPane.setConstraints(yes,0,3);
		GridPane.setConstraints(no,2,3);
		layout.getChildren().addAll(label1, name, add, label2, yes, no);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return data;
	}
}
