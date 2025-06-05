package CargaSurTransporte.service;

import CargaSurTransporte.model.OrdenTransporte;
import CargaSurTransporte.repository.OrdenTransporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrdenTransporteService {

    private final OrdenTransporteRepository repo;

    public OrdenTransporteService(OrdenTransporteRepository repo) {
        this.repo = repo;
    }

    public OrdenTransporte crear(OrdenTransporte o) {
        o.setFechaCreacion(LocalDateTime.now());
        o.setEstado(OrdenTransporte.EstadoOrden.CREADA);
        return repo.save(o);
    }


    public OrdenTransporte findById(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Orden id=" + id + " no encontrada"));
    }

    public OrdenTransporte actualizar(Long id, OrdenTransporte datos) {
        OrdenTransporte o = findById(id);

        o.setVehiculoId(datos.getVehiculoId());
        o.setConductorId(datos.getConductorId());
        o.setObservaciones(datos.getObservaciones());

        o.setEstado(OrdenTransporte.EstadoOrden.ASIGNADA);
        o.setFechaAsignacion(LocalDateTime.now());

        return repo.save(o);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Orden id=" + id + " no encontrada");
        repo.deleteById(id);
    }

    public OrdenTransporte cerrar(Long id) {
        OrdenTransporte o = findById(id);
        o.setEstado(OrdenTransporte.EstadoOrden.CERRADA);
        o.setFechaCierre(LocalDateTime.now());
        return repo.save(o);
    }

    public List<OrdenTransporte> listarTodas() {
        return repo.findAll();
    }
}

