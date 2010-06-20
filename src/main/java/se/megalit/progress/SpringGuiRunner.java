package se.megalit.progress;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.megalit.progress.configuration.ApplicationConfiguration;
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
        final Worker worker = ctx.getBean(Worker.class);

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

    {
        laf(true);
    }

    private static void laf(boolean useSystemLaf) {
        if (useSystemLaf) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // If Nimbus is not available, you can set the GUI to another look and feel.
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    private static void centerOnScreen(JFrame window) {
        int width = window.getWidth();
        int height = window.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width / 2) - (width / 2); // Center horizontally.
        int y = (screen.height / 2) - (height / 2); // Center vertically.

        window.setBounds(x, y, width, height);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        startWorkButton = new JButton();
        startWorkButton.setText("Start work");
        jPanel.add(startWorkButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        jPanel.add(spacer1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(20, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        jPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer3 = new Spacer();
        jPanel.add(spacer3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer4 = new Spacer();
        jPanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(140, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 14));
        label1.setText("Spring GuiRunner");
        jPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Total work time");
        jPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalWorktimeField = new JTextField();
        jPanel.add(totalWorktimeField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(136, 28), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jPanel;
    }
}
