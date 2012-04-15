import dictionaries.PonsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 06.12.11
 * Time: 21:26
 */
public class MainWindow {
    private JFrame mainFrame;
    private JButton button;
    private PonsService ponsService ;

    public MainWindow() {
        mainFrame = new JFrame();
        ponsService = new PonsService();
        button = new JButton("Press Me");

        setupSystemTray();

        mainFrame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("B Event dispatch thread = " + SwingUtilities.isEventDispatchThread());
            }
        });
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    private void setupSystemTray() {
        if (!SystemTray.isSupported()) return;
        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon trayIcon = new TrayIcon(createImage("bulb.gif", "IntellijDict"));
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = MainWindow.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
