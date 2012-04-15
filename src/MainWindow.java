import dictionaries.PonsService;
import dictionaries.PonsServiceListener;

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
    private PonsService ponsService;
    private TextField textField;
    private MainWindowController mainWindowController;
    private TrayIcon trayIcon;

    public MainWindow() {
        mainFrame = new JFrame();
        ponsService = new PonsService();
        button = new JButton("Press Me");
        textField = new TextField();

        setupSystemTray();

        mainFrame.setLayout(new FlowLayout());
        mainFrame.add(button);
        mainFrame.add(textField);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("B Event dispatch thread = " + SwingUtilities.isEventDispatchThread());
            }
        });

        ponsService.addPonsServiceListener(new PonsServiceListener() {
            @Override
            public void actionPerformed(final String entry) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        displayDictonaryEntry(entry);
                    }
                });
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    private void displayDictonaryEntry(String entry) {
        trayIcon.displayMessage("Pons dictionary Entry", entry, TrayIcon.MessageType.INFO);
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
        URL imageURL = MainWindow.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
