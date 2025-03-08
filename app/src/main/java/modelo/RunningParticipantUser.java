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
    private Long time;
    private int dorsal;
    private boolean banned;

    public RunningParticipantUser(int id, Carrera running, Long time, int dorsal, boolean banned) {
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

    public Carrera getRunning() {
        return running;
    }

    public void setRunning(Carrera running) {
        this.running = running;
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
        return "RunningParticipantUser{" + "id=" + id + ", running=" + running + ", time=" + time + ", dorsal=" + dorsal + ", banned=" + banned + '}';
    }

   
    
    
}
