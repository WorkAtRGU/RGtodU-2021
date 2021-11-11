package uk.ac.rgu.rgtodu.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {

    // ethod for getting TaskDao which will be implemented for us
    public abstract TaskDao taskDao();

    // singleton instance for the TaskDatabase
    private static TaskDatabase INSTANCE;

    public static TaskDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TaskDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                            .fallbackToDestructiveMigration()
                            // the following is only for testing / initial dev purposes
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
