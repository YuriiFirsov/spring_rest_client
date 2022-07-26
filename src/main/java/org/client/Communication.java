package org.client;

import org.client.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/spring_rest/api/worker";

    public List<Worker> getAllWorkers() {

        ResponseEntity<List<Worker>> responseEntity = restTemplate.exchange(URL + "s", HttpMethod.GET
                , null, new ParameterizedTypeReference<List<Worker>>() {
                });

        List<Worker> workers = responseEntity.getBody();

        return workers;
    }

    public Worker getWorker(int id) {


        Worker worker = null;
        try {
            worker = restTemplate.getForObject(URL + "/" + id, Worker.class);
        } catch (Exception e) {
            System.out.println("There is no worker with id = " + id + " in database");
        }

        return worker;
    }

    public void saveWorker(Worker worker) {
        int id = worker.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, worker, String.class);
            System.out.println("Worker added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, worker);
            System.out.println("worker with id = " + id + " updated");
            System.out.println(worker);
        }

    }

    public void deleteWorker(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("worker with id = " + id + " deleted");
    }
}
