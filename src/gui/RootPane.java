package gui;

import javafx.scene.layout.Pane;
import utils.Goto;

public class RootPane extends Pane {

	private static RootPane instance;
	private PaneState currentPane;

	private RootPane() {
		Goto.setRootPane(this);
		Goto.mainPage();
	}

	public static RootPane getInstance() {
		if (instance == null)
			instance = new RootPane();
		return instance;
	}

	public PaneState getCurrentPane() {
		return currentPane;
	}

	public void setCurrentPane(PaneState currentPane) {
		this.currentPane = currentPane;
	}

	public void update() {
		if (this.currentPane.equals(PaneState.RESTAURANT)) {
			RestaurantPane.getInstance().update();
		}
	}

}
