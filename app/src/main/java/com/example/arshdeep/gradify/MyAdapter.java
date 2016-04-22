package com.example.arshdeep.gradify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arshdeep on 3/21/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    MyViewHolder holder;
    List<Information> data = Collections.emptyList();

    public MyAdapter(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_row, parent, false); //inflate
        holder = new MyViewHolder(view); //pass the root
        return holder;
    }

    //This is the place where we will fill the data
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Information current = data.get(i);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);


    }


    @Override
    public int getItemCount() {

        return 8;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listImage);

        }




        @Override
        public void onClick(View view) {
            position = getAdapterPosition();

            switch (position) {
                case 0:context.startActivity(new Intent(context, Result.class));
                       break;
                case 1:context.startActivity(new Intent(context, Result.class));
                    break;
                case 2:context.startActivity(new Intent(context, Result.class));
                    break;
                case 3:context.startActivity(new Intent(context, Result.class));
                    break;
                case 4:context.startActivity(new Intent(context, SGPAcalculator.class));
                    break;
                case 5:context.startActivity(new Intent(context, Downloads.class));
                    break;
                case 6:context.startActivity(new Intent(context, HelpFAQ.class));
                    break;
                case 7:context.startActivity(new Intent(context, AboutUs.class));
                    break;



            }





        }


    }
}
