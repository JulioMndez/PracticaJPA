package ni.edu.uam.empleados_jpa.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ni.edu.uam.empleados_jpa.model.Cargo;
import ni.edu.uam.empleados_jpa.service.CargoService;

import java.util.List;

@Path("/cargos")
public class CargoResource {

    @Inject
    CargoService cargoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargo> listar() {
        return cargoService.obtenerTodosLosCargos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Cargo cargo) {
        Cargo nuevoCargo = cargoService.guardarCargo(cargo);
        return Response.status(Response.Status.CREATED).entity(nuevoCargo).build();
    }
}