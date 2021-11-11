package uk.ac.rgu.rgtodu.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * A piece of work that needs to be done
 * @author David Corsar
 */
@Entity(tableName = "task")
public class Task implements Parcelable {

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", hoursToCompletion=" + hoursToCompletion +
                ", deadline=" + deadline +
                '}';
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    // reference name for the task
    private String name;
    // some more information about it
    private String description;
    // how important the task is
    private TaskPriority priority;
     // estimate of how many hours it will take to complete
    @ColumnInfo(name = "Hours_Remaining")
    private int hoursToCompletion;
    // when the task needs to be completed by
    private Date deadline;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoursToCompletion() {
        return hoursToCompletion;
    }

    public void setHoursToCompletion(int hoursToCompletion) {
        this.hoursToCompletion = hoursToCompletion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        // write all of the fields of the Task to out
        out.writeString(getName());
        out.writeString(getDescription());
        out.writeInt(getHoursToCompletion());
        out.writeLong(getDeadline().getTime());
        // as TaskPriority is an enum, write the label as a String
        out.writeString(getPriority().getLabel());
    }

    // A creators for reading Tasks back from a Parcel
    public static final Creator<Task> CREATOR
            = new Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            // create and return a new Task based on the contents on in
            Task task = new Task();
            // read the Task fields in the same order they were writter
            task.setName(in.readString());
            task.setDescription(in.readString());
            task.setHoursToCompletion(in.readInt());
            task.setDeadline(new Date(in.readLong()));
            // restore proprity using the TaskProprity enum valueOf method for the String that
            // was written to the Parcel
            task.setPriority(TaskPriority.valueOf(in.readString()));
            // now return the new task
            return task;
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}

