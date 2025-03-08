/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author plope
 */
public class RunningParticipant {
    private int id;
    private User user;
    private Long time;
    private int dorsal;
    private boolean banned;

    public RunningParticipant(int id, User user, Long time, int dorsal, boolean banned) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.dorsal = dorsal;
        this.banned = banned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "RunningParticipant{" + "id=" + id + ", user=" + user + ", time=" + time + ", dorsal=" + dorsal + ", banned=" + banned + '}';
    }

    
    
    
}
