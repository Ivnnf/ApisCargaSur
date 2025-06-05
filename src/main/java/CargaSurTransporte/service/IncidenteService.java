package CargaSurTransporte.service;

import CargaSurTransporte.model.Incidente;
import CargaSurTransporte.repository.IncidenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidenteService {

    private final IncidenteRepository repo;

    public IncidenteService(IncidenteRepository repo) {
        this.repo = repo;
    }

    public Incidente registrar(Incidente i) {
        i.setTimestamp(LocalDateTime.now());
        return repo.save(i);
    }

    public List<Incidente> obtenerPorViaje(Long viajeId) {
        List<Incidente> lista = repo.findByViajeIdOrderByTimestampAsc(viajeId);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay incidentes para el viaje id=" + viajeId);
        }
        return lista;
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incidente id=" + id + " no encontrado");
        }
        repo.deleteById(id);
    }
}