package edu.gatech.cs2340.lab3newcomponents.entity;

public enum ClassStanding {
    FRESHMAN("Freshman","FR"),
    SOPHOMORE("Sophomore", "SO"),
    JUNIOR("Junior", "JR"),
    SENIOR("Senior", "SR");

    /** the full string representation of the student's class standing */
    private final String year;

    /** the representation of the year's abbreviation*/
    private final String code;

    /**
     * Constructor for the enumeration
     *
     * @param pyear   full name of the course
     * @param pcode   letter code / abbreviation for the course
     */
    ClassStanding(String pyear, String pcode) {
        year = pyear;
        code = pcode;
    }

    /**
     *
     * @return   the student's full class standing
     */
    public String getYear() { return year; }


    /**
     *
     * @return the abbreviation for the year
     */
    public String getCode() { return code; }

    /**
     *
     * @return the display string representation of the class standing
     */
    public String toString() { return code; }

    /**
     *
     * @return the display string representation of the class standing
     */
    public String toStringLong() { return year; }
}
