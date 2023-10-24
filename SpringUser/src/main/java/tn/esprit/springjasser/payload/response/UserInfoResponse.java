package tn.esprit.springjasser.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class UserInfoResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Date dateOfBirth;
    private String email;
    private String contactNumber;
    private List<String> roles;

    public UserInfoResponse(String token, Long id, String firstName, String lastName, String username, Date dateOfBirth, String email, String contactNumber, List<String> roles) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.contactNumber = contactNumber;
        this.roles = roles;
    }
}
