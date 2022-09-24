package net.javaguides.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appUser")
public class AppUser {
    @Id
    @SequenceGenerator(name = "sequence-Name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long appUserId;
    @NotNull
    public  String registrationNumber;
    public  String username;
    @Email
    public  String email;
    public  String phoneNumber;
    @NotNull
    public String password;
    @NotNull
    public  String confirmPassword;
    public boolean enabled = false;

    public void password(String encode) {
    }
}
