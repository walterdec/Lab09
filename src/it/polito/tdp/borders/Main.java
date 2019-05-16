package it.polito.tdp.borders;

import it.polito.tdp.borders.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Model model = new Model();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Borders.fxml"));
			BorderPane root = (BorderPane) loader.load();
			BordersController controller = loader.getController();
			controller.setModel(model);

			// TODO set model

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
