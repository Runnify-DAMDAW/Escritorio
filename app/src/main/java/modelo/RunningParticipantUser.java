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
public class RunningParticipantUser {
    private int id;
    private Carrera running;
    private Date time;
    private int dorsal;
    private boolean banned;

    public RunningParticipantUser(int id, Carrera running, Date time, int dorsal, boolean banned) {
        this.id = id;
        this.running = running;
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

    public Carrera getCarrera() {
        return running;
    }

    public void setCarrera(Carrera running) {
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
        return "RunningParticipant{" + "id=" + id + ", user=" + running + ", time=" + time + ", dorsal=" + dorsal + ", banned=" + banned + '}';
    }
    
    
}
