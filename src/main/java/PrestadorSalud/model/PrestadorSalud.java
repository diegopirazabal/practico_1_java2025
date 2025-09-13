package PrestadorSalud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class PrestadorSalud {

    @Id
    private long id;
    private String nombre;
    private String direccion;

    @Enumerated(EnumType.STRING)
    private TipoPrestador tipo;

    public PrestadorSalud() {
    }

    public PrestadorSalud(String nombre, String direccion, TipoPrestador tipo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoPrestador getTipo() {
        return tipo;
    }

    public void setTipo(TipoPrestador tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PrestadorSalud{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}

