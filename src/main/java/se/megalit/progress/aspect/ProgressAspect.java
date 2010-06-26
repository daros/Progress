package se.megalit.progress.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 20, 2010
 * Time: 8:37:42 PM
 */

@Aspect
public class ProgressAspect {
    int callCounter = 0;

    @Before("execution(* work())")
    public void increaseCounter() {
        callCounter++;
    }
}
