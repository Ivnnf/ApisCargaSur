package CargaSurTransporte.service;

import CargaSurTransporte.model.Viaje;
import CargaSurTransporte.model.ViajeEvento;
import CargaSurTransporte.repository.ViajeEventoRepository;
import CargaSurTransporte.repository.ViajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeService {

    private final ViajeRepository repo;
    private final ViajeEventoRepository viajeEventoRepo;

    public ViajeService(ViajeRepository repo, ViajeEventoRepository viajeEventoRepo) {
        this.repo = repo;
        this.viajeEventoRepo = viajeEventoRepo;
    }

    /* ────── obtener un viaje por ID ────── */
    public Viaje obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Viaje id=" + id + " no encontrado"));
    }

    public List<Viaje> listarTodas() {
        return repo.findAll();
    }

    /* ───────── obtener estado ───────── */
    public String obtenerEstado(Long id) {
        Viaje v = obtenerPorId(id);
        return v.getEstado().name(); // EN_CURSO, FINALIZADO, CANCELADO
    }

    /* ───────── iniciar viaje ───────── */
    public Viaje iniciarViaje(Long ordenId, Viaje datos) {

        if (repo.existsByOrdenTransporteId(ordenId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La Orden de Transporte id=" + ordenId + " ya tiene un viaje registrado");
        }

        Viaje v = new Viaje();
        v.setOrdenTransporteId(ordenId);
        v.setVehiculoId(datos.getVehiculoId());
        v.setConductorId(datos.getConductorId());

        v.setFechaInicio(LocalDateTime.now());
        v.setEstado(Viaje.EstadoViaje.EN_CURSO);

        v.setLatitudInicio(datos.getLatitudInicio());
        v.setLongitudInicio(datos.getLongitudInicio());
        v.setObservaciones(datos.getObservaciones());

        return repo.save(v);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Viaje id=" + id + " no encontrado");
        }
        repo.deleteById(id);
    }

    public Viaje cerrarViaje(Long id) {
        Viaje viaje = obtenerPorId(id);  // Lanza 404 si no existe

        if (viaje.getEstado() != Viaje.EstadoViaje.EN_CURSO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Solo se pueden cerrar viajes en estado EN_CURSO");
        }

        viaje.setEstado(Viaje.EstadoViaje.FINALIZADO);
        viaje.setFechaFin(LocalDateTime.now());
        viaje = repo.save(viaje);  // ← guarda cambios

        // Agrega evento "FIN_VIAJE"
        ViajeEvento finEvento = new ViajeEvento();
        finEvento.setViajeId(id);
        finEvento.setTimestamp(LocalDateTime.now());
        finEvento.setTipo("FIN_VIAJE");
        finEvento.setDescripcion("Viaje finalizado exitosamente");
        finEvento.setLatitud(viaje.getLatitudFin());
        finEvento.setLongitud(viaje.getLongitudFin());

        viajeEventoRepo.save(finEvento);

        return viaje;
    }
}
