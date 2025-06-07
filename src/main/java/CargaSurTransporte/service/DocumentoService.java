package CargaSurTransporte.service;

import CargaSurTransporte.model.Documento;
import CargaSurTransporte.repository.DocumentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository repo;

    public DocumentoService(DocumentoRepository repo) {
        this.repo = repo;
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Documento id=" + id + " no encontrado");
        }
        repo.deleteById(id);
    }

    public List<Documento> listarTodos() {
        return repo.findAll();
    }

}
