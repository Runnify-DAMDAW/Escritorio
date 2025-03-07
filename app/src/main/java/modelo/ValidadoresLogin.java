/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author gonzalo
 */
public class ValidadoresLogin {
   
    // Valida que el campo no esté vacío.
    public static boolean noEstaVacio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }
    
}
