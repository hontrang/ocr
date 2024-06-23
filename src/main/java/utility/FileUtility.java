package utility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/5/2017.
 */

public class FileUtility {
    private File file;
    private String line = null;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;
    private PrintWriter pw;

    public List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<String>();
        try {
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            try {
                while ((line = br.readLine()) != null)
                    lines.add(line);
                fr.close();
                br.close();
            } catch (IOException ioex) {
            }

        } catch (FileNotFoundException ex) {
        }
        return lines;
    }

    public void writeFile(String filePath, String... lines) {
        file = new File(filePath);
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (String shipment : lines)
                bw.write(shipment);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String path, String... content) {
        try {
            file = new File(path);
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8));
            for (String str : content)
                bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    public void copyFile(String from, String to, boolean overwrite) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);

        if (!fromFile.exists()) {
            throw new IOException("File not found: " + from);
        }
        if (!fromFile.isFile()) {
            throw new IOException("Cannot copy directories: " + from);
        }
        if (!fromFile.canRead()) {
            throw new IOException("Cannot read file: " + from);
        }

        if (toFile.isDirectory()) {
            toFile = new File(toFile, fromFile.getName());
        }

        if (toFile.exists() && !overwrite) {
            throw new IOException("File already exists: " + toFile.getPath());
        } else {
            String parent = toFile.getParent();
            if (parent == null) {
                parent = System.getProperty("user.dir");
            }
            File dir = new File(parent);
            if (!dir.exists()) {
                throw new IOException("Destination directory does not exist: " + parent);
            }
            if (dir.isFile()) {
                throw new IOException("Destination is not a valid directory: " + parent);
            }
            if (!dir.canWrite()) {
                throw new IOException("Cannot write to destination: " + parent);
            }
        }

        try (FileInputStream fis = new FileInputStream(fromFile);
             FileOutputStream fos = new FileOutputStream(toFile)) {
             
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Error occurred while copying file: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println(path + " deleted");
        } else {
            System.out.println(path + " doesn't exists");
        }
    }

    public void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }

    }

    public void createFolder(File folder) {
        if (!folder.isDirectory())
            folder.mkdirs();
    }

    public void appendFile(String filePath, String text) {
        file = new File(filePath);
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
