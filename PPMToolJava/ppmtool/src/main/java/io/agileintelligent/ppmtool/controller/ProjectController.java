package io.agileintelligent.ppmtool.controller;

import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.services.ProjectService;
import io.agileintelligent.ppmtool.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody @Valid Project project, BindingResult result)
    {
        ResponseEntity<?> responseEntity = mapValidationErrorService.validProject(result);
        if(null != responseEntity)
        {
            return responseEntity;
        }
        Project savedProject = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
