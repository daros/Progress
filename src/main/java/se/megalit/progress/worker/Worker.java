package se.megalit.progress.worker;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 16, 2010
 * Time: 11:27:13 PM
 */
public class Worker implements IWorker {
    
    public void work() {
        for (int i=0; i<100; i++) {
            doTask();
        }
    }

    private void doTask() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
