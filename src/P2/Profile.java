package P2;

public class Profile implements Comparable<Profile> {

    private  String fname;
    private  String lname;
    private  Date dob;

    /**
     * Constructor for a profile.
     *
     * @param fname First name of the account holder.
     * @param lname Last name of the account holder.
     * @param dob of birth of the account holder.
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getfname() {
        return fname;
    }

    public String getlname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }


    /**
     * Compares this profile to another profile.
     *
     * @param otherProfile The other profile to compare to.
     * @return A negative integer, zero, or a positive integer if this profile is less than, equal to, or greater than the specified profile respectively.
     */
    @Override
    public int compareTo(Profile otherProfile) {
        int lastNameComparison = this.lname.compareTo(otherProfile.lname);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = this.fname.compareTo(otherProfile.fname);
        if (firstNameComparison != 0) {;
            return firstNameComparison;
        }

        return this.dob.compareTo(otherProfile.dob);
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }
}
