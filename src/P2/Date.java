package P2;

import java.util.Calendar;
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

    public int getYear () {
        return year;
    }

    public int getMonth () {
        return month;
    }

    public int getDay () {
        return day;
    }

    public void setYear (int year) {
        this.year = year;
    }

    public void setMonth (int month){
        this.month = month;
    }

    public void setDay (int day){
        this.day = day;
    }

    public Date() {
        Calendar calendar = Calendar.getInstance();

        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
    }

    public Date( int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public boolean isValid () {
        // Days need to align with month
        if (this.month >= JANUARY && this.month <= DECEMBER) {
            if (this.month == FEBRUARY) {
                if (leapYear(this.year)) {
                    // implement leapYear helper method
                    return (this.day < minDays || this.day > leapDay);
                } else {
                    return (this.day < minDays || this.day > nonLeapDay);
                }
            } else if (this.month == JANUARY || this.month == MARCH || this.month == MAY || this.month == JULY || this.month == AUGUST || this.month == OCTOBER || this.month == DECEMBER) {
                return (this.day < minDays || this.day > max31Days);
            } else {
                return (this.day < minDays || this.day > max30Days);
            }
        } else {
            return true;
        }
    }


    public boolean isFuture () {
        Calendar potentialDate = Calendar.getInstance();
        potentialDate.set(this.year, this.month, this.day);

        Calendar present = Calendar.getInstance();

        int future = potentialDate.compareTo(present);

        return future > 0;
    }

    // TODO: finish method
    boolean moreThanSix() {
        Date present = new Date();
        if (this.isValid()) {
            System.out.println(this.toString() + " : Invalid date");
            return false;
        }
        else if (this.compareTo(present) > 0) {
            System.out.println(this.toString() + " : Event must be a future date");
            return false;
        }
        else if (Math.abs(this.compareTo(present)) > DAYS_IN_SIX_MONTHS) {
            System.out.println(this.toString() + " : Event date has to be within 6 months");
            return false;
        } else {
            return true;
        }

    }


    // private leap year method

    private boolean leapYear ( int year){
        if (year % QUADRENNIAL != 0) {
            return false;
        }
        if (year % CENTENNIAL == 0) {
            return year % QUARTERCENTENNIAL == 0;
        }
        return true;
    }

    @Override
    public String toString () {
        return (this.month + "/" + this.day + "/" + this.year);

    }

    // CompareTo
    // TODO: finish method

    public int compareTo (Date date){
        if (this.year != date.day || this.month != date.month || this.year != date.year) {
            Calendar date_ = Calendar.getInstance();
            date_.set(this.day, this.month, this.year);

            Calendar newDate = Calendar.getInstance();
            newDate.set(date.day, date.month, date.year);
            long thisDate = date_.getTimeInMillis();
            long dateTime = newDate.getTimeInMillis();
            long difference = (dateTime - thisDate) / (HOURS_IN_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLI_PER_MINUTE);

            return ((int) difference);
        } else {
            return 0;
        }


    }

    public boolean equals(Date date) {
        return (this.day == date.day
                && this.month == date.month
                && this.year == date.year);
    }


   /* public static void main(String [] args) {
        Calendar potentialDate = Calendar.getInstance();
        potentialDate.set(2030, 12, 24);
        System.out.println(potentialDate.getTime());

        Calendar present = Calendar.getInstance();

        System.out.println(present.compareTo(potentialDate));

    }*/
}



