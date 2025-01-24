package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.diving.DivingLogic;

public class DivingPane extends BaseGamePane {
    private static DivingPane instance;

    private DivingPane() {
        super();
        timerText.setText("Time: " + DivingLogic.getInstance().getRemainingTime());
    }

    @Override
    protected void loadResource() {
        background = new ImageView(new Image(ClassLoader.getSystemResource("background/diving.jpg").toString()));
    }

    public static DivingPane getInstance() {
        if (instance == null)
            instance = new DivingPane();
        return instance;
    }
}
