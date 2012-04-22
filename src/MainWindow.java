import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import controllers.MainWindowController;
import controllers.TrayController;
import dataproviders.HibernateSessionFactoryManager;
import dictionaries.PonsService;
import dictionaries.PonsServiceListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    private TrayController trayController = new TrayController();
    private Provider provider;

    public MainWindow() {
        HibernateSessionFactoryManager.getSessionFactory();
        setupServiceProviders();
        setupGUI();
    }

    private void setupServiceProviders() {
        ponsService = new PonsService();
        provider = Provider.getCurrentProvider(true);
        ponsService.start();

        setupKeyListener();

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
    }

    private void setupGUI() {
        mainFrame = new JFrame();
        button = new JButton("Press Me");
        textField = new TextField();
        mainFrame.setLayout(new FlowLayout());
        mainFrame.add(button);
        mainFrame.add(textField);
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


    void setupKeyListener() {
        final ArrayList<KeyStroke> strokes = new ArrayList<KeyStroke>();

        int modifiers = 0;
        modifiers |= InputEvent.SHIFT_DOWN_MASK;
        modifiers |= InputEvent.ALT_DOWN_MASK;
        modifiers |= InputEvent.CTRL_DOWN_MASK;

        strokes.add(KeyStroke.getKeyStroke(KeyEvent.VK_1, modifiers));

        for (final KeyStroke stroke : strokes) {
            provider.register(stroke, new HotKeyListener() {
                public void onHotKey(HotKey hotKey) {
                    System.out.println("Oh yeah event!");

                    if (stroke.equals(hotKey.keyStroke)) {
                        System.out.println("YEEEEEEEEEEEEAH PASS");
                    } else {
                        System.out.println("FAILED");
                    }
                }
            });
        }
    }

    private void displayDictonaryEntry(String entry) {
        trayController.displayInfoMessage(entry);
    }
}
