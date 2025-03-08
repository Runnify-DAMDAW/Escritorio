/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelo.Carrera;
import modelo.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author allae
 */
public interface ApiLeer {
    
    @GET("/api/running")
    Call<List<Carrera>> obtenerCarreras();
    
    @GET("/api/user/{id}")
    Call<User> obtenerMiUsuario(@Path("id") int id);

    
    @GET("consultarTodos.php")
    Call<List<Carrera>> obtenerCarrerasLocal();
    
}
