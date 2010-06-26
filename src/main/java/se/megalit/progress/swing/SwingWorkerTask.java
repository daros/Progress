package se.megalit.progress.swing;

import se.megalit.progress.SpringGuiRunner;
import se.megalit.progress.worker.IWorker;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 20, 2010
 * Time: 9:51:09 PM
 */
public class SwingWorkerTask extends SwingWorker<Long, Void> {
    private IWorker worker;
    private SpringGuiRunner gui;

    @Override
    protected Long doInBackground() throws Exception {
        gui.pauseStartWorkButton();

        long start = System.currentTimeMillis();
        worker.work();
        long end = System.currentTimeMillis();
        return end - start;
    }

    @Override
    protected void done() {
        super.done();
        try {
            long total = get();

            gui.restartStartWorkButton();
            gui.totalWorktimeFieldText(total + " ms");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }
    }
}
