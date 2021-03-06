package com.example.gradify;

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
 * Written by team Gradify.
 */

public class TabFragment2 extends Fragment {




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

        Toast.makeText(getContext(), "Your SGPA is: " + sgpa, Toast.LENGTH_LONG).show();


        return view;


    }
}

