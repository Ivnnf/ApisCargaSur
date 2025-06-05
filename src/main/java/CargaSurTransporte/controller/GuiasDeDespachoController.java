package CargaSurTransporte.controller;

import CargaSurTransporte.model.GuiasDeDespacho;
import CargaSurTransporte.service.GuiasDeDespachoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/guias-despacho")
public class GuiasDeDespachoController {

    private final GuiasDeDespachoService service;

    public GuiasDeDespachoController(GuiasDeDespachoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GuiasDeDespacho>> getAll() {
        return ResponseEntity.ok(service.listarTodas());
    }

    /* ---------- GET /api/v1/guias-despacho/{id} ---------- */
    @GetMapping("/{id}")
    public ResponseEntity<GuiasDeDespacho> getById(@PathVariable Long id) {
        GuiasDeDespacho guia = service.obtenerPorId(id);
        return ResponseEntity.ok(guia);
    }

    @PostMapping("/generar")
    public ResponseEntity<GuiasDeDespacho> generar(@RequestBody GuiasDeDespacho body) {
    /*  Sólo deben venir: ordenTransporteId, remitente, destinatario,
        origen, destino, pesoKg. El service completará lo demás.  */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.generar(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuiasDeDespacho> actualizar(
            @PathVariable Long id,
            @RequestBody  GuiasDeDespacho body) {

        GuiasDeDespacho guiAct = service.actualizar(id, body);
        return ResponseEntity.ok(guiAct);
    }


    @PostMapping("/{id}/firma-digital")
    public ResponseEntity<GuiasDeDespacho> firmar(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {          // sin DTO extra

        String firma = body.get("firmaDigital");              // clave obligatoria
        if (firma == null || firma.isBlank())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El campo 'firmaDigital' es obligatorio");

        GuiasDeDespacho guiFirmada = service.firmarGuia(id, firma);
        return ResponseEntity.ok(guiFirmada);
    }

    // GuiaDespachoController.java
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }



}
