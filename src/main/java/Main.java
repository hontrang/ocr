import utility.CaptureScreenUtility;
import utility.DriveUtility;
import utility.FileUtility;
import utility.OCRUtility;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Main {
    private static final String CREDENTIALS_FILE_PATH = "icauto_asics.json";

    public static void main(String[] args) throws IOException, AWTException {
        DriveUtility drive;
        int x = 0;
        int y = 0;
        int width = 600;
        int height = 200;
        String arg;
        FileUtility fileUtility = new FileUtility();
        for (int i = 0; i < args.length; i++) {
            arg = args[i];
            if (arg.equals("--resolution") || arg.equals("-r")) {
                x = Integer.valueOf(args[i + 1]);
                y = Integer.valueOf(args[i + 2]);
                width = Integer.valueOf(args[i + 3]);
                height = Integer.valueOf(args[i + 4]);
            }
        }
        while (true) {
            CaptureScreenUtility.captureScreen(x, y, width, height);
            fileUtility.writeFile("code.txt", OCRUtility.doOCR());
            drive = new DriveUtility(CREDENTIALS_FILE_PATH);
            try {
                drive.updateFile("code.txt", System.getProperty("user.dir") + java.io.File.separator + "code.txt", "text/plain text");
            } catch (SocketTimeoutException | SSLException | SocketException e) {
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
