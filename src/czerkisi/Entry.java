/*
 * Course: CS1021
 * Winter 2021-2022
 * Homework 6 - Login
 * Name: Ian Czerkis
 * Created: 23 Jan 2022
 */

package czerkisi;

/**
 * A database entry for users of a system
 */
public class Entry {
    private final String email;
    private final String password;

    /**
     * an object containing an email and password passed by the User
     * @param email the email the User entered
     * @param password the password the User entered
     */
    public Entry(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
