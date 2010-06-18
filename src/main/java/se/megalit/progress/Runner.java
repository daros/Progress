package se.megalit.progress;

import se.megalit.progress.worker.Worker;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 16, 2010
 * Time: 11:33:44 PM
 */
public class Runner {

    public static void main(String[] args) {
        System.out.println("Start working...");

        long start = System.currentTimeMillis();
        new Worker().work();
        long end = System.currentTimeMillis();

        System.out.println("Work time: "+(end - start));
    }

}
