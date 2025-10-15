package ni.edu.uam.empleados_jpa.rest; // O muévela al paquete 'rest' y ajusta el package

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ni.edu.uam.empleados_jpa.model.Empleado;
import ni.edu.uam.empleados_jpa.service.EmpleadoService;

import java.util.List;

@Path("/empleados")
public class EmpleadoResource { // Renombra la clase también

    @Inject
    EmpleadoService empleadoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empleado> listar() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Long id) {
        Empleado empleado = empleadoService.buscarPorId(id);
        if (empleado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(empleado).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Empleado empleado) {
        // Asumimos que el cargo viene con un ID válido
        Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
        return Response.status(Response.Status.CREATED).entity(nuevoEmpleado).build();
    }
}