package se.megalit.progress;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.megalit.progress.configuration.ApplicationConfiguration;
import se.megalit.progress.worker.IWorker;
import se.megalit.progress.worker.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 19, 2010
 * Time: 12:16:05 AM
 */
public class SpringGuiRunner {
    private JButton startWorkButton;
    private JTextField totalWorktimeField;
    private JPanel jPanel;

    public SpringGuiRunner() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final IWorker worker = ctx.getBean(Worker.class);

        startWorkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String buttonLabel = startWorkButton.getText();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        startWorkButton.setText("Working...");
                        totalWorktimeField.setText("...");
                    }
                });
                SwingWorker<Long, Void> swingWorker =
                        new SwingWorker<Long, Void>() {

                            public Long doInBackground() {
                                long start = System.currentTimeMillis();
                                worker.work();
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
                swingWorker.execute();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpringGuiRunner");
        frame.setContentPane(new SpringGuiRunner().jPanel);
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
