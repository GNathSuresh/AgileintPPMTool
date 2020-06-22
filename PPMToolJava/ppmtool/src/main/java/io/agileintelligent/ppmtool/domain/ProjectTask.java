package io.agileintelligent.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(updatable = false)
     private String projectIdentifier;

     @NotBlank(message = "Please provide summary for task")
     private String summary;

     private Date created_date;
     private Date updated_date;
     private String acceptanceCriteria;
     private Integer priority;
     private Date dueDate;
     private String status;
     private String projectSequence;

     @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
     @JoinColumn(name = "backlog_id" , updatable = false, nullable = false)
     @JsonIgnore
     private Backlog backlog;

     @PrePersist
     public void onCreate()
     {
         this.created_date = new Date();
     }

     @PreUpdate
     public void onUpdate()
     {
         this.updated_date = new Date();
     }

    public ProjectTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public String toString() {
        return "ProjectItem{" +
                "id=" + id +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", summary='" + summary + '\'' +
                ", created_date=" + created_date +
                ", updated_date=" + updated_date +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", projectSequence='" + projectSequence + '\'' +
                '}';
    }
}
