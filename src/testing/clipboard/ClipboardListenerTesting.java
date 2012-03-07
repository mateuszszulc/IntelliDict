package testing.clipboard;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 3/7/12
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClipboardListenerTesting implements FlavorListener, ClipboardOwner {
    private Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    public ClipboardListenerTesting() {
        System.out.println("ClipboardListenerTesting constructor");
        clip.setContents(clip.getContents(null), this);
        clip.addFlavorListener(this);
        try {
            Thread.sleep(100000L);
        }
        catch (InterruptedException e) {

        }
    }

    public static void main(String[] args) {
        ClipboardListenerTesting clt = new ClipboardListenerTesting();
    }

    @Override
    public void flavorsChanged(FlavorEvent e) {
        System.out.println("ClipBoard Changed!!!");
        clip.removeFlavorListener(this);
        clip.setContents(clip.getContents(null), this);
        clip.addFlavorListener(this);

    }

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {
        System.out.println("ownership losted");
    }
}
