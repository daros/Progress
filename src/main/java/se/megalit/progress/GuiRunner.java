package se.megalit.progress;

import se.megalit.progress.worker.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 16, 2010
 * Time: 11:40:58 PM
 */
public class GuiRunner {
    private JButton startWorkButton;
    private JTextField totalWorktimeField;
    private JPanel jPanel;

    public GuiRunner() {
        startWorkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String buttonLabel = startWorkButton.getText();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        startWorkButton.setText("Working...");
                        totalWorktimeField.setText("...");
                    }
                });
                SwingWorker<Long, Void> worker =
                        new SwingWorker<Long, Void>() {

                            public Long doInBackground() {
                                long start = System.currentTimeMillis();
                                new Worker().work();
                                long end = System.currentTimeMillis();
                                return end - start;
                            }

                            public void done() {
                                try {
                                    long total = get();

                                    startWorkButton.setText(buttonLabel);
                                    totalWorktimeField.setText(total + " ms");
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                } catch (ExecutionException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        };
                worker.execute();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuiRunner");
        frame.setContentPane(new GuiRunner().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerOnScreen(frame);
        frame.setVisible(true);
    }

    private static void centerOnScreen(JFrame window) {
        int width = window.getWidth();
        int height = window.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width / 2) - (width / 2); // Center horizontally.
        int y = (screen.height / 2) - (height / 2); // Center vertically.

        window.setBounds(x, y, width, height);
    }
}
