package com.example.arshdeep.gradify;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by omega on 11/4/16.
 */
public class StudentInfoAdapter extends ArrayAdapter<StudentListItem> {
    /**
     * Adapter context
     */
    Context mContext;

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    public StudentInfoAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    /**
     * Returns the view for a specific item on the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final StudentListItem currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);
        final TextView textView = (TextView) row.findViewById(R.id.student_list_item);
        final TextView textView2 = (TextView) row.findViewById(R.id.student_list_item2);
        final TextView textView3 = (TextView) row.findViewById(R.id.student_list_item3);
        final TextView textView4 = (TextView) row.findViewById(R.id.student_list_item4);
        final TextView textView5 = (TextView) row.findViewById(R.id.student_list_item5);
        final TextView textView6 = (TextView) row.findViewById(R.id.student_list_item6);
        final TextView textView7 = (TextView) row.findViewById(R.id.student_list_item7);

        String rollno=currentItem.getRollNo();
        String name=currentItem.getName();
        String fname=currentItem.getFname();
        //String coursecode=currentItem.getCoursecode();
        String coursecode="";
        String session=currentItem.getSesion();
        //String branchcode=currentItem.getBranchCode();
        String branchcode="";
        String email=currentItem.getEmail();


        //creating global hashmap for branches
        HashMap<String, String> bmap = new HashMap<String, String>();
        bmap.put("01", "Civil Engg.");
        bmap.put("02", "Computer Science Engg.");
        bmap.put("03", "Electronics & Comm.");
        bmap.put("04", "Electrical Engg.");
        bmap.put("05", "Information Technology");
        bmap.put("06", "Mechanical Engg.");
        bmap.put("07", "Petroleum Engg.");
        bmap.put("08", "CSE with Cloud");
        bmap.put("09", "CAD/CAM Engg.");
        bmap.put("10", "Mobile App & Design");

        //roll number breakdown
        //1301021050
        //0123456789
        String breakdown = rollno.substring(2,3);
        if(breakdown.compareTo("0")==0) coursecode = "Bachelors of Technology";

        breakdown = rollno.substring(4,6);
        branchcode = bmap.get(breakdown);

        //breakdown ends




     /*   String[] data= new String[6];
        data[0]=Integer.toString(currentItem.getRollNo());
        data[1]=currentItem.getName();
        data[2]=currentItem.getFname();
        List<String> studentData=new ArrayList<String>(
                Arrays.asList(data));
     */
        textView.setText(rollno);
        textView2.setText(name);
        textView3.setText(fname);
        textView4.setText(coursecode);
        textView5.setText(session);
        textView6.setText(branchcode);
        textView7.setText(email);

        textView.setEnabled(true);


        return row;
    }

}
