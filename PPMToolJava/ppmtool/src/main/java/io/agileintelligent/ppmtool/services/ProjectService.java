package io.agileintelligent.ppmtool.services;

import io.agileintelligent.ppmtool.domain.Backlog;
import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.exceptions.ProjectIdException;
import io.agileintelligent.ppmtool.repository.BacklogRepository;
import io.agileintelligent.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project createOrUpdateProject(Project project)
    {
        Project saveProject;
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if(isNull(project.getId()))
            {
                Backlog backlog = new Backlog();
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
                backlog.setProject(project);
                project.setBacklog(backlog);
            }

            else {
                Backlog backlog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
                project.setBacklog(backlog);
            }

            saveProject = projectRepository.save(project);
        }
        catch (Exception ex)
        {
            throw new ProjectIdException("The Project "+project.getProjectIdentifier()+ " is not unique");
        }
        return saveProject;
    }

    public Project findProjectByIdentifier(String projectIdentifier)
    {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(isNull(project))
        {
            throw new ProjectIdException("Project id "+projectIdentifier+ " does not exists");
        }

        return project;
    }

    public Iterable<Project> findAllProjects()
    {
        return  projectRepository.findAll();
    }

    public void deleteProjectByProjectId(String projectIdentifier)
    {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(isNull(project))
        {
            throw new ProjectIdException("Project with project id "+projectIdentifier+" Does not exist to delete");
        }
        projectRepository.delete(project);
    }
}
