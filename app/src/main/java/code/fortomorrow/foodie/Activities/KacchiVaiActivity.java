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

public class KacchiVaiActivity extends AppCompatActivity {
    private RecyclerView alltrailors;
    private List<FoodListModel> foodListModels;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacchi_vai);
        button3 = findViewById(R.id.btn3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kv = new Intent(KacchiVaiActivity.this, RestaurentListAdapter.class);
                startActivity(kv);
            }
        });

        init();
        alltrailors.setLayoutManager(new GridLayoutManager(KacchiVaiActivity.this, 2));
        foodListModels.add(new FoodListModel(R.drawable.basmatikacchi,"Basmati Kacchi","(For 3 Person)1090"));
        foodListModels.add(new FoodListModel(R.drawable.chickenkhichuri,"Chicken Khichuri","450"));
        foodListModels.add(new FoodListModel(R.drawable.muttonkacchi,"Mutton Kacchi","(For 3 Person)1690"));
        foodListModels.add(new FoodListModel(R.drawable.shahimorogpolaw,"Shahi Morog Polaw","650"));
        foodListModels.add(new FoodListModel(R.drawable.beefrezala,"Beef Rejala","699"));
        foodListModels.add(new FoodListModel(R.drawable.jalikabab,"Jali Kabab","per piece 50"));

        alltrailors.setAdapter(new FoodListAdapter(KacchiVaiActivity.this, foodListModels));
    }

    private void init() {
        alltrailors = findViewById(R.id.kvfoods);
        foodListModels = new ArrayList<>();
    }
}