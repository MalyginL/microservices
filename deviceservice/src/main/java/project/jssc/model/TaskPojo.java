package project.jssc.model;

public class TaskPojo {

    public TaskPojo(byte[] task, int priority) {
        this.task = task;
        this.priority = priority;
    }

    private int priority;
    private byte[] task;


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public byte[] getTask() {
        return task;
    }

    public void setTask(byte[] task) {
        this.task = task;
    }

}
