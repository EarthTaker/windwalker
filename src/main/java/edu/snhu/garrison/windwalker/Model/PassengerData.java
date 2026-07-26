package edu.snhu.garrison.windwalker.Model;

/**
 * DTO for receiving passenger information from booking form.
 * Field names match the HTML form input names for automatic binding.
 */
public class PassengerData {
    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // Default constructor
    public PassengerData() {
    }

    // Constructor with all fields
    public PassengerData(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
