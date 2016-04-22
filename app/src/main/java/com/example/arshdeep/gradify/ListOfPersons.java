package com.example.arshdeep.gradify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arshdeep on 4/21/2016.
 */
public class ListOfPersons {

    class Person {
        String name;
        String age;
        int photoId;

        Person(String name, String age, int photoId) {
            this.name = name;
            this.age = age;
            this.photoId = photoId;
        }
    }

    private List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.gradify_li_7_480));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.gradify_li_7_480));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.gradify_li_7_480));
    }
}
