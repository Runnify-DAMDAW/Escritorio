/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.Inscripcion;
import modelo.RespuestaInscripcion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *
 * @author plope
 */
public interface ApiEditarPerfil {
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    @POST("/api/running_participant/new")
    Call<RespuestaInscripcion> inscribirse(@Body Inscripcion inscripcion);
}
