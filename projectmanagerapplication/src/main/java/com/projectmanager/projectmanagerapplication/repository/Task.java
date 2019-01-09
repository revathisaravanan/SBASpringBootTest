package com.projectmanager.projectmanagerapplication.repository;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Component
@Entity
@Table(name="tasktable")
public class Task implements Serializable {

    private long taskId;
    @Id
    @Column(name = "Task_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    private ParentTask parenttask = new ParentTask();
    @ManyToOne(optional=false)
    @JoinColumn(name="Parent_ID", insertable = true)
	public ParentTask getParenttask() {
		return parenttask;
	}

	public void setParenttask(ParentTask parenttask) {
		this.parenttask = parenttask;
    }

    /*  @Column(name = "ParentTask")
    private String parentTask;

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
  */
    @Column(name = "Task")
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    @Column(name = "Priority")
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Column(name = "Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    private User user = new User();
    @ManyToOne(optional=false)
    @JoinColumn(name="User_ID", insertable = true)
	
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Project project;
    @ManyToOne(optional=false)
    @JoinColumn(name="Project_ID", insertable = true)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task(){
    }

    public Task(int taskId, final String taskName, Date startDate, Date endDate, int priority, String status){
        this.taskId = taskId;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority =  priority;
        this.status = status;
    } 
}