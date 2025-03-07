package modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author plope
 */
public class RespuestaInscripcion {
    private int id;
    private int dorsal;

    public RespuestaInscripcion(int id, int dorsal) {
        this.id = id;
        this.dorsal = dorsal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "RespuestaInscripcion{" + "id=" + id + ", dorsal=" + dorsal + '}';
    }
    
    
}
