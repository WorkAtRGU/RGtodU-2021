package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;
import uk.ac.rgu.rgtodu.data.TaskRepository;

/**
 * A simple {@link Fragment} subclass that displays details of a Task
 * Use the {@link ViewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String task_hoursToCompletion = "task_hoursToCompletion";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewTaskFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewTaskFragment newInstance(String param1, String param2) {
        ViewTaskFragment fragment = new ViewTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // TAG to be used when logging
    private static final String FRAG_TAG = "TODO ViewTaskFragment";
    // Field variable for storing the task being displayed
    private Task mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(FRAG_TAG, "fragment on create");

        // initialise the data to be displayed in this UI
        this.mTask = TaskRepository.getRepository(getContext()).getTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(FRAG_TAG, "fragment on create view");
        return inflater.inflate(R.layout.fragment_view_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(FRAG_TAG, "fragment on view created");

        // code to update this.mTask with details from savedInstanceState
        if (savedInstanceState != null){
            Task t = new Task();
            // set t name based on savedInstanceState (ok)
            t.setName(savedInstanceState.getString("task_name"));

            // set t description based on savedInstanceState (better)
            if (savedInstanceState.containsKey("task_description")){
                t.setDescription(savedInstanceState.getString("task_description"));
            }

            // set t hoursToCompletion based on savedInstanceState (best)
            if (savedInstanceState.containsKey(task_hoursToCompletion)){
                t.setHoursToCompletion(savedInstanceState.getInt(task_hoursToCompletion, 1));
            }

            // set t deadline based on savedInstanceState
            if (savedInstanceState.containsKey("task_deadline")){
                // get the deadline from savedInstanceState
                long deadline = savedInstanceState.getLong("task_deadline");
                // create a new Date based on that
                Date deadlineDate = new Date(deadline);
                t.setDeadline(deadlineDate);
            }

            // set t priority based on savedInstanceState
            if (savedInstanceState.containsKey("task_priority")){
                String priortyStr = savedInstanceState.getString("task_priority");
                TaskPriority priorityEnum = TaskPriority.valueOf(priortyStr);
                t.setPriority(priorityEnum);
            }

            this.mTask = t;
        }

        // display the task stored in mTask field
        displayTask(view, this.mTask);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(FRAG_TAG, "fragment on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(FRAG_TAG, "fragment on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(FRAG_TAG, "fragment on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(FRAG_TAG, "fragment on stop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(FRAG_TAG, "fragment on save instance state");

        // add mTask name to the outState
        outState.putString("task_name", this.mTask.getName());
        // add mTask description to outState
        outState.putString("task_description", this.mTask.getDescription());
        // add mTask hoursToCompletion to outState
        outState.putInt(task_hoursToCompletion, this.mTask.getHoursToCompletion());
        // add mTask priority to outState
        outState.putString("task_priority", this.mTask.getPriority().getLabel());
        // add mTask deadline to outState
        outState.putLong("task_deadline", this.mTask.getDeadline().getTime());
        int i =0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(FRAG_TAG, "fragment on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(FRAG_TAG, "fragment on destroy");
    }

    /**
     * Updates the UI to display details of task
     * @param task
     */
    private void displayTask(View v, Task task) {
        // display the task name
        TextView tv_viewTaskName = v.findViewById(R.id.tv_viewTaskName);
        tv_viewTaskName.setText(task.getName());

        // display the task description
        TextView tv_viewTaskDescription = v.findViewById(R.id.tv_viewTaskDescription);
        tv_viewTaskDescription.setText(task.getDescription());

        // display the task priority
        TextView tv_taskPriority = v.findViewById(R.id.tv_viewPriorityValue);
        TaskPriority priority = task.getPriority();
        switch (priority){
            case LOW: tv_taskPriority.setText(getString(R.string.rb_low));
                break;
            case MEDIUM:tv_taskPriority.setText(getString(R.string.rb_medium));
                break;
            case HIGH:tv_taskPriority.setText(getString(R.string.rb_high));
                break;
        }

        // display the task hours to completion
        TextView tv_hours = v.findViewById(R.id.tv_viewHoursCompleteValue);
        tv_hours.setText(getString(R.string.tv_viewHoursCompleteValue,
                task.getHoursToCompletion()));

        // display the task deadline
        TextView tv_dateValue = v.findViewById(R.id.tv_viewTaskDeadlineValue);
        DateFormat format = SimpleDateFormat.getDateInstance();
        String formattedDate = format.format(task.getDeadline());
        tv_dateValue.setText(formattedDate);
    }
}