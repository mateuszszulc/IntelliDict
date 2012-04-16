import controllers.MainWindowController;
import controllers.TrayController;
import dictionaries.PonsService;
import dictionaries.PonsServiceListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainWindow() {
        mainFrame = new JFrame();
        ponsService = new PonsService();
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
        trayController.displayInfoMessage(entry);
    }
}
