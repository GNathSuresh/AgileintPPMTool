package io.agileintelligent.ppmtool.services;

import io.agileintelligent.ppmtool.domain.Backlog;
import io.agileintelligent.ppmtool.domain.ProjectTask;
import io.agileintelligent.ppmtool.repository.BacklogRepository;
import io.agileintelligent.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask)
    {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if(Objects.nonNull(backlog))
        {
            Integer nextPtSequect = backlog.getPTSequence() + 1;
            String projectTaskIdentifier = projectIdentifier + "-" + nextPtSequect;
            projectTask.setProjectIdentifier(projectTaskIdentifier);
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
        return projectTask;
    }
}


