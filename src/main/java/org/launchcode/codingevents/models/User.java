package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String username;

    @NotNull
    private String pwHash;
    /*This dependency provides the BCryptPasswordEncoder class, which we will use to create and verify hashes*/
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User() {
    }

    public User(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** User objects should also be responsible for determining
     * if a given password is a match for the hash stored by the object.
     * We can do this using the encoder.matches() method.
     * */
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
    /*
    public boolean isMatchingPassword(String password) {
   String candidateHash = encoder.encode(password);
   return candidateHash.equals(pwHash);
}
    * */
}
