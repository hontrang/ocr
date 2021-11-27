package utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureScreenUtility {
    private static final int SCREEN_ORDER = 0;

    public static void captureScreen() throws IOException, AWTException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        Rectangle screenBounds = screens[SCREEN_ORDER].getDefaultConfiguration().getBounds();
        // debug and get screenBounds value to define below parameters
        screenBounds.setBounds(1930, -440, 600, 200);
        BufferedImage capture = new Robot().createScreenCapture(screenBounds);
        File imageFile = new File("Screenshot.png");
        ImageIO.write(capture, "png", imageFile);
    }
}
