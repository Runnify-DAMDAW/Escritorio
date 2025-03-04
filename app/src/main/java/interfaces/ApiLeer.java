/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.CarrerasRunning;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * @author allae
 */
public interface ApiLeer {
    
    @GET("/api/running")
    Call<CarrerasRunning> obtenerCarreras();
    
}
