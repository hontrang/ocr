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
import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) throws IOException, AWTException {
        String credentialsFilePath = "icauto_asics.json";
        String fileName = "code.txt";
        String code = "";
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
            if (scanUntilImageChanged(fileName, screenshot, x, y, width, height)) {
                code = OCRUtility.doOCR(screenshot);
                fileUtility.writeFile(fileName, code);
                pushFileToGoogleDrive(credentialsFilePath, fileName);
                sleep(29000);
            }

        }
    }

    private static void sleep(long timeInMiliSeconds) {
        try {
            Thread.sleep(timeInMiliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getFirstLine(String fileName) {
        FileUtility fileUtility = new FileUtility();
        try {
            return fileUtility.readFile(fileName).get(0);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    private static void pushFileToGoogleDrive(String credentialsFilePath, String fileName) throws IOException, AWTException {
        DriveUtility drive = new DriveUtility(credentialsFilePath);
        try {
            drive.updateFile(fileName, System.getProperty("user.dir") + java.io.File.separator + fileName, "text/plain text");
        } catch (SocketTimeoutException | SSLException | SocketException | GoogleJsonResponseException e) {
            e.printStackTrace();
        }
    }

    private static boolean scanUntilImageChanged(String fileName, String screenshot, int x, int y, int width, int height) throws IOException, AWTException {
        String code = OCRUtility.doOCR(screenshot);
        String currentCode = getFirstLine(fileName);
        while (code.equals(currentCode)) {
            CaptureScreenUtility.captureScreen(x, y, width, height, screenshot);
            code = OCRUtility.doOCR(screenshot);
            sleep(100);
        }
        return true;
    }
}
