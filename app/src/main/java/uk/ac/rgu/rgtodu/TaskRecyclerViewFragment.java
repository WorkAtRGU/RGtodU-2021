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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskRecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskRecyclerViewFragment newInstance(String param1, String param2) {
        TaskRecyclerViewFragment fragment = new TaskRecyclerViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_task_recycler_view, container, false);

        // create 1000 tasks for testing
        List<Task> tasks = new ArrayList<Task>();//TaskRepository.getRepository(getContext()).getTasks(1000);

        // get the RecycylerView on the UI
        RecyclerView rv = view.findViewById(R.id.rv_taskRecyclerView);

        // create a new Adapter for the Tasks
        RecyclerView.Adapter adapter = new TaskRecyclerViewAdapter(getContext(), tasks);
        // set the recycler view's adapter
        rv.setAdapter(adapter);
        // setup the layout manager on the recycler view
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

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
                                int i = 0;

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


        return view;
    }
}