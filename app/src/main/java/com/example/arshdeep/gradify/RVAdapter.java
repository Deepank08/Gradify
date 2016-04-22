package com.example.arshdeep.gradify;

import android.content.Intent;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {


    List<Person> persons;
    private Context context;

    RVAdapter(List<Person> persons){


        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personPhoneNo.setText(persons.get(i).phoneNo);
        personViewHolder.personEmail.setText(persons.get(i).email);
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView personName;
        TextView personPhoneNo;
        TextView personEmail;
        ImageView personPhoto;
        int position;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoneNo = (TextView)itemView.findViewById(R.id.person_phoneNo);
            personEmail = (TextView)itemView.findViewById(R.id.person_email);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View view) {

            position = getAdapterPosition();

            switch (position) {
                case 0:
                    Toast.makeText(context, "Clicked on 0th pos",Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, Arshdeep.class));
                    break;
                case 1:
                    Toast.makeText(context, "Clicked on 1st pos",Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, Arshdeep.class));
                    break;
                case 2:
                    Toast.makeText(context, "Clicked on 1st pos",Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, Arshdeep.class));
                    break;
            }
        }

    }
}
