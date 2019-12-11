package zadanie.graphics.listeners;

import com.sun.javafx.logging.JFRInputEvent;
import com.sun.media.jfxmediaimpl.HostUtils;
import zadanie.graphics.JOurFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readListener {

    public static String readFromFile(String pathToFile) {
        String file = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line.replaceAll("\n", "");
                file += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(file);

        return file;
    }

    public static String getPath(JRootPane frame) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "TXT");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

}
