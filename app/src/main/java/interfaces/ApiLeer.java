/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelo.Carrera;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * @author allae
 */
public interface ApiLeer {
    
    @GET("/api/running")
    Call<List<Carrera>> obtenerCarreras();
    
    @GET("consultarTodos.php")
    Call<List<Carrera>> obtenerCarrerasLocal();
    
}
