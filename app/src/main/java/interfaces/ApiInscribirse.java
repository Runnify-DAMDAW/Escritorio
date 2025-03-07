/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelo.Carrera;
import modelo.Inscripcion;
import modelo.Login;
import modelo.RespuestaInscripcion;
import modelo.RespuestaLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @author allae
 */
public interface ApiInscribirse {
    
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    @POST("/api/running_participant/new")
    Call<RespuestaInscripcion> inscribirse(@Body Inscripcion inscripcion);
    
    @DELETE("/api/running_participant/{id}")
    Call<Boolean> borrarInscripcion(@Path("id") int id);

    
}
