package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DISPLAY_NAME = "displayName";

    private String mDisplayName;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displayName The name to be displayed
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String displayName) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DISPLAY_NAME, displayName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            mDisplayName = args.getString(ARG_DISPLAY_NAME);
        }
    }

    //HomeFragmentArgs args = HomeFragmentArgs.fromBundle(getArguments());
    //HomeFragmentArgs.fromBundle(getArguments()).getDisplayName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Add click listener for the three buttons
        // Add A Task Button
        Button btnHome = view.findViewById(R.id.btn_home_add_task);
        btnHome.setOnClickListener(this);

        // View Tasks Button
        Button btnViewTasks = view.findViewById(R.id.btn_home_view_tasks);
        btnViewTasks.setOnClickListener(this);

        // Personalise Button
        Button btnPersonalise = view.findViewById(R.id.btn_home_personalise);
        btnPersonalise.setOnClickListener(this);

        // View Tasks (ListView) Button
        Button btnViewTasksList = view.findViewById(R.id.btn_home_vew_tasks_list);
        btnViewTasksList.setOnClickListener(this);

        // Now the View is setup, return it
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Update the welcome message
        if (mDisplayName != null) {
            TextView tvWelcome = view.findViewById(R.id.tv_home_welcome);
            String welcomeMsg = getString(R.string.tv_home_welcome, mDisplayName);
            tvWelcome.setText(welcomeMsg);
        }
    }

    @Override
    public void onClick(View v) {
        // Get the NavController that will be used to navigate to other fragments
        NavController navController = Navigation.findNavController(v);
        if (v.getId() == R.id.btn_home_add_task) {
            // Navigate using the action between the home fragment and create task fragment
            navController.navigate(R.id.action_home_to_create_task);
        } else if (v.getId() == R.id.btn_home_view_tasks) {
            // Navigate using the action between the home fragment and view tasks fragment (this uses the one that includes a RecyclerView)
            navController.navigate(R.id.action_home_to_recyclerview_tasks);
        } else if (v.getId() == R.id.btn_home_vew_tasks_list) {
            // Navigate using the action between the home fragment and view tasks fragment (this uses the one that includes a RecyclerView)
            navController.navigate(R.id.action_home_to_listview_tasks);
        } if (v.getId() == R.id.btn_home_personalise) {
            // Navigate using the action between the home fragment and personalise fragment
            navController.navigate(R.id.action_home_to_personalise);
        }
    }
}