package CargaSurTransporte.controller;

import CargaSurTransporte.model.OrdenTransporte;
import CargaSurTransporte.service.OrdenTransporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes-transporte")
public class OrdenTransporteController {

    private final OrdenTransporteService service;

    public OrdenTransporteController(OrdenTransporteService service) {
        this.service = service;
    }

    /* Traer todas las órdenes */
    @GetMapping
    public ResponseEntity<List<OrdenTransporte>> getAll() {
        return ResponseEntity.ok(service.listarTodas());
    }

    /* Crear */
    @PostMapping("/crear")
    public ResponseEntity<OrdenTransporte> crear(@RequestBody OrdenTransporte body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(body));
    }

    /* Obtener por ID */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenTransporte> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /* Actualizar (asignar) */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenTransporte> update(@PathVariable Long id,
                                                  @RequestBody OrdenTransporte body) {
        return ResponseEntity.ok(service.actualizar(id, body));
    }

    /* Eliminar (anular) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /* Cerrar */
    @PostMapping("/{id}/cerrar")
    public ResponseEntity<OrdenTransporte> close(@PathVariable Long id) {
        return ResponseEntity.ok(service.cerrar(id));
    }

    /* Alias que también lista todas (opcional) */
    @GetMapping("/estado")
    public ResponseEntity<List<OrdenTransporte>> estados() {
        return ResponseEntity.ok(service.listarTodas());
    }
}
