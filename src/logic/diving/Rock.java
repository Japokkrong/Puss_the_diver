package logic.diving;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.Interface.Movable;
import utils.Config;

public class Rock implements Movable {

	private ImageView rockImageView;

	public Rock(Pane gamePane) {
		try {
			Image rockImage = new Image(ClassLoader.getSystemResource("etc/rock.png").toString());
			rockImageView = new ImageView(rockImage);
			rockImageView.setFitWidth(Config.ROCK_SIZE);
			rockImageView.setFitHeight(Config.ROCK_SIZE);

			// Random starting position at the top of the screen
			rockImageView.setX(Math.random() * Config.SCREEN_WIDTH);
			rockImageView.setY(Math.random() * Config.SCREEN_HEIGHT);

			gamePane.getChildren().add(rockImageView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void move() {
		rockImageView.setY(rockImageView.getY() + Config.ROCK_SPEED);
	}

	public boolean isOutOfBounds() {
		return rockImageView.getY() > Config.SCREEN_HEIGHT;
	}

	public ImageView getImageView() {
		return rockImageView;
	}
}