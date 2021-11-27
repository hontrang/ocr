import utility.DriveUtility;
import utility.FileUtility;

import java.awt.*;
import java.io.IOException;

public class Main {
    private static final String CREDENTIALS_FILE_PATH = "icauto_asics.json";

    public static void main(String[] args) throws IOException, AWTException {
        FileUtility fileUtility = new FileUtility();
//        while (true) {
//            CaptureScreenUtility.captureScreen();
//            fileUtility.writeFile("code.txt", OCRUtility.doOCR());
        DriveUtility drive = new DriveUtility(CREDENTIALS_FILE_PATH);
        drive.uploadOverride("code.txt", System.getProperty("user.dir") + java.io.File.separator + "code.txt", "*/*");
        sleep(2000);
//        }
    }

    private static void sleep(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
