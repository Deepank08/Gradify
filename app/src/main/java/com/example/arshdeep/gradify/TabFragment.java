package com.example.arshdeep.gradify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by omega on 22/4/16.
 */
public class TabFragment extends Fragment {


    public static String[] coursecode_str = {"code11", "code12", "code13", "code14", "code15", "code16"};
    public static String[] coursename_str = {"subject11", "subject12", "subject13", "subject14", "subject15", "subject16"};
    public static String[] grade_str = {"11A", "11B", "11C", "11D", "11E", "11F"};
    public static String[] credit_str = {"11-4", "11-3", "11-2", "11-4", "11-3.5", "11-4"};


    public static List<String> coursecode = new ArrayList<String>(Arrays.asList(coursecode_str));
    public static List<String> coursename = new ArrayList<String>(Arrays.asList(coursename_str));
    public static List<String> grade = new ArrayList<String>(Arrays.asList(grade_str));
    public static List<String> credit = new ArrayList<String>(Arrays.asList(credit_str));
    public static ArrayAdapter<String> adpcourse;
    public static ArrayAdapter<String> adpname;
    public static ArrayAdapter<String> adpcredit;
    public static ArrayAdapter<String> adpgrade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //code for fragments
        View view = inflater.inflate(R.layout.tabs, container, false);
        adpcourse = new ArrayAdapter<>(getActivity(), R.layout.student_list_result,
                R.id.student_list_items, coursecode);
        adpname = new ArrayAdapter<>(getActivity(), R.layout.student_list_result,
                R.id.student_list_items, coursename);
        adpcredit = new ArrayAdapter<>(getActivity(), R.layout.student_list_result,
                R.id.student_list_items, credit);
        adpgrade = new ArrayAdapter<>(getActivity(), R.layout.student_list_result,
                R.id.student_list_items, grade);

        ListView course_codeList = (ListView) view.findViewById(R.id.listCourseCode);
        course_codeList.setAdapter(adpcourse);

        ListView course_nameList = (ListView) view.findViewById(R.id.listCourseName);
        course_nameList.setAdapter(adpname);

        ListView course_creditList = (ListView) view.findViewById(R.id.listCredit);
        course_creditList.setAdapter(adpcredit);

        ListView course_gradeList = (ListView) view.findViewById(R.id.listGrade);
        course_gradeList.setAdapter(adpgrade);

        Toast.makeText(getContext(), "Your SGPA is: 8.1 ",Toast.LENGTH_LONG).show();

        return view;


    }
}

