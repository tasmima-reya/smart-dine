package code.fortomorrow.foodie.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import code.fortomorrow.foodie.R;

public class AboutFragment extends Fragment {
    private TextView title, title1, title2,  about, about1, about2, about3;


    public AboutFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void findViewById(int tv3) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_about, container, false);
        title = view.findViewById(R.id.tv3);
        title1 = view.findViewById(R.id.tv7);
        about3 = view.findViewById(R.id.tv6);
        title2 = view.findViewById(R.id.tv8);
        about = view.findViewById(R.id.tv2);
        about1 = view.findViewById(R.id.tv4);
        about2 = view.findViewById(R.id.tv5);
        // Inflate the layout for this fragment
        return view;
    }
}