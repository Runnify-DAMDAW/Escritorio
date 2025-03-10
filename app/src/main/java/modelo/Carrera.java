/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author allae
 */
public class Carrera {
    
    private int id;
    private String name;
    private String description;
    private Date date;
    private double distance_km;
    private String location;
    private String coordinates;
    private double entry_fee;
    private int available_slots;
    private String status;
    private String category;
    private String image;
    private String gender;
    private List<RunningParticipant> runningParticipants;

    public Carrera(String name, String description, Date date, double distance_km, String location, String coordinates, double entry_fee, int available_slots, String status, String category, String image, String gender, List<RunningParticipant> runningParticipants) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.distance_km = distance_km;
        this.location = location;
        this.coordinates = coordinates;
        this.entry_fee = entry_fee;
        this.available_slots = available_slots;
        this.status = status;
        this.category = category;
        this.image = image;
        this.gender = gender;
        this.runningParticipants = runningParticipants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDistance_km() {
        return distance_km;
    }

    public void setDistance_km(double distance_km) {
        this.distance_km = distance_km;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public double getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(double entry_fee) {
        this.entry_fee = entry_fee;
    }

    public int getAvailable_slots() {
        return available_slots;
    }

    public void setAvailable_slots(int available_slots) {
        this.available_slots = available_slots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<RunningParticipant> getRunningParticipants() {
        return runningParticipants;
    }

    public void setRunningParticipants(List<RunningParticipant> runningParticipants) {
        this.runningParticipants = runningParticipants;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    @Override
    public String toString() {
        return name + " - " + location ;
    }
    
    
    
}



/*    "id": 1,
      "name": "",
      "description": "",
      "date": "",
      "distance_km": 42.195,
      "location": "Granada, España",
      "coordinates": "37.1773, -3.5986",
      "entry_fee": 30.00,
      "available_slots": 5000,
      "status": "Abierta",
      "category": "Maratón",
      "imagen": ""
*/