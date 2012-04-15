package testing.clipboardmonitor;

import clipboard.monitor.ClipboardEvent;
import clipboard.monitor.ClipboardListener;
import clipboard.monitor.windows.WindowsClipboardMonitor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 18.09.11
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class WindowsClipboardMonitorApp implements ClipboardListener {
    public WindowsClipboardMonitorApp() throws InterruptedException {
        System.out.println("Starting.");
        WindowsClipboardMonitor wc = new WindowsClipboardMonitor();
        wc.addListener(this);
        wc.run();
    }

    public static void main(String[] args) throws InterruptedException {
        WindowsClipboardMonitorApp wcApp = new WindowsClipboardMonitorApp();
    }

    public void onEvent(ClipboardEvent event) {
        System.out.println("Nowa zawartosc w schowku!" + getClipboardContents());

        //To change body of implemented methods use File | Settings | File Templates.
    }
    public String getClipboardContents() {
    String result = "";
    System.out.println("AAAA");
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        System.out.println("ABBB");

    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
        System.out.println("CCC");

    boolean hasTransferableText =
      (contents != null) &&
      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
    ;
    if ( hasTransferableText ) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException ex){
        //highly unlikely since we are using a standard DataFlavor
        System.out.println(ex);
        ex.printStackTrace();
      }
      catch (IOException ex) {
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
  }
}
