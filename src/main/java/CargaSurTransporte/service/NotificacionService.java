package CargaSurTransporte.service;

import CargaSurTransporte.model.Notificacion;
import CargaSurTransporte.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository repo;

    public NotificacionService(NotificacionRepository repo) {
        this.repo = repo;
    }

    public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }
}
