package CargaSurTransporte.controller;

import CargaSurTransporte.model.Incidente;
import CargaSurTransporte.service.IncidenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {

    private final IncidenteService service;

    public IncidenteController(IncidenteService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Incidente> registrar(@RequestBody Incidente i) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(i));
    }

    @GetMapping("/viaje/{viajeId}")
    public ResponseEntity<List<Incidente>> listarPorViaje(@PathVariable Long viajeId) {
        return ResponseEntity.ok(service.obtenerPorViaje(viajeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}