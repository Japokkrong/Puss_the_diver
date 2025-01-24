package utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Background Music (BGM) utility class for handling game audio
 */
public class BGM {
	private static Clip clip;
	private static FloatControl volumeControl;

	private static void select(String audioPath) {
		try {
			// Close existing clip
			if (isPlaying()) {
				close();
			}

			URL url = ClassLoader.getSystemResource(audioPath);
			if (url == null) {
				throw new IllegalArgumentException("Audio file not found: " + audioPath);
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioStream);

			// Setup volume control
			if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			}

			audioStream.close();
		} catch (Exception e) {
			System.err.println("Error loading audio: " + audioPath);
			e.printStackTrace();
		}
	}

	private static void play() {
		if (clip != null && !clip.isRunning()) {
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
	}

	private static void close() {
		if (clip != null) {
			clip.stop();
			clip.close();
			clip = null;
		}
	}

	private static boolean isPlaying() {
		return clip != null && clip.isRunning();
	}

	private static void setVolume(float volume) {
		// volume range: -80.0f to 6.0f
		if (volumeControl != null) {
			float newVolume = Math.max(-80.0f, Math.min(6.0f, volume));
			volumeControl.setValue(newVolume);
		}
	}

	public static void change(String audioPath, float volume) {
		select(audioPath);
		setVolume(volume);
		play();
	}
}