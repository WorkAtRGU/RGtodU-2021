package uk.ac.rgu.rgtodu.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTasks(List<Task> tasks);

    @Query("Select * from task")
    public List<Task> getAllTasks();

    @Query("Select * from task WHERE name like :name")
    public List<Task> findTasksByName(String name);

    @Delete
    public void delete(Task task);

    @Delete
    public void deleteTasks(List<Task> tasks);

    @Update
    public void update(Task task);

    @Update
    public void updateTasks(List<Task> tasks);
}
