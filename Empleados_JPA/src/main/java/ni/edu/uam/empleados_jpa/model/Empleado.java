package ni.edu.uam.empleados_jpa.model;

import jakarta.persistence.*;

@Entity
@Table (name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 150)
    private String nombres;

    @Column (nullable = false, length = 150)
    private String apellidos;

    @ManyToOne (optional = false)
    @JoinColumn (name = "cargo_id", nullable = false)
    private Cargo cargo;

    // Constructores + Getters y Setters

    public Empleado(){
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Empleado (String nombres, String apellidos){
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos(){
        return apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public Cargo getCargo(){
        return cargo;
    }

    public void setCargo(Cargo cargo){
        this.cargo = cargo;
    }
}
