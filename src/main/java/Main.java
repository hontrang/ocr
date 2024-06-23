import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import utility.CaptureScreenUtility;
import utility.DriveUtility;
import utility.FileUtility;
import utility.OCRUtility;

import javax.net.ssl.SSLException;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Main {
    private static String code = "";

    public static void main(String[] args) throws IOException, AWTException {
        String credentialsFilePath = "credentials.json";
        String fileName = "code.txt";
        DriveUtility drive;
        FileUtility fileUtility = new FileUtility();

        int x = 0;
        int y = 0;
        int width = 600;
        int height = 200;
        String arg;
        String screenshot = "Screenshot.png";
        for (int i = 0; i < args.length; i++) {
            arg = args[i];
            if (arg.equals("--resolution") || arg.equals("-r")) {
                x = Integer.valueOf(args[i + 1]);
                y = Integer.valueOf(args[i + 2]);
                width = Integer.valueOf(args[i + 3]);
                height = Integer.valueOf(args[i + 4]);
            }
            if (arg.equals("-c")) {
                credentialsFilePath = args[i + 1];
            }
            if (arg.equals("-n")) {
                fileName = args[i + 1];
            }
            if (arg.equals("-s")) {
                screenshot = args[i + 1];
            }
        }
        while (true) {
            CaptureScreenUtility.captureScreen(x, y, width, height, screenshot);
            fileUtility.writeFile(fileName, OCRUtility.doOCR(screenshot));
            drive = new DriveUtility(credentialsFilePath);
            try {
                drive.updateFile(fileName, System.getProperty("user.dir") + java.io.File.separator + fileName, "text/plain text");
            } catch (SocketTimeoutException | SSLException | SocketException | GoogleJsonResponseException e) {
                e.printStackTrace();
            }
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
