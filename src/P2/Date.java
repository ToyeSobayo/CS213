package P2;

import java.util.Calendar;

/**
 * The date class allows us to represent a date object, consisting of a month, day, and year. It also has helper methods
 * isValid, isFuture, moreThanSix, and leapYear to help determine if a date is valid. Methods compareTo and equals are
 * also implemented to help with comparison of dates.
 * @author Toye Sobayo
 */

public class Date implements Comparable<Date> {

    // Months
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    // private static final int APRIL = 4;
    private static final int MAY = 5;
    //  private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    //  private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    //  private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLI_PER_MINUTE = 1000;
    // Calculating leap year
    private static final int nonLeapDay = 28;
    private static final int leapDay = 29;
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUARTERCENTENNIAL = 400;
    private static final int max30Days = 30;
    private static final int max31Days = 31;
    private static final int minDays = 1;
    private static final int DAYS_IN_SIX_MONTHS = 182;

    // Instance Variables
    private int day;
    private int month;
    private int year;

    /**
     * get year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * set year
     *
     * @return year
     */
    public int getMonth() {
        return month;
    }

    /**
     * get day
     *
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * set day
     *
     * @return day
     */
    public int setYear(int year) {
        return this.year;
    }

    /**
     * get month
     *
     * @return month
     */
    public int setMonth(int month) {
        return this.month;
    }

    /**
     * set month
     *
     * @return month
     */
    public int setDay(int day) {
        return this.day;
    }

    // Constructor to get current date
    public Date() {
        Calendar calendar = Calendar.getInstance();

        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
    }

    // Constructor to set month, day, and year based off of arguments

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * See if a date is valid
     *
     * @return true, else false
     */
    public boolean isValid() {
        // Days need to align with month
        if (this.month >= JANUARY && this.month <= DECEMBER) {
            if (this.month == FEBRUARY) {
                if (leapYear(this.year)) {
                    // implement leapYear helper method
                    return (this.day >= minDays && this.day <= leapDay);
                } else {
                    return (this.day >= minDays && this.day <= nonLeapDay);
                }
            } else if (this.month == JANUARY || this.month == MARCH || this.month == MAY || this.month == JULY || this.month == AUGUST || this.month == OCTOBER || this.month == DECEMBER) {
                return (this.day >= minDays && this.day <= max31Days);
            } else {
                return (this.day > minDays && this.day < max30Days);
            }
        } else {
            return false;
        }
    }

    /**
     * See if a date is in the future
     *
     * @return true, or false
     */
    public boolean isFuture() {
        Calendar potentialDate = Calendar.getInstance();
        potentialDate.set(this.year, this.month, this.day);

        Calendar present = Calendar.getInstance();

        int future = potentialDate.compareTo(present);

        return future > 0;
    }

    /**
     * See if a date is more than six months in the future
     *
     * @return true, or false
     */
    public boolean moreThanSix() {
        Calendar date = Calendar.getInstance();
        date.set(this.year, this.month, this.day);
        Calendar potentialDate = Calendar.getInstance();
        potentialDate.add(Calendar.MONTH, 7);
        return date.compareTo(potentialDate) > 0;
    }

    public boolean underSixteen() {
        Calendar dob = Calendar.getInstance();
        dob.set(this.year, this.month - 1, this.day);
        Calendar potentialDate = Calendar.getInstance();
        potentialDate.add(Calendar.YEAR, -16);
        return dob.compareTo(potentialDate) > 0;

    }

    public boolean underTwentyFour() {
        return true;
    }


    /**
     * see if a year is a leapyear
     *
     * @param year
     * @return true, or false
     */
    private boolean leapYear(int year) {
        if (year % QUADRENNIAL != 0) {
            return false;
        }
        if (year % CENTENNIAL == 0) {
            return year % QUARTERCENTENNIAL == 0;
        }
        return true;
    }

    /**
     * toString
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return (this.month + "/" + this.day + "/" + this.year);

    }

    /**
     * Compare 2 dates
     *
     * @param date the object to be compared.
     * @return
     */

    public int compareTo(Date date) {

        Calendar potentialDate = Calendar.getInstance();
        potentialDate.set(date.year, date.month, date.day);

        Calendar dob = Calendar.getInstance();
        dob.set(this.year, this.month, this.day);

        if (dob.get(Calendar.YEAR) != potentialDate.get(Calendar.YEAR))
            return dob.get(Calendar.YEAR) - potentialDate.get(Calendar.YEAR);
        if (dob.get(Calendar.MONTH) != potentialDate.get(Calendar.MONTH))
            return dob.get(Calendar.MONTH) - potentialDate.get(Calendar.MONTH);
        return dob.get(Calendar.DAY_OF_MONTH) - potentialDate.get(Calendar.DAY_OF_MONTH);

    }


    /**
     * See if 2 dates are equal
     *
     * @param date
     * @return
     */
    public boolean equals(Date date) {
        return (this.day == date.day
                && this.month == date.month
                && this.year == date.year);
    }
}