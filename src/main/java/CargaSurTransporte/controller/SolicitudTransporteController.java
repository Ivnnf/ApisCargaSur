package CargaSurTransporte.controller;

import CargaSurTransporte.model.SolicitudTransporte;
import CargaSurTransporte.service.SolicitudTransporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes-transporte")   // ← ruta base: se antepone a TODO
public class SolicitudTransporteController {

    private final SolicitudTransporteService service;

    public SolicitudTransporteController(SolicitudTransporteService service) {
        this.service = service;
    }

    /* ────────────────────────────────
       GET  /api/v1/solicitudes-transporte/home
       ──────────────────────────────── */
    @GetMapping("/home")
    public String home() {
        return "Bienvenidoooooooooooo";
    }

    /* ────────────────────────────────
       GET  /api/v1/solicitudes-transporte          (todas)
       ──────────────────────────────── */
    @GetMapping
    public ResponseEntity<List<SolicitudTransporte>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /* ────────────────────────────────
       GET  /api/v1/solicitudes-transporte/{id}     (una por ID)
       ──────────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudTransporte> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /* ────────────────────────────────
       POST /api/v1/solicitudes-transporte/registrar
       ──────────────────────────────── */
    @PostMapping("/registrar")
    public ResponseEntity<SolicitudTransporte> crear(@RequestBody SolicitudTransporte nueva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(nueva));
    }

    /* ────────────────────────────────
       PUT  /api/v1/solicitudes-transporte/{id}
       ──────────────────────────────── */
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudTransporte> actualizar(
            @PathVariable Long id,
            @RequestBody SolicitudTransporte body) {

        return ResponseEntity.ok(service.actualizar(id, body));
    }

    /* ────────────────────────────────
       DELETE /api/v1/solicitudes-transporte/{id}
       ──────────────────────────────── */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }

    @GetMapping("/listarPorCliente/{idCliente}")
    public ResponseEntity<List<SolicitudTransporte>> listarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.listarPorCliente(idCliente));
    }

}
