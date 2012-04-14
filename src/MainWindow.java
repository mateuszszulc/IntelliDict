import clipboard.monitor.ClipboardEvent;
import clipboard.monitor.ClipboardListener;
import clipboard.monitor.windows.WindowsClipboardMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

    public MainWindow() {
        System.out.println("0 Event dispatch thread = " + SwingUtilities.isEventDispatchThread());

        WindowsClipboardMonitor wc = new WindowsClipboardMonitor();
        wc.addListener(new ClipboardListener() {
            @Override
            public void onEvent(ClipboardEvent event) {

                System.out.println("Works!!!!! = " + getClipboardContents());
            }
        });
        wc.start();

        mainFrame = new JFrame();
        System.out.println("1 Event dispatch thread = " + SwingUtilities.isEventDispatchThread());

        button = new JButton("Press Me");
        System.out.println("2 Event dispatch thread = " + SwingUtilities.isEventDispatchThread());

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

    public String getClipboardContents() {
        String result = "";
        System.out.println("1 Event dispatch thread = " + SwingUtilities.isEventDispatchThread());

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);

        boolean hasTransferableText =
                (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);

        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                //highly unlikely since we are using a standard DataFlavor
                System.out.println(ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }
}
