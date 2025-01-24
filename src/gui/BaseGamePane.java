package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.player.Player;
import utils.Config;

public abstract class BaseGamePane extends Pane {
	protected ImageView background;
	protected Text timerText;

	protected BaseGamePane() {
		loadResource();
		setupPane();
		setupTimerText();
		this.getChildren().addAll(background, timerText);
		loadPlayer();
	}

	private void setupPane() {
		background.setFitWidth(Config.SCREEN_WIDTH);
		background.setFitHeight(Config.SCREEN_HEIGHT);
		this.setPrefWidth(Config.SCREEN_WIDTH);
		this.setPrefHeight(Config.SCREEN_HEIGHT);
	}

	protected void setupTimerText() {
		timerText = new Text();
		timerText.setFill(javafx.scene.paint.Color.WHITE);
		timerText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		timerText.setX(Config.SCREEN_WIDTH - 100);
		timerText.setY(20);
	}

	protected abstract void loadResource();

	public void loadPlayer() {
		Player.getInstance().setPos(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 1.25 + 10);
		this.getChildren().add(Player.getInstance().getImageView());
	}

	public Text getTimerText() {
		return timerText;
	}

	public void setTimerText(Text timerText) {
		this.timerText = timerText;
	}
}
