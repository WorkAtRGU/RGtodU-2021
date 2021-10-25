package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskRepository;

/**
 * A simple {@link Fragment} subclass for displaying Tasks using a ListView and custom adapter
 * Use the {@link TaskListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListViewFragment newInstance(String param1, String param2) {
        TaskListViewFragment fragment = new TaskListViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_task_list_view, container, false);

        // get 1000 Tasks and create a List of Strings from these to be displayed
        List<Task> tasks = TaskRepository.getRepository(getContext()).getTasks(1000);

        // get the ListView that they will be displayed in
        ListView lv_Tasks = view.findViewById(R.id.lv_tasks);

        // Create our own adapter to use to display the Tasks in the ListView
        TaskListItemViewAdapter adapter = new TaskListItemViewAdapter(
                getContext(),
                R.layout.task_list_view_item,
                tasks);

        // Associate the Adapter with the ListView
        lv_Tasks.setAdapter(adapter);

        // set a listener for when the user clicks on a row in the ListView
        lv_Tasks.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("TASKS", "ListView item at " + position + " clicked");
        Task taskStr = (Task)adapterView.getItemAtPosition(position);
        Log.d("TASKS", "ListView item is " + taskStr.getName());
    }

    /**** Old code for using default adapter to just display text **/

//        List<String> taskStrs = new ArrayList<String>();
//        for (Task task : tasks){
//            taskStrs.add(task.getName());
//        }

    // Create an ArrayAdapter that will adapt the Strings for display
    // in the ListView
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getContext(),
//                android.R.layout.simple_list_item_1,
//                taskStrs
//        );

}