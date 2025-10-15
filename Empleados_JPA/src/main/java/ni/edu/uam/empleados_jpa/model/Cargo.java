package ni.edu.uam.empleados_jpa.model;

import jakarta.persistence.*;

@Entity
@Table (name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true, length = 100)
    private String nombre;

    public Cargo(){
    }

    public Cargo(String nombre){
        this.nombre = nombre;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

}
