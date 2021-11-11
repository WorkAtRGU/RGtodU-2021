package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;
import uk.ac.rgu.rgtodu.data.TaskRepository;

/**
 * A simple {@link Fragment} subclass that uses a RecyclerView to display a list of tasks
 * Use the {@link TaskRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskRecyclerViewFragment extends Fragment {

    // member variable for tasks being displayed
    List<Task> tasks;

    // member variable for RecyclerView adapter
    RecyclerView.Adapter adapter;

    public TaskRecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment TaskRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskRecyclerViewFragment newInstance() {
        TaskRecyclerViewFragment fragment = new TaskRecyclerViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        // initialise the tasks list
        tasks = new ArrayList<Task>();//TaskRepository.getRepository(getContext()).getTasks(1000);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);

        // get the RecycylerView on the UI
        RecyclerView rv = view.findViewById(R.id.rv_taskRecyclerView);

        // create a new Adapter for the Tasks
        adapter = new TaskRecyclerViewAdapter(getContext(), tasks);
        // set the recycler view's adapter
        rv.setAdapter(adapter);
        // setup the layout manager on the recycler view
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        downloadTasks();

        return view;
    }

    private void downloadTasks(){
        // make my volley request
        String url = "https://cm3110-2021-default-rtdb.europe-west1.firebasedatabase.app/dcorsar.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            tasks.clear();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject tasksObject = jsonObject.getJSONObject("tasks");
                            for (Iterator<String> it = tasksObject.keys(); it.hasNext();){
                                String taskId = it.next();
                                JSONObject taskObj = tasksObject.getJSONObject(taskId);
                                String name = taskObj.getString("name");
                                String description = taskObj.getString("description");
                                int hoursToCompletion = taskObj.getInt("hoursToCompletion");
                                long deadlineL = taskObj.getLong("deadline");
                                String priority = taskObj.getString("priority");

                                Task task = new Task();
                                task.setName(name);
                                task.setDescription(description);
                                task.setHoursToCompletion(hoursToCompletion);
                                task.setPriority(TaskPriority.valueOf(priority));
                                Date deadLineDate = new Date();
                                deadLineDate.setTime(deadlineL);
                                task.setDeadline(deadLineDate);
                                tasks.add(task);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tasks", error.getLocalizedMessage());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }
}