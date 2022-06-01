package me.pimpao.hrworker.resources;

import me.pimpao.hrworker.entities.Worker;
import me.pimpao.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path = "/workers")
public class WorkerResource {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private Environment environment;

    @Value("${test.config}")
    private String testConfig;

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok(workerRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT = " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(workerRepository.findById(id).get());
    }

    @GetMapping(path = "/configs")
    public ResponseEntity<Void> getConfigs(){
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }
}
