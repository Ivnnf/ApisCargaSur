package CargaSurTransporte.controller;

import CargaSurTransporte.model.Vehiculo;
import CargaSurTransporte.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService service;
    public VehiculoController(VehiculoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAll() {
        return ResponseEntity.ok(service.listarTodas());
    }

    /* /api/v1/vehiculos/disponibles */
    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehiculo>> disponibles() {
        return ResponseEntity.ok(service.disponibles());
    }

    /* /api/v1/vehiculos/{id}/estado-tecnico */
    @GetMapping("/{id}/estado-tecnico")
    public ResponseEntity<Vehiculo> estado(@PathVariable Long id) {
        return ResponseEntity.ok(service.estadoTecnico(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo nuevo) {
    /*  estadoTecnico y disponible deben llegar en el JSON
        – si quieres forzarlos en el servidor, asigna aquí antes de guardar */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(nuevo));      // VehiculoService.guardar(...)
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Vehiculo> actualizar(
            @PathVariable Long id,
            @RequestBody Vehiculo body) {

        return ResponseEntity.ok(service.actualizar(id, body));
    }


    /* Eliminar (anular) */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
