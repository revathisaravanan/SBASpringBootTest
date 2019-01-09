package com.projectmanager.projectmanagerapplication.exception;

@SuppressWarnings("serial")
public class ProjectNotFoundException extends Exception {

    private String message;

    public ProjectNotFoundException(final String message){
        super(message);
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserNotFoundException [message = "+ message + " ]";
    }
}