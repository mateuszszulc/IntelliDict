package testing.clipboard;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 3/7/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardListenerTestingSecond {

    private static final Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

    static class FlavorListenerCustom implements FlavorListener {

        @Override
        public void flavorsChanged(FlavorEvent flavorEvent) {
System.out.println("FlavorsChanged!");
            processClipboard();
    }
    }
    static FlavorListener fl;
    public static void main(String[] args) throws Exception {
        // The clipboard
        // read clipboard and take ownership to get the FlavorListener notified
        // when the content has changed but the owner has not
        fl = new FlavorListenerCustom();

        processClipboard();

        cb.addFlavorListener(fl);
        // keep thread for testing
        Thread.sleep(100000L);
    }

    public static void processClipboard() {
        // gets the content of clipboard
        Transferable trans = cb.getContents(null);
        if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                // cast to string
                String s = (String) trans
                        .getTransferData(DataFlavor.stringFlavor);
                System.out.println(s);
                // only StringSelection can take ownership, i think
                StringSelection ss = new StringSelection(s);
                // set content, take ownership
                if ( fl == null ) { System.out.println("NULL"); }  else {     System.out.println("NOT NULL"); }
                //cb.removeFlavorListener(fl);
                if ( fl == null ) { System.out.println("NULL 2 "); }  else {     System.out.println("NOT NULL 2"); }

                cb.setContents(ss, ss);
                //cb.addFlavorListener(fl);

            } catch (UnsupportedFlavorException e2) {
                e2.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
