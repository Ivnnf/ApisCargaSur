package CargaSurTransporte.controller;

import CargaSurTransporte.model.Factura;
import CargaSurTransporte.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturacion")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // POST /api/facturacion/emitir
    @PostMapping("/emitir")
    public ResponseEntity<Factura> emitir(@RequestBody Factura factura) {
        Factura emitida = facturaService.emitir(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(emitida);
    }

    // GET /api/facturacion/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Factura> consultar(@PathVariable Long id) {
        Factura f = facturaService.obtener(id);
        return ResponseEntity.ok(f);
    }

    @GetMapping("/listarPorCliente/{idCliente}")
    public ResponseEntity<List<Factura>> listarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(facturaService.listarPorCliente(idCliente));
    }

}
