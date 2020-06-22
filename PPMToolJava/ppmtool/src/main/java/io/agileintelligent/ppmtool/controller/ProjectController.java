package io.agileintelligent.ppmtool.controller;

import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.services.ProjectService;
import io.agileintelligent.ppmtool.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody @Valid Project project, BindingResult result)
    {
        ResponseEntity<?> responseEntity = mapValidationErrorService.validEntity(result);
        if(null != responseEntity)
        {
            return responseEntity;
        }
        Project savedProject = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/getProject/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier)
    {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public Iterable<Project> findAllProject()
    {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/deleteProject/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier)
    {
        projectService.deleteProjectByProjectId(projectIdentifier);
        return new ResponseEntity<String>("Project with projectId "+projectIdentifier+ "is Deleted ", HttpStatus.OK);
    }
}
