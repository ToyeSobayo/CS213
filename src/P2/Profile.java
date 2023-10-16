package P2;

/**
 * Represents a profile for an account holder with their first name, last name, and date of birth.
 * Provides methods to access and modify the profile information.
 *
 * @author [Toye Sobayo]
 */
public class Profile implements Comparable<Profile> {

    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructor for creating a profile.
     *
     * @param fname First name of the account holder.
     * @param lname Last name of the account holder.
     * @param dob Date of birth of the account holder.
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Get the first name from the profile.
     *
     * @return The first name of the account holder.
     */
    public String getfname() {
        return fname;
    }

    /**
     * Get the last name from the profile.
     *
     * @return The last name of the account holder.
     */
    public String getlname() {
        return lname;
    }

    /**
     * Get the date of birth from the profile.
     *
     * @return The date of birth of the account holder.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Set the first name in the profile.
     *
     * @param fname The first name to be set for the account holder.
     */
    public void setfname(String fname) {
        this.fname = fname;
    }

    /**
     * Set the last name in the profile.
     *
     * @param lname The last name to be set for the account holder.
     */
    public void setlname(String lname) {
        this.lname = lname;
    }

    /**
     * Set the date of birth in the profile.
     *
     * @param dob The date of birth to be set for the account holder.
     */
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
        int lastNameComparison = this.lname.compareToIgnoreCase(otherProfile.lname);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = this.fname.compareToIgnoreCase(otherProfile.fname);
        if (firstNameComparison != 0) {;
            return firstNameComparison;
        }

        return this.dob.compareTo(otherProfile.dob);
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return A string containing the first name, last name, and date of birth of the account holder.
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }
}
