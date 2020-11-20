package net.banking.models;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateCreated;

    public User(){

    }

    public User(int id, String firstName, String lastName, String email, LocalDateTime dateCreated) {
        if(id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        if(firstName == null || firstName.trim().length() == 0)
            throw new IllegalArgumentException("invalid firstName supplied");
        this.firstName = firstName;

        if(lastName == null || lastName.trim().length() == 0)
            throw new IllegalArgumentException("invalid lastName supplied");
        this.lastName = lastName;

        if(email == null || email.trim().length() == 0 || !email.contains("@"))
            throw new IllegalArgumentException("invalid email supplied");
        this.email = email;

        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        if(id <= 0)
            throw new IllegalArgumentException("invalid id supplied");

        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().length() == 0)
            throw new IllegalArgumentException("invalid firstName supplied");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().length() == 0)
            throw new IllegalArgumentException("invalid lastName supplied");

        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().length() == 0 || !email.contains("@"))
            throw new IllegalArgumentException("invalid email supplied");

        this.email = email;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");

        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "User{ \n" +
                " id=" + id +
                ", \n firstName='" + firstName + '\'' +
                ", \n lastName='" + lastName + '\'' +
                ", \n email='" + email + '\'' +
                ", \n dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(! (obj instanceof User))
            return false;

        User user = (User)obj;

        return this.id == user.id && this.email.equals(user.email) && this.dateCreated == user.dateCreated;
    }

    @Override
    public int hashCode(){
        return id;
    }
}
