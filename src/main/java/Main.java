import utility.CaptureScreenUtility;
import utility.DriveUtility;
import utility.FileUtility;
import utility.OCRUtility;

import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String CREDENTIALS_FILE_PATH = "icauto_asics.json";

    public static void main(String[] args) throws IOException, AWTException {
        int x;
        int y;
        int width;
        int height;
        if (args.length > 0) {
            x = Integer.valueOf(args[0]);
            y = Integer.valueOf(args[1]);
            width = Integer.valueOf(args[2]);
            height = Integer.valueOf(args[3]);
        } else {
            x = 70;
            y = 70;
            width = 600;
            height = 200;
        }
        FileUtility fileUtility = new FileUtility();
        while (true) {
            CaptureScreenUtility.captureScreen(x, y, width, height);
            fileUtility.writeFile("code.txt", OCRUtility.doOCR());
            DriveUtility drive = new DriveUtility(CREDENTIALS_FILE_PATH);
            drive.updateFile("code.txt", System.getProperty("user.dir") + java.io.File.separator + "code.txt", "*/*");
            sleep(2000);
        }
    }

    private static void sleep(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
