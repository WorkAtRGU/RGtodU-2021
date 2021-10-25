package uk.ac.rgu.rgtodu.data;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String label;

    private TaskPriority(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
