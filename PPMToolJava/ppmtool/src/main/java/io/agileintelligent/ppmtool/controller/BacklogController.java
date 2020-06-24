package io.agileintelligent.ppmtool.controller;

import io.agileintelligent.ppmtool.domain.ProjectTask;
import io.agileintelligent.ppmtool.services.MapValidationErrorService;
import io.agileintelligent.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/addProjectTask/{projectIdentifier}")
    public ResponseEntity<?> addProjectTask(@RequestBody ProjectTask projectTask , @PathVariable String projectIdentifier, BindingResult bindingResult)
    {
        ResponseEntity<?> responseEntity = mapValidationErrorService.validEntity(bindingResult);
        if(Objects.nonNull(responseEntity))
        {
            return responseEntity; // BAD Request
        }
        ProjectTask savedTask = projectTaskService.addProjectTask(projectIdentifier, projectTask);
        return new ResponseEntity<ProjectTask>(savedTask, HttpStatus.OK);
    }

    @GetMapping("/{backlog_identifier}")
    public Iterable<ProjectTask> getProjectBackLogWithProjectTasks(@PathVariable String backlog_identifier)
    {
        return projectTaskService.findByBacklogProjectIdentifier(backlog_identifier);

    }
}
