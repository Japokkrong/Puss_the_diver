package application;

import gui.RootPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.GameLogic;
import utils.Config;
import utils.Input;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		Scene scene = new Scene(RootPane.getInstance(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		scene.setOnKeyPressed((KeyEvent event) -> {
			Input.setKeyPressed(event.getCode(), true);
		});
		scene.setOnKeyReleased((KeyEvent event) -> {
			Input.setKeyPressed(event.getCode(), false);
		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("PUSS THE DRIVER");
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		AnimationTimer gameLoop = new AnimationTimer() {
			public void handle(long now) {
//				RootPane.getInstance().update();
				Platform.runLater(() -> {
					RootPane.getInstance().update();
					GameLogic.getInstance().logicUpdate();
				});
			}
		};

		gameLoop.start();
	}
}
