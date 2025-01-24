package logic.fish;

import logic.cooking.CookState;
import utils.Config;
import javafx.scene.layout.Pane;

public class BlueDiscus extends BaseFish {

	public BlueDiscus(Pane gamePane) {
		super("BlueDiscus", gamePane, "fish/BlueDiscus.png");
		this.speed = Config.BLUEDISCUS_SPEED; // Set unique movement speed for BlueDiscus
	}

	@Override
	public String getName() {
		return "BlueDiscus";
	}

}
