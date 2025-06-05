package CargaSurTransporte.controller;

import CargaSurTransporte.model.Viaje;
import CargaSurTransporte.service.ViajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/viajes")
public class ViajeController {

    private final ViajeService service;

    public ViajeController(ViajeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Viaje>> getAll() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/{id}/estado")
    public ResponseEntity<Map<String, String>> estado(@PathVariable Long id) {
        String estado = service.obtenerEstado(id);
        return ResponseEntity.ok(Map.of(
                "id", id.toString(),
                "estado", estado));
    }

    @PostMapping("/{ordenId}/registrar")
    public ResponseEntity<Viaje> registrar(
            @PathVariable Long ordenId,
            @RequestBody Viaje body) {
        Viaje nuevo = service.iniciarViaje(ordenId, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Viaje> cerrar(@PathVariable Long id) {
        Viaje cerrado = service.cerrarViaje(id);
        return ResponseEntity.ok(cerrado);
    }
}
