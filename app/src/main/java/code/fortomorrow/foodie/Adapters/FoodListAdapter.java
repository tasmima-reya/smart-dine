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

import code.fortomorrow.foodie.Activities.ConfirmOrderActivity;
import code.fortomorrow.foodie.Model.FoodListModel;
import code.fortomorrow.foodie.R;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Viewholder> {
    private Context context;
    private List<FoodListModel> trailorList;

    public FoodListAdapter(Context context, List<FoodListModel> trailorList) {
        this.context = context;
        this.trailorList = trailorList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singletailor,parent,false);
        return new FoodListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tailorImageId.setImageResource(trailorList.get(position).getImageView());
        holder.tailorNameId.setText(trailorList.get(position).getName());
        if(!trailorList.get(position).getPrice().isEmpty()){
            holder.price.setVisibility(View.VISIBLE);
            holder.price.setText("Price: "+trailorList.get(position).getPrice()+"Taka");
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConfirmOrderActivity.class);
                intent.putExtra("price",trailorList.get(position).getPrice());
                intent.putExtra("name",trailorList.get(position).getName());
                context.startActivity(intent);
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
