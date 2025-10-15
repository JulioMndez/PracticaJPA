package ni.edu.uam.empleados_jpa.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ni.edu.uam.empleados_jpa.model.Empleado;
import java.util.List;

@ApplicationScoped
public class EmpleadoService {

    private final EntityManagerFactory emf;

    public EmpleadoService() {
        this.emf = Persistence.createEntityManagerFactory("empleados-pu");
    }

    @Override
    protected void finalize() throws Throwable {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        super.finalize();
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar el empleado", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return empleado;
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Empleado buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}