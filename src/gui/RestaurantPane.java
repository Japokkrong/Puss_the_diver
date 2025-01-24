package gui;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.cooking.OrderLogic;
import logic.player.Player;
import utils.Config;
import utils.Goto;

public class RestaurantPane extends BaseGamePane {
	private static RestaurantPane instance;
	private Rectangle cookingObj;
	private Text orderQueueText;
	private Text cookingText;
	private Text gameOverText;
	private Text winText;
	private Rectangle overlay;
	private Button quitButton;

	private RestaurantPane() {
		super();
		setupCooking();
		setupOrderQeue();
		setupGameEnd();

		this.getChildren().addAll(cookingObj, orderQueueText, cookingText, gameOverText, winText, overlay, quitButton);
	}

	protected void loadResource() {
		background = new ImageView(new Image(ClassLoader.getSystemResource("background/restaurantBg.png").toString()));
	}

	private void setupCooking() {
		cookingObj = new Rectangle(251, 306, Color.GREEN);
		cookingObj.setX(1032);
		cookingObj.setY(415);
		cookingObj.setVisible(false);
		cookingText = new Text();
		cookingText.setFill(Color.WHITE);
		cookingText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
		cookingText.setLayoutX(10);
		cookingText.setLayoutY(140);
	}

	private void setupOrderQeue() {
		orderQueueText = new Text("Next Order : " + OrderLogic.getInstance().getOrderQueue().get(0));
		orderQueueText.setFill(Color.WHITE);
		orderQueueText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		orderQueueText.setX(10);
		orderQueueText.setY(20);
	}

	private void setupGameEnd() {
		gameOverText = new Text("GAME OVER");
		gameOverText.setFill(Color.RED);
		gameOverText.setStyle("-fx-font-size: 48px; -fx-font-weight: bold;");
		gameOverText.setX(Config.SCREEN_WIDTH / 2 - 150);
		gameOverText.setY(Config.SCREEN_HEIGHT / 2);
		gameOverText.setVisible(false);
		winText = new Text("YOU WIN!");
		winText.setFill(Color.SPRINGGREEN);
		winText.setStyle("-fx-font-size: 48px; -fx-font-weight: bold;");
		winText.setX(Config.SCREEN_WIDTH / 2 - 100);
		winText.setY(Config.SCREEN_HEIGHT / 2);
		winText.setVisible(false);
		overlay = new Rectangle(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, Color.BLACK);
		overlay.setOpacity(0.7);
		overlay.setVisible(false);
		quitButton = new Button("Quit");
		quitButton.setStyle("-fx-background-color: #8B4513; " + // Brown color
				"-fx-text-fill: white; " + // White text
				"-fx-font-size: 18px; " + // Adjust font size
				"-fx-font-weight: bold; " + // Bold text
				"-fx-border-width: 2px; " + // Border thickness
				"-fx-background-radius: 10px; " +  // Rounded corners
				"-fx-padding: 10px 20px;" // Padding for size adjustment
		);	
		quitButton.setLayoutX(Config.SCREEN_WIDTH / 2 - 20);
		quitButton.setLayoutY(Config.SCREEN_HEIGHT / 2 + 100);
		quitButton.setOnAction(e -> Goto.mainPage());
		quitButton.setVisible(false);
	}

	public static RestaurantPane getInstance() {
		if (instance == null)
			instance = new RestaurantPane();
		return instance;
	}

	public void update() {
		if (Player.getInstance().getX() == 0) {
			Goto.divingPage();
		}
		Platform.runLater(() -> {
			orderQueueText.setText("Next Order : " + OrderLogic.getInstance().getOrderQueue().get(0));
		});
	}

	public Rectangle getCookingObj() {
		return cookingObj;
	}

	public void setCookingObj(Rectangle cookingObj) {
		this.cookingObj = cookingObj;
	}

	public Text getOrderQueueText() {
		return orderQueueText;
	}

	public void setOrderQueueText(Text orderQueueText) {
		this.orderQueueText = orderQueueText;
	}

	public Text getCookingText() {
		return cookingText;
	}

	public void setCookingText(Text cookingText) {
		this.cookingText = cookingText;
	}

	public Text getGameOverText() {
		return gameOverText;
	}

	public void setGameOverText(Text gameOverText) {
		this.gameOverText = gameOverText;
	}

	public Text getWinText() {
		return winText;
	}

	public void setWinText(Text winText) {
		this.winText = winText;
	}

	public Rectangle getOverlay() {
		return overlay;
	}

	public void setOverlay(Rectangle overlay) {
		this.overlay = overlay;
	}

	public Button getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(Button quitButton) {
		this.quitButton = quitButton;
	}

}
