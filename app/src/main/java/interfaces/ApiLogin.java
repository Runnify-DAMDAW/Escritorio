/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.CarrerasRunning;
import modelo.Login;
import modelo.RespuestaLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *
 * @author allae
 */
public interface ApiLogin {
    
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    @POST("/api/auth/login")
    Call<RespuestaLogin> login(@Body Login login);
    
}
