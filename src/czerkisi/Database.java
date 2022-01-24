/*
 * Course: CS1021
 * Winter 2021-2022
 * Homework 6 - Login
 * Name: Ian Czerkis
 * Created: 23 Jan 2022
 */

package czerkisi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * A database of Entry objects for a system
 */
public class Database {
    private final ArrayList<Entry> entries = new ArrayList<>();
    /**
     * Minimum number of characters in a password
     */
    public static final int MIN_PASSWORD_LENGTH = 8;
    private final Character[] specialCharactersArray = {'!', '@', '#', '$', '%', '^',
            '&', '*', '(', ')', '-', '+'};
    private final ArrayList<Character> specialCharacters =
            new ArrayList<>(Arrays.asList(specialCharactersArray));
    public static final int MIN_END_SERIES_LENGTH = 2;
    public static final int MAX_END_SERIES_LENGTH = 7;


    /**
     * Helper method that verifies the given String is a valid email using the following rules
     * <pre>
     * * starts with at least one letter, digit, or special character
     * * then has exactly one @ symbol
     * * then has one or more series of characters followed by a .
     * * ends with a series of between 2 and 7 letters (case does not matter)
     *</pre>
     * @param email String provided by user
     * @return true if a valid email, false otherwise
     */
    private boolean isEmail(String email) {
        int atSignCounter = 0;
        int atSignIndex = 0;
        int lastPeriodIndex = 0;
        char[] charArray = email.toCharArray();
        for (int i = 0; i < email.length(); i++) {
            if (charArray[i]=='@'){
                atSignCounter++;
                atSignIndex = i;
            }
            if (charArray[i]=='.'){
                lastPeriodIndex = i;
            }
        }
        boolean oneAtSign = atSignCounter == 1;
        boolean atSignCorrectPlace = atSignIndex > 0;
        int endSeriesLength = charArray.length-lastPeriodIndex-1;
        System.out.println(endSeriesLength);
        boolean periodCorrectPlace = endSeriesLength >= MIN_END_SERIES_LENGTH
                && endSeriesLength <= MAX_END_SERIES_LENGTH;
        boolean endSeriesCorrect = true;
        for (int i = lastPeriodIndex+1; i < email.length(); i++) {
            if (!Character.isAlphabetic(charArray[i])){
                endSeriesCorrect = false;
            }
        }
        return oneAtSign && atSignCorrectPlace && periodCorrectPlace && endSeriesCorrect;
    }

    /**
     * Helper method that verifies the given String is a valid password using the following rules
     * <pre>
     * * At least 8 characters
     * * At least one lowercase letter
     * * At least one uppercase letter
     * * At least one digit
     * * At least one special character
     * </pre>
     * @param password String provided by user
     * @return true if a valid password, false otherwise
     */
    private boolean isPassword(String password) {
        boolean length = password.length() >= MIN_PASSWORD_LENGTH;
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean digit = false;
        boolean specialChar = false;
        ArrayList<Character> specialCharacters =
                new ArrayList<>(Arrays.asList(specialCharactersArray));
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowerCase = true;
            }
            if (Character.isUpperCase(c)) {
                upperCase = true;
            }
            if (Character.isDigit(c)) {
                digit = true;
            }
            if (specialCharacters.contains(c)) {
                specialChar = true;
            }
        }
        return length && lowerCase && upperCase && digit && specialChar;
    }

    /**
     * adds an entry to the database if the entry meets all criteria
     * @param entry the Entry containing the email and password to be added
     * @throws MalformedEmailException if the email doesn't meet correct email address standards
     * @throws WeakPasswordException if the password doesn't meet correct email password standards
     * @throws DuplicateEntryException if the email already exists in the database
     */
    public void add(Entry entry) throws MalformedEmailException,
            WeakPasswordException, DuplicateEntryException {
        if (!isEmail(entry.getEmail())){
            throw new MalformedEmailException();
        } else if (!isPassword(entry.getPassword())){
            throw new WeakPasswordException();
        } else if (isDuplicate(entry.getEmail())){
            throw new DuplicateEntryException();
        } else {
            entries.add(entry);
        }
    }

    private boolean isDuplicate(String email) {
        boolean ret = false;
        for (Entry entry : entries) {
            if (entry.getEmail().equals(email)) {
                ret = true;
            }
        }
        return ret;
    }

// Inner Exception classes go here

    /**
     * throws if the email entered does not meet correct email address standards
     */
    public static class MalformedEmailException extends Exception {

    }

    /**
     * throws if the password does not meet criteria for being a strong password
     */
    public static class WeakPasswordException extends Exception {

    }

    /**
     * throws if the email matches another email in the database
     */
    public static class DuplicateEntryException extends Exception {

    }
}
