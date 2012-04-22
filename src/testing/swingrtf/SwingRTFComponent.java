package testing.swingrtf;

import javax.swing.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 22.04.12
 * Time: 20:05
 */
public class SwingRTFComponent {
    public static void main(String[] args) {
        JEditorPane editorPane = new JEditorPane();
        String fileContent = null;
        try {
            fileContent = readFileAsStringUTF8("haslo");
        } catch (IOException e) {
            //
        }
        editorPane.setContentType("text/rtf");
        editorPane.setText(fileContent);

        JFrame j = new JFrame();

        //JTextField textField = new JTextField(fileContent);

        j.add(editorPane);
//        j.add(textField);
        j.pack();
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    private static String readFileAsStringUTF8(String filePath) throws java.io.IOException {
        String s;
        //BufferedReader in = new BufferedReader(new FileReader(filePath));

        //Reader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
        Reader reader = new InputStreamReader(new FileInputStream(filePath), "windows-1250");
        BufferedReader br = new BufferedReader(reader);


        String UTF8Str = null;
        return br.readLine();

//        while ((s = br.readLine()) != null) {
//            UTF8Str = new String(s.getBytes(), "windows-1250");
//        }
//        return UTF8Str;
    }

    private static String readFileAsString(String filePath) throws java.io.IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) try {
                f.close();
            } catch (IOException ignored) {
            }
        }
        return new String(buffer);
    }
}
