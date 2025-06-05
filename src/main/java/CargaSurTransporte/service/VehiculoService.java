package CargaSurTransporte.service;

import CargaSurTransporte.model.Vehiculo;
import CargaSurTransporte.model.Vehiculo.EstadoTecnico;
import CargaSurTransporte.repository.VehiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository repo;

    public VehiculoService(VehiculoRepository repo) { this.repo = repo; }

    public List<Vehiculo> listarTodas() {
        return repo.findAll();
    }

    /* GET /vehiculos/disponibles */
    public List<Vehiculo> disponibles() {
        return repo.findByDisponibleTrueAndEstadoTecnico(EstadoTecnico.OK);
    }

    /* GET /vehiculos/{id}/estado-tecnico */
    public Vehiculo estadoTecnico(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehículo id=" + id + " no encontrado"));
    }

    /* ───────────────────────────────
       1. Crear / guardar vehículo
       ─────────────────────────────── */
    public Vehiculo guardar(Vehiculo v) {

        /* 1) Normalizamos algunos campos */
        if (v.getId() != null) v.setId(null);                   // evita colisiones
        if (v.getPatente() != null)
            v.setPatente(v.getPatente().trim().toUpperCase());

        /* 2) Valores por defecto si llegan nulos */
        if (v.getDisponible() == null)        v.setDisponible(true);
        if (v.getEstadoTecnico() == null)     v.setEstadoTecnico(EstadoTecnico.OK);

        /* 3) Regla de unicidad en patente */
        boolean existe = repo.existsByPatenteIgnoreCase(v.getPatente());
        if (existe)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un vehículo con patente " + v.getPatente());

        /* 4) Persistir */
        return repo.save(v);
    }


    public Vehiculo actualizar(Long id, Vehiculo datos) {
        Vehiculo v = repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehículo id=" + id + " no encontrado"));

        /* Solo los campos que permites modificar */
        v.setDisponible(datos.getDisponible());
        v.setEstadoTecnico(datos.getEstadoTecnico());
        v.setKilometraje(datos.getKilometraje());
        v.setFechaUltRevision(datos.getFechaUltRevision());

        return repo.save(v);
    }


    public void eliminar(Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Vehiculo id=" + id + " no encontrado");
        repo.deleteById(id);
    }


}
