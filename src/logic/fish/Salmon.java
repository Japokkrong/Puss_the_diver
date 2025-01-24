package logic.fish;

import logic.Interface.Cookable;
import logic.cooking.CookState;
import utils.Config;
import javafx.scene.layout.Pane;

public class Salmon extends BaseFish {

	public Salmon(Pane gamePane) {
		super("Salmon", gamePane, "fish/salmon.png"); // Call parent class constructor
		this.speed = Config.SALMON_SPEED; // Set Salmon's specific speed
	}

	@Override
	public String getName() {
		return "Salmon";
	}

}
