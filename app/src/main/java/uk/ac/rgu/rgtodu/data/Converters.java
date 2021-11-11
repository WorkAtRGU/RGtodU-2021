package uk.ac.rgu.rgtodu.data;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Converters for the various attributes of a Task for storing / retrieval from SQLite database
 */
public class Converters {

    @TypeConverter
    public static Date timestampToDate(Long timestamp){
        if (timestamp == null){
            return  null;
        } else return new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        if (date == null){
            return null;
        }
        return date.getTime();
    }

    @TypeConverter
    public static String fromTaskPriority(TaskPriority value) {
        return value == null ? null : value.getLabel();
    }
    @TypeConverter
    public static TaskPriority stringToTaskPriority(String value) {
        return value == null ? null : TaskPriority.valueOf(value);
    }
}
