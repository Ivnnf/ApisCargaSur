package CargaSurTransporte.service;

import CargaSurTransporte.model.ViajeEvento;
import CargaSurTransporte.repository.ViajeEventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeEventoService {

    private final ViajeEventoRepository repo;
    private final ViajeService viajeService;

    public ViajeEventoService(ViajeEventoRepository repo, ViajeService viajeService) {
        this.repo = repo;
        this.viajeService = viajeService;
    }

    public List<ViajeEvento> listarTodos() {
        return repo.findAll();
    }


    public List<ViajeEvento> obtenerEventos(Long viajeId) {
        viajeService.obtenerPorId(viajeId); // ← Ahora llamado correctamente

        List<ViajeEvento> lista = repo.findByViajeIdOrderByTimestampAsc(viajeId);

        if (lista.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El viaje id=" + viajeId + " no tiene eventos registrados");
        }
        return lista;
    }

    public ViajeEvento registrarEvento(Long viajeId, ViajeEvento evento) {
        viajeService.obtenerPorId(viajeId); // ← También corregido

        evento.setId(null);
        evento.setViajeId(viajeId);

        if (evento.getTimestamp() == null) {
            evento.setTimestamp(LocalDateTime.now());
        }

        return repo.save(evento);
    }

    public ViajeEvento editarEvento(Long viajeId, ViajeEvento eventoEditado) {
        // Verifica que el viaje exista
        viajeService.obtenerPorId(viajeId);

        // Busca el evento por su ID
        ViajeEvento original = repo.findById(eventoEditado.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

        // Asegura que el evento pertenezca al viaje indicado
        if (!original.getViajeId().equals(viajeId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El evento no pertenece al viaje indicado");
        }

        // Actualiza los campos editables
        original.setTipo(eventoEditado.getTipo());
        original.setDescripcion(eventoEditado.getDescripcion());
        original.setLatitud(eventoEditado.getLatitud());
        original.setLongitud(eventoEditado.getLongitud());
        original.setTimestamp(eventoEditado.getTimestamp());

        return repo.save(original);
    }

}

