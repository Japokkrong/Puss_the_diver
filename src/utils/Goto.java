package utils;

import java.util.ArrayList;
import java.util.List;

import gui.DivingPane;
import gui.MainMenuPane;
import gui.PaneState;
import gui.RestaurantPane;
import gui.RootPane;
import inventory.FishInventory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameLogic;
import logic.diving.DivingLogic;
import logic.player.Player;

public class Goto {
	private static RootPane rootPane;

	public static void setRootPane(RootPane rootPane) {
		Goto.rootPane = rootPane;
	}

	public static void clear() {
		if (rootPane != null && !rootPane.getChildren().isEmpty()) {
//			List<Node> toRetain = new ArrayList<>(rootPane.getChildren().subList(0, 2));
//			rootPane.getChildren().retainAll(rootPane.getChildren().get(0));
			rootPane.getChildren().clear();
		}

	}

	public static void mainPage() {
		clear();
		BGM.change("bgm/mainmenu.wav", Config.MAINMENU_BGM_VOLUME);
		GameLogic.getInstance().newGame();
		rootPane.getChildren().add(MainMenuPane.getInstance());
		rootPane.setCurrentPane(PaneState.MAINMENU);
	}

	public static void restaurantPage() {
		clear();
		BGM.change("bgm/restaurant.wav", Config.RESTAURANT_BGM_VOLUME);
//		if (!GameLogic.getInstance().isGameStart())
//			GameLogic.getInstance().startGame();
		rootPane.getChildren().add(RestaurantPane.getInstance());
		if (!RestaurantPane.getInstance().getChildren().contains(Player.getInstance().getImageView())) {
			RestaurantPane.getInstance().loadPlayer();
		}
		rootPane.getChildren().add(FishInventory.getInstance().getInventoryPane());
		rootPane.setCurrentPane(PaneState.RESTAURANT);
	}

	public static void divingPage() {
		clear();
		BGM.change("bgm/diving.wav", Config.DIVING_BGM_VOLUME);
		rootPane.getChildren().add(DivingPane.getInstance());
		rootPane.setCurrentPane(PaneState.DIVING);
		if (!DivingPane.getInstance().getChildren().contains(Player.getInstance().getImageView())) {
			DivingPane.getInstance().loadPlayer();
		}
		rootPane.getChildren().add(FishInventory.getInstance().getInventoryPane());
		DivingLogic.getInstance().newGame();
	}

}
