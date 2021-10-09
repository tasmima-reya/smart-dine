package code.fortomorrow.foodie.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import code.fortomorrow.foodie.Adapters.RestaurentListAdapter;
import code.fortomorrow.foodie.Model.FoodListModel;
import code.fortomorrow.foodie.R;


public class HomeFragment extends Fragment {
    private RecyclerView trailorlistRecylerView;
    private List<FoodListModel> trailorList;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        trailorlistRecylerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        trailorlistRecylerView.setLayoutManager(layoutManager);
        trailorList.add(new FoodListModel(R.drawable.bfc,"BFC",""));
        trailorList.add(new FoodListModel(R.drawable.kfc,"KFC",""));
        trailorList.add(new FoodListModel(R.drawable.kacchivai,"Kacchi Vai",""));
        trailorlistRecylerView.setAdapter(new RestaurentListAdapter(getActivity(), trailorList));
        return view;
    }

    private void init(View view) {
        trailorlistRecylerView = view.findViewById(R.id.trailorlistRecylerView);
        trailorList = new ArrayList<>();
    }
}