package io.agileintelligent.ppmtool.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectItem {

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

    public ProjectItem() {
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
