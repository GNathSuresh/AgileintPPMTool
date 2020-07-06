package io.agileintelligent.ppmtool.services;

import io.agileintelligent.ppmtool.domain.Backlog;
import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.domain.ProjectTask;
import io.agileintelligent.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligent.ppmtool.repository.BacklogRepository;
import io.agileintelligent.ppmtool.repository.ProjectRepository;
import io.agileintelligent.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask)
    {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if(Objects.nonNull(backlog))
        {
            Integer nextPtSequect = backlog.getPTSequence() + 1;
            String projectTaskIdentifier = projectIdentifier + "-" + nextPtSequect;
            projectTask.setProjectIdentifier(projectIdentifier);
            projectTask.setProjectSequence(projectTaskIdentifier);
            backlog.setPTSequence(nextPtSequect);
            projectTask.setBacklog(backlog);
            if(Objects.isNull(projectTask.getPriority()))
            {
                projectTask.setPriority(3); // Low priority
            }
            if(Objects.isNull(projectTask.getStatus()) || StringUtils.isEmpty(projectTask.getStatus()))
            {
                projectTask.setStatus("TO-DO");
                projectTask = projectTaskRepository.save(projectTask);
            }
        }
        else
        {
            throw new ProjectNotFoundException("Project is not Found");
        }
        return projectTask;
    }

    public Iterable<ProjectTask> findByBacklogProjectIdentifier(String backlog_identifier) {
        Project project = projectRepository.findByProjectIdentifier(backlog_identifier);
        if(Objects.isNull(project))
        {
            throw new ProjectNotFoundException("Project with ID '"+backlog_identifier+"' does not exists");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_identifier);
    }
}


