package kisonar.poc.framework.spring.controller.rest.task;


import kisonar.poc.framework.spring.domain.TaskState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskRestController {

      @GetMapping("/get")
      public ResponseEntity<HttpStatus> getTask(@RequestParam TaskState taskState) {
            String name = taskState.name();
            return ResponseEntity.ok().build();
      }
}
