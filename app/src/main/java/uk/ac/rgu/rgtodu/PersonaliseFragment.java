package uk.ac.rgu.rgtodu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonaliseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonaliseFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonaliseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonaliseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonaliseFragment newInstance(String param1, String param2) {
        PersonaliseFragment fragment = new PersonaliseFragment();
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
        return inflater.inflate(R.layout.fragment_personalise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.btn_personalise_save);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        // get the name entered by the user, to send to HomeFragment
        EditText etPersonaliseName = getView().findViewById(R.id.et_personalise_name);
        String personaliseName = etPersonaliseName.getText().toString();

        Bundle bundle = new Bundle();
        // check something was actually entered
        if (personaliseName.length() > 0) {
            bundle.putString("displayName", personaliseName);
        }
        // launch the action
        Navigation.findNavController(view).navigate(
                R.id.action_personalise_to_home, bundle);
    }
}