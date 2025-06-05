package CargaSurTransporte.controller;

import CargaSurTransporte.model.ViajeEvento;
import CargaSurTransporte.service.ViajeEventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/viajes")
public class ViajeEventoController {

    private final ViajeEventoService eventoService;

    public ViajeEventoController(ViajeEventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<ViajeEvento>> historial(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.obtenerEventos(id));
    }

    @PostMapping("/{id}/eventos/registrar")
    public ResponseEntity<ViajeEvento> registrar(
            @PathVariable Long id,
            @RequestBody ViajeEvento evento) {

        ViajeEvento nuevo = eventoService.registrarEvento(id, evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // GET /api/v1/eventos — traer todos los eventos
    @GetMapping("/eventos")
    public ResponseEntity<List<ViajeEvento>> listarTodos() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }

    @PutMapping("/{viajeId}/eventos/editar")
    public ResponseEntity<ViajeEvento> editarEvento(
            @PathVariable Long viajeId,
            @RequestBody ViajeEvento eventoEditado) {

        return ResponseEntity.ok(eventoService.editarEvento(viajeId, eventoEditado));
    }

    @PostMapping("/geolocalizacion/actualizar")
    public ResponseEntity<ViajeEvento> actualizarUbicacion(@RequestBody ViajeEvento geo) {

        // Reutiliza el método registrarEvento, seteando tipo "GPS"
        geo.setTipo("GPS");
        ViajeEvento evento = eventoService.registrarEvento(geo.getViajeId(), geo);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }






}
