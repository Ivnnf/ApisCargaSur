package CargaSurTransporte.service;


import CargaSurTransporte.model.SolicitudTransporte;
import CargaSurTransporte.repository.SolicitudTransporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class SolicitudTransporteService {

    private final SolicitudTransporteRepository repo;

    public SolicitudTransporteService(SolicitudTransporteRepository repo) {
        this.repo = repo;
    }


    public List<SolicitudTransporte> listarPorCliente(Long clienteId) {
        return repo.findByClienteId(clienteId);
    }


    public List<SolicitudTransporte> findAll() {
        return repo.findAll();
    }

    //Guarda tal cual llega el objeto; si falta fechaSolicitud MySQL lanzará error.
    public SolicitudTransporte crear(SolicitudTransporte nueva) {
        return repo.save(nueva);          // ← sin “if”, sin setters ni cambios
    }

    public SolicitudTransporte findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "SolicitudTransporte id=" + id + " no encontrada"));

    }


    public SolicitudTransporte actualizar(Long id, SolicitudTransporte n) {

        SolicitudTransporte e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "SolicitudTransporte id=" + id + " no encontrada"));

        BeanUtils.copyProperties(n, e, "id");   // copia todo menos el id
        return repo.save(e);
    }


    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "SolicitudTransporte id=" + id + " no encontrada");
        }
        repo.deleteById(id);
    }



}