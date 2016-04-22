package com.example.arshdeep.gradify;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Arshdeep on 4/21/2016.
 */
public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.PersonViewHolder>{

    /*public AboutUsAdapter(){
        List<Person> persons;

        AboutUsAdapter(List <Person> persons){
            this.persons = persons;
        }
    }*/

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personPhoneNo;
        TextView personEmail;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoneNo = (TextView)itemView.findViewById(R.id.person_phoneNo);
            personEmail = (TextView)itemView.findViewById(R.id.person_email);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

}
