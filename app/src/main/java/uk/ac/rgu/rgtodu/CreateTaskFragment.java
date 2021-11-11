package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTaskFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateTaskFragment newInstance(String param1, String param2) {
        CreateTaskFragment fragment = new CreateTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveBtn = view.findViewById(R.id.btn_save_task);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // get all of the values that have been entered to create a new Task
        String taskName = String.valueOf(((TextView)getView().findViewById(R.id.et_taskName)).getText());
        String taskDesc = String.valueOf(((TextView)getView().findViewById(R.id.et_description)).getText());
        Date taskDeadline = new Date(((CalendarView)getView().findViewById(R.id.cv_deadline)).getDate());
        int hoursToCompletion = Integer.parseInt(String.valueOf(((EditText)getView().findViewById(R.id.et_hoursEstimate)).getText()));

        // create the new task with those values
        Task task = new Task();
        task.setName(taskName);
        task.setDescription(taskDesc);
        task.setHoursToCompletion(hoursToCompletion);
        task.setDeadline(taskDeadline);

        // now set the propority
        int taskPriorityButton = ((RadioGroup)getView().findViewById(R.id.rb_taskPriority)).getCheckedRadioButtonId();
//        if (taskPriorityButton == R.id.rb_low)
//            task.setPriority(TaskPriority.LOW);
//        else if (taskPriorityButtonPriorityButton == R.id.rb_medium)
            task.setPriority(TaskPriority.MEDIUM);
//        else if (taskPriorityButton == R.id.rb_high)
//            task.setPriority(TaskPriority.HIGH);

        int i = 0;

        Navigation.findNavController(v).navigate(R.id.action_create_task_to_home);
    }
}