/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.CambiarContraseña;
import modelo.EditarPerfil;
import modelo.Inscripcion;
import modelo.RespuestaInscripcion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author plope
 */
public interface ApiEditarPerfil {
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    @PUT("/api/user/{id}/edit")
    Call<Boolean> editarPerfil(@Path("id") int id, @Body EditarPerfil editarPerfil);
    
    @PUT("/api/user/{id}/edit")
    Call<Boolean> editarContraseña(@Path("id") int id, @Body CambiarContraseña cambiarContraseña);
}
