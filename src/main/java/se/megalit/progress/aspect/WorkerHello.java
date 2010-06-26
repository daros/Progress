package se.megalit.progress.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 20, 2010
 * Time: 3:13:11 PM
 */

@Aspect
public class WorkerHello {

    @Before("execution(* work())")
    public void workerMethodCalled() {
        System.out.println("Worker method called");
    }
}
