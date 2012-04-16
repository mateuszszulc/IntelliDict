package controllers;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 15.04.12
 * Time: 15:50
 */
public class TrayController {
    private TrayIcon trayIcon;

    public TrayController() {
        setupSystemTray();
    }

    private void setupSystemTray() {
        if (!SystemTray.isSupported()) return;
        SystemTray tray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(createImage("bulb.gif", "IntellijDict"));
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = TrayController.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    public void displayInfoMessage(String entry) {
        trayIcon.displayMessage("Pons Dictionary", entry, TrayIcon.MessageType.INFO );
    }
}
