package com.projectmanager.projectmanagerapplication.repository;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name="projecttable")
public class Project implements Serializable {

    @Id
    @Column(name = "Project_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Column(name = "ProjectName")
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Column(name = "Priority")
    private int priority;
    
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Column(name = "Start_Date")
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "End_Date")
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch=FetchType.LAZY)
    private Set<Task> task = new HashSet<Task>(0);
    
    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="User_ID", insertable = true)
	private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
    public Project(){
    }

    public Project(int projectId, final String projectName, 
                    Date startDate, Date endDate, int priority, String status){
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority =  priority;
        this.status = status;
    }
}