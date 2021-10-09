package code.fortomorrow.foodie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import code.fortomorrow.foodie.Adapters.FoodListAdapter;
import code.fortomorrow.foodie.Adapters.RestaurentListAdapter;
import code.fortomorrow.foodie.Model.FoodListModel;
import code.fortomorrow.foodie.R;

public class KFCActivity extends AppCompatActivity {
    private RecyclerView alltrailors;
    private List<FoodListModel> foodListModels;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_f_c);
        button1 = findViewById(R.id.btn1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kfc = new Intent(KFCActivity.this, RestaurentListAdapter.class);
                startActivity(kfc);
            }
        });
        init();
        alltrailors.setLayoutManager(new GridLayoutManager(KFCActivity.this, 2));
        foodListModels.add(new FoodListModel(R.drawable.kfcchickenmeal,"Chicken Meal","499"));
        foodListModels.add(new FoodListModel(R.drawable.kfcspicybucket,"Hot and Spicy Bucket","1799"));
        foodListModels.add(new FoodListModel(R.drawable.kfcmeals,"Meal Box","699"));
        foodListModels.add(new FoodListModel(R.drawable.kfctwister,"Twister Box","450"));
        foodListModels.add(new FoodListModel(R.drawable.kfczingerbox,"Ginger Meal","549"));
        foodListModels.add(new FoodListModel(R.drawable.tacos,"Tacos Meal","449"));

        alltrailors.setAdapter(new FoodListAdapter(KFCActivity.this, foodListModels));
    }

    private void init() {
        alltrailors = findViewById(R.id.kfcfoods);
        foodListModels = new ArrayList<>();
    }
}