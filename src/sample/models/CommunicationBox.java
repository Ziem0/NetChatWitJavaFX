package sample.models;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CommunicationBox {

	private static boolean answer;

	public static boolean display(String message) {

		Stage window = new Stage();

		window.setMinHeight(100);
		window.setMinWidth(100);

		window.initModality(Modality.APPLICATION_MODAL);

		Label label = new Label(message);
		Button yes = new Button("yes");
		Button no = new Button("no");

		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});
		no.setOnAction(e -> {
			answer = false;
			window.close();
		});

		BorderPane lay = new BorderPane();
		lay.setPadding(new Insets(40, 40, 40, 40));
		lay.setTop(label);
		lay.setLeft(yes);
		lay.setRight(no);
		Scene scene = new Scene(lay);
		window.setScene(scene);
		window.showAndWait();

		return answer;
	}

}
