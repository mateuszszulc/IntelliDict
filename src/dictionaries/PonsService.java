package dictionaries;

import clipboard.monitor.ClipboardEvent;
import clipboard.monitor.ClipboardListener;
import clipboard.monitor.windows.WindowsClipboardMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 15.04.12
 * Time: 12:15
 */
public class PonsService {
    //responsible for filtering/validation Clipboard content
    //private ClipboardService clipboardService;

    private List<PonsServiceListener> listeners = new ArrayList<PonsServiceListener>();
    private WindowsClipboardMonitor wc = new WindowsClipboardMonitor();

    public PonsService() {
        wc.addListener(new ClipboardListener() {
            @Override
            public void onEvent(ClipboardEvent event) {
                String currentClipboardContent = getClipboardContents();
                System.out.println("Works!!!!! = " + currentClipboardContent);
                for ( PonsServiceListener listener : listeners ) {
                    listener.actionPerformed(currentClipboardContent);
                }
            }
        });
    }

    public void addPonsServiceListener(PonsServiceListener listener) {
        listeners.add(listener);
    }

    public void removePonsServiceListener(PonsServiceListener listener) {
        listeners.remove(listener);
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
    public void start() {
          wc.start();
    }
}
