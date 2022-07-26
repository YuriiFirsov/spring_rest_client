package org.client;

import org.client.configuration.Config;
import org.client.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Communication communication = context.getBean("communication", Communication.class);



        System.out.println(communication.getWorker(8));

        Worker worker = new Worker("Svetlana", "Vyatchina", "IT", 1500);
        worker.setId(12);

        communication.saveWorker(worker);

    }
}
