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

public class AllFoodActivity extends AppCompatActivity {
    private RecyclerView alltrailors;
    private List<FoodListModel> foodListModels;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);
        button2 = findViewById(R.id.btn2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bfc = new Intent(AllFoodActivity.this, RestaurentListAdapter.class);
                startActivity(bfc);
            }
        });
        init();
        alltrailors.setLayoutManager(new GridLayoutManager(AllFoodActivity.this, 2));
        foodListModels.add(new FoodListModel(R.drawable.burger,"Burger","200"));
        foodListModels.add(new FoodListModel(R.drawable.pizza,"Pizza","300"));
        foodListModels.add(new FoodListModel(R.drawable.swarma,"Swarma","100"));
        foodListModels.add(new FoodListModel(R.drawable.grill,"Grill","490"));
        foodListModels.add(new FoodListModel(R.drawable.bfcbrussels,"Brussels","600"));
        foodListModels.add(new FoodListModel(R.drawable.bfcburger,"Burger Meal","590"));

        alltrailors.setAdapter(new FoodListAdapter(AllFoodActivity.this, foodListModels));

    }

    private void init() {
        alltrailors = findViewById(R.id.allfoods);
        foodListModels = new ArrayList<>();
    }
}