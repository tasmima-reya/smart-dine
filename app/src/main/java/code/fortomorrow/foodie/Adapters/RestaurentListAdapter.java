package code.fortomorrow.foodie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import code.fortomorrow.foodie.Activities.AllFoodActivity;
import code.fortomorrow.foodie.Activities.KFCActivity;
import code.fortomorrow.foodie.Activities.KacchiVaiActivity;
import code.fortomorrow.foodie.Model.FoodListModel;
import code.fortomorrow.foodie.R;


public class RestaurentListAdapter extends RecyclerView.Adapter<RestaurentListAdapter.Viewholder> {
    private Context context;
    private List<FoodListModel> trailorList;

    public RestaurentListAdapter(Context context, List<FoodListModel> trailorList) {
        this.context = context;
        this.trailorList = trailorList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singletailor,parent,false);
        return new RestaurentListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tailorImageId.setImageResource(trailorList.get(position).getImageView());
        holder.tailorNameId.setText(trailorList.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tailorNameId.getText().equals("BFC")){
                    context.startActivity(new Intent(context, AllFoodActivity.class));

                }
                if(holder.tailorNameId.getText().equals("KFC")){
                    context.startActivity(new Intent(context, KFCActivity.class));
                }

                if(holder.tailorNameId.getText().equals("Kacchi Vai")){
                    context.startActivity(new Intent(context, KacchiVaiActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailorList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView tailorImageId;
        private TextView tailorNameId;
        private TextView price;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tailorImageId = itemView.findViewById(R.id.tailorImageId);
            tailorNameId = itemView.findViewById(R.id.tailorNameId);
            price = itemView.findViewById(R.id.price);
        }
    }
}
