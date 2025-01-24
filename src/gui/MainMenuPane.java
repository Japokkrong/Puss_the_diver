package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import logic.GameLogic;
import utils.Config;
import utils.GameDifficulty;
import utils.Goto;

public class MainMenuPane extends Pane {
	private static MainMenuPane instance;
	private ImageView background;
	private Label gameLogo;
	private Button startButton;
	private Button easyButton;
	private Button mediumButton;
	private Button hardButton;
	private Button hellButton;
	private HBox difficultyBox;

	private MainMenuPane() {
		loadResource();
		setupBackground();
		setupGameLogo();
		setupStartButton();
		setupDifficultyButtons();
		setupButtonActions();
		this.getChildren().addAll(background, gameLogo, startButton, difficultyBox);
	}

	private void setupDifficultyButtons() {
		String buttonStyle = "-fx-background-color: #8B4513; " + "-fx-text-fill: white; " + "-fx-font-size: 18px; "
				+ "-fx-font-weight: bold; " + "-fx-border-color: #3B5323; " + "-fx-border-width: 2px; "
				+ "-fx-background-radius: 10px; " + "-fx-padding: 10px 20px;";

		easyButton = new Button("EASY");
		mediumButton = new Button("MEDIUM");
		hardButton = new Button("HARD");
		hellButton = new Button("HELL");

		final Button[] difficultyButtons = { easyButton, mediumButton, hardButton, hellButton };

		for (Button button : difficultyButtons) {
			button.setStyle(buttonStyle);
		}

		// Create HBox for buttons
		difficultyBox = new HBox(20); // 20px spacing between buttons
		difficultyBox.setAlignment(Pos.CENTER);
		difficultyBox.getChildren().addAll(difficultyButtons);
		difficultyBox.setLayoutY(Config.SCREEN_HEIGHT / 2);

		// Center HBox horizontally
		difficultyBox.layoutXProperty().bind(this.widthProperty().subtract(difficultyBox.widthProperty()).divide(2));
		difficultyBox.setVisible(false);
	}

	private void setupButtonActions() {
		startButton.setOnAction(e -> {
			startButton.setVisible(false);
			difficultyBox.setVisible(true);
		});

		easyButton.setOnAction(e -> {
			// Handle EASY difficulty selection
			GameDifficulty.easyGame();
			difficultyBox.setVisible(false);
			startButton.setVisible(true);
			GameLogic.getInstance().startGame();
			Goto.restaurantPage();
		});

		mediumButton.setOnAction(e -> {
			// Handle MEDIUM difficulty selection
			GameDifficulty.mediumGame();
			difficultyBox.setVisible(false);
			startButton.setVisible(true);
			GameLogic.getInstance().startGame();
			Goto.restaurantPage();
		});

		hardButton.setOnAction(e -> {
			// Handle HARD difficulty selection
			GameDifficulty.hardGame();
			difficultyBox.setVisible(false);
			startButton.setVisible(true);
			GameLogic.getInstance().startGame();
			Goto.restaurantPage();
		});

		hellButton.setOnAction(e -> {
			// Handle HELL difficulty selection
			GameDifficulty.hellGame();
			difficultyBox.setVisible(false);
			startButton.setVisible(true);
			GameLogic.getInstance().startGame();
			Goto.restaurantPage();
		});
	}

	private void loadResource() {
		background = new ImageView(new Image(ClassLoader.getSystemResource("background/mainMenuBg.jpg").toString()));
	}

	public static MainMenuPane getInstance() {
		if (instance == null) {
			instance = new MainMenuPane();
		}
		return instance;
	}

	private void setupBackground() {
		background.setFitWidth(Config.SCREEN_WIDTH);
		background.setFitHeight(Config.SCREEN_HEIGHT);
		this.setPrefWidth(Config.SCREEN_WIDTH);
		this.setPrefHeight(Config.SCREEN_HEIGHT);
	}

	private void setupGameLogo() {
		gameLogo = new Label("Puss The Diver");
		gameLogo.setStyle("-fx-text-fill: yellow; " + // White text color
				"-fx-font-size: 70px; " + // Font size
				"-fx-font-weight: bold; " + // Bold text
				"-fx-effect: dropshadow(gaussian, black, 5, 0.5, 2, 2);" // Subtle shadow effect
		);
		gameLogo.setAlignment(Pos.CENTER);
		gameLogo.setLayoutX(Config.SCREEN_WIDTH / 2 - 240); // Centered horizontally above the button
		gameLogo.setLayoutY(Config.SCREEN_HEIGHT / 2 - 200); // Position above the start button

	}

	private void setupStartButton() {
		startButton = new Button("start");
		startButton.setStyle("-fx-background-color: #8B4513; " + // Brown color
				"-fx-text-fill: white; " + // White text
				"-fx-font-size: 18px; " + // Adjust font size
				"-fx-font-weight: bold; " + // Bold text
				"-fx-border-color: #3B5323; " + // Greenish border to match ocean vegetation
				"-fx-border-width: 2px; " + // Border thickness
				"-fx-background-radius: 10px; " + // Rounded corners
				"-fx-padding: 10px 20px;" // Padding for size adjustment
		);
		startButton.setAlignment(Pos.CENTER);
		startButton.setLayoutX(Config.SCREEN_WIDTH / 2 - 50);
		startButton.setLayoutY(Config.SCREEN_HEIGHT / 2);

	}
}