package com.projectmanager.projectmanagerapplication.repository;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name="parenttasktable")
public class ParentTask implements Serializable {

    private long parentId;
    @Id
    @Column(name = "Parent_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    /* public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    } */

    @Column(name = "ParentTaskName")
    private String parentTaskName;

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }
    
    private Set<Task> task = new HashSet<Task>(0);
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parenttask")
    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    public ParentTask(){
    }

    public ParentTask(int parentId, final String parentTaskName){
        this.parentId = parentId;
        this.parentTaskName = parentTaskName;
    }
 }