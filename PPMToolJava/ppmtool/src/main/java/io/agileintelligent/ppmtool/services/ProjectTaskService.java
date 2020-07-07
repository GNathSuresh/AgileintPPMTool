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

    public ProjectTask findProjectTaskByBacklogIdAndSequence(String backlog_id, String sequence)
    {
        //TODO verify the Project exists for the back log id and then fetch the project sequence
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(Objects.isNull(backlog))
        {
            throw new ProjectNotFoundException("Project with ID:'"+backlog_id+"' does not exists");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);

        if(Objects.isNull(projectTask))
        {
            throw new ProjectNotFoundException("Project with Project Task: '"+sequence+"' does not exits");
        }

        if(!projectTask.getProjectIdentifier().equals(backlog_id))
        {
            throw new ProjectNotFoundException("For Project with ID '"+backlog_id+"' No task exists by Task'"+sequence+"'");
        }

        return projectTaskRepository.findByProjectSequence(sequence);
    }

    public ProjectTask updateProjectTask(ProjectTask updatedProjectTask, String backlog_id, String sequence)
    {
        ProjectTask projectTask = findProjectTaskByBacklogIdAndSequence(backlog_id, sequence);
        ProjectTask updatedTask;
        if(Objects.nonNull(projectTask))
        {
            updatedTask = projectTaskRepository.save(updatedProjectTask);
            return updatedTask;
        }
        return projectTask;
    }

    public void deleteProjectTask(String backlog_id, String sequence)
    {
        ProjectTask projectTask = findProjectTaskByBacklogIdAndSequence(backlog_id, sequence);
        if(Objects.nonNull(projectTask))
        {
            projectTaskRepository.delete(projectTask);
        }
    }
}


