package utils;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class Input {
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keycode)) {
				keyPressed.add(keycode);
			}
		} else {
			keyPressed.remove(keycode);
		}
		System.out.println(keyPressed);
	}

}
