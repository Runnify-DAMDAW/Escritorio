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
public class Inscripcion {
    private int user;
    private int running;
    private Date time;
    private int dorsal;
    private boolean banned;

    public Inscripcion(int user, int running, int dorsal, boolean banned) {
        this.user = user;
        this.running = running;
        this.time = (time != null) ? time : new Date();
        this.dorsal = dorsal;
        this.banned = banned;
    }


    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
        return "Inscripcion{" + "user=" + user + ", running=" + running + ", time=" + time + ", dorsal=" + dorsal + ", banned=" + banned + '}';
    }
    
    
}
