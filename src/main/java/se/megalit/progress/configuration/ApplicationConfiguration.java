package se.megalit.progress.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.megalit.progress.worker.IWorker;
import se.megalit.progress.worker.Worker;

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jun 18, 2010
 * Time: 11:54:41 PM
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public IWorker worker() {
        return new Worker();
    }
}
