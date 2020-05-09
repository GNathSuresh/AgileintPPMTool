package io.agileintelligent.ppmtool.services;

import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.exceptions.ProjectIdException;
import io.agileintelligent.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createOrUpdateProject(Project project)
    {
        Project saveProject;
        try{
            saveProject = projectRepository.save(project);
        }
        catch (Exception ex)
        {
            throw new ProjectIdException("The Project "+project.getProjectIdentifier()+ " is not unique");
        }
        return saveProject;
    }
}
