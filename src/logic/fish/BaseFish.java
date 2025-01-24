package logic.fish;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import logic.Interface.Cookable;
import logic.Interface.Movable;
import logic.cooking.CookState;
import utils.Config;

public abstract class BaseFish implements Cookable, Movable {
	protected ImageView imageView; // Make it protected so child classes can use it
	protected double speed; // Default speed for movement
	protected String name;
	protected Image fishImage;
	protected CookState state;
	protected Pane gamePane;

	public BaseFish(String name, Pane gamePane, String imagePath) {
		this.name = name;
		this.state = CookState.RAW;
		this.gamePane = gamePane;
		try {
			// Load the image
			fishImage = new Image(ClassLoader.getSystemResource(imagePath).toString());

			// Initialize the ImageView with the image
			this.imageView = new ImageView(fishImage);
			this.imageView.setX(0);
			this.imageView.setY(Math.random() * Config.SCREEN_HEIGHT);

			this.imageView.setFitWidth(Config.FISH_SIZE);
			this.imageView.setFitHeight(Config.FISH_SIZE);

			// Add to the game pane
			gamePane.getChildren().add(this.imageView);
		} catch (Exception e) {
			System.err.println("Failed to load image: " + e.getMessage());
		}
	}
	
	public void move() {
		this.imageView.setX(this.imageView.getX() + speed);

		if (this.imageView.getX() > gamePane.getWidth()) {
			this.gamePane.getChildren().remove(this.imageView);
		}
	}

	public abstract String getName();

	public ImageView getImageView() {
		return this.imageView;
	}

	public boolean isOutOfBounds() {
		return imageView.getTranslateX() + imageView.getFitWidth() < 0;
	}

	public Image getfishImage() {
		return this.fishImage;
	}

	@Override
	public CookState getCookState() {
		return state;
	}

	@Override
	public void setCookState(CookState state) {
		this.state = state;
	}

}
