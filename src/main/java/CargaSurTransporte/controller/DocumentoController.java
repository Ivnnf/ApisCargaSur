package CargaSurTransporte.controller;

import CargaSurTransporte.model.Documento;
import CargaSurTransporte.service.DocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping
    public ResponseEntity<List<Documento>> getAll() {
        return ResponseEntity.ok(service.listarTodos());
    }

}
