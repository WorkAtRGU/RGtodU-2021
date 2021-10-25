package uk.ac.rgu.rgtodu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    // member variables for the context the adapter is working in
    private Context context;
    // the data thats going to be displayed
    private List<Task> tasks;

    // TAG for logging
    private static final String TAG = "TaskRecyclerViewAdapter";

    /**
     * Creates a new {@link TaskRecyclerViewAdapter}
     * @param context that the adapter is working in
     * @param tasks data to be displayed
     */
    public TaskRecyclerViewAdapter(Context context, List<Task> tasks){
        super();
        // initialise the member variables
        this.context = context;
        this.tasks = tasks;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout file for the row
        View itemView = LayoutInflater.from(this.context)
                                    .inflate(R.layout.task_list_view_item, parent, false);
        // store it in a ViewHolder
        TaskViewHolder viewHolder = new TaskViewHolder(itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // get the task at position
        Task task = this.tasks.get(position);

        // update the task name
        TextView tv_taskName = holder.itemView.findViewById(R.id.tv_taskListItemName);
        tv_taskName.setText(task.getName());

        // update the hours
        TextView tv_taskHours = holder.itemView.findViewById(R.id.tv_taskListItemHours);
        String msg = context.getString(R.string.tv_taskListItemHours, task.getHoursToCompletion());
        tv_taskHours.setText(msg);    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    /**
     * ViewHolder for the View that's going to display Tasks
     */
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View itemView;
        private TaskRecyclerViewAdapter adapter;

        /**
         *
         * @param itemView The root View that's being displayed
         * @param adapter The adapter that is holding this ViewHolder
         */
        public TaskViewHolder(@NonNull View itemView, TaskRecyclerViewAdapter adapter) {
            super(itemView);
            this.itemView = itemView;
            this.adapter = adapter;

            // add a listener to the button in the taskItemView
            itemView.findViewById(R.id.btn_taskListItemView).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // get the clicked item's position
            int position = getAdapterPosition();

            // get the task at that position
            Task task = this.adapter.tasks.get(position);

            // display a log message with the task's name
            Log.d(TAG, task.getName() );


        }
    }
}
