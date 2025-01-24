package logic.fish;

import logic.Interface.Cookable;
import logic.cooking.CookState;
import utils.Config;
import javafx.scene.layout.Pane;

public class Tuna extends BaseFish {

	public Tuna(Pane gamePane) {
		super("Tuna", gamePane, "fish/tuna.png"); // Call parent NormalFish constructor
		this.speed = Config.TUNA_SPEED; // Set the unique speed for Tuna
	}

	@Override
	public String getName() {
		return "Tuna";
	}

}
