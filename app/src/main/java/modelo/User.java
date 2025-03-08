/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author plope
 */
public class User {
    private int id;
    private String email;
    private List<String> roles;
    private String name;
    private boolean banned;
    private List<RunningParticipantUser> runningParticipants;
    private int age;
    private String gender;
    private String image;

    public User(int id, String email, List<String> roles, String name, boolean banned, List<RunningParticipantUser> runningParticipants, int age, String gender, String image) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.name = name;
        this.banned = banned;
        this.runningParticipants = runningParticipants;
        this.age = age;
        this.gender = gender;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<RunningParticipantUser> getRunningParticipants() {
        return runningParticipants;
    }

    public void setRunningParticipants(List<RunningParticipantUser> runningParticipants) {
        this.runningParticipants = runningParticipants;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", name=" + name + ", banned=" + banned + ", runningParticipants=" + runningParticipants + ", age=" + age + ", gender=" + gender + ", image=" + image + '}';
    }


}
