package edu.gatech.cs2340.lab3newcomponents.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a single Student in the database. It is an information holder.
 */

public class Student implements Serializable {
    /** a demonstration of using something other than an enum for holding choices
     *  IRL I would always use an enumeration for this
     */
    public static List<String> legalMajors = Arrays.asList("CS", "CM", "ISYE", "MATH", "EE", "CMPE", "NA");

    /** a globally unique number for this object */
    private int id;

    /** this students name */
    private String name;

    /** this student's class standing*/
    private ClassStanding year;

    /** this students major */
    private String major;

    /**
     * Lookup a major based on its code.  Returns the position of that
     * major in the array, would not have to do this for an enum
     *
     * @param code the major to find
     *
     * @return the index of the array that corresponds to the submitted major
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalMajors.size()) {
            if (code.equals(legalMajors.get(i))) return i;
            ++i;
        }
        return 0;
    }

    public static int findPositionStanding(String yearArg) {
        if (yearArg == ClassStanding.FRESHMAN.toString()){
            return 0;
        }
        if (yearArg == ClassStanding.SOPHOMORE.toString()){
            return 1;
        }
        if (yearArg == ClassStanding.JUNIOR.toString()) {
            return 2;
        }
        if (yearArg == ClassStanding.SENIOR.toString()) {
            return 3;
        }
        return 0;
    }

    /**
     * Constructor required for making the  new object
     *
     * @param nam     name of the student
     * @param maj    major of the student
     */
    public Student(String nam, String maj, ClassStanding yearArg) {
        name = nam;
        major = maj;
        year = yearArg;
    }

    public Student(String nam, String maj){
        this(nam, maj, ClassStanding.FRESHMAN);
    }

    //Getters are required for accessing the fields from the database

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setName(String nam) { name = nam; }

    public void setMajor(String maj) {major = maj; }

    public void setStanding(ClassStanding yearArg){
        this.year = yearArg;
    }

    public ClassStanding getStanding(){
        return year;
    }

    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, major: %s, id: %d, class standing: %s", name, major, id, year);
    }

}
