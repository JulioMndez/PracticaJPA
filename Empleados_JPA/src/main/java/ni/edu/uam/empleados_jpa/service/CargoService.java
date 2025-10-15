package ni.edu.uam.empleados_jpa.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ni.edu.uam.empleados_jpa.model.Cargo;
import java.util.List;

@ApplicationScoped
public class CargoService {

    private final EntityManagerFactory emf;

    public CargoService() {
        // Creamos el EntityManagerFactory usando el nombre de nuestra persistence-unit
        this.emf = Persistence.createEntityManagerFactory("empleados-pu");
    }

    @Override
    protected void finalize() throws Throwable {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        super.finalize();
    }

    public Cargo guardarCargo(Cargo cargo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // 1. Iniciar transacción
            em.persist(cargo);           // 2. Realizar operación
            em.getTransaction().commit();  // 3. Confirmar cambios
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // 4. Revertir si hay un error
            }
            // Aquí podrías lanzar tu propia excepción
            throw new RuntimeException("Error al guardar el cargo", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();                // 5. Siempre cerrar el EntityManager
            }
        }
        return cargo;
    }

    public List<Cargo> obtenerTodosLosCargos() {
        EntityManager em = emf.createEntityManager();
        try {
            // Las operaciones de solo lectura no necesitan una transacción explícita
            return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}