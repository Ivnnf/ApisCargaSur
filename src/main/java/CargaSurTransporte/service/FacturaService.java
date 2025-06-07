package CargaSurTransporte.service;

import CargaSurTransporte.model.Factura;
import CargaSurTransporte.repository.FacturaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    private final FacturaRepository repo;

    public FacturaService(FacturaRepository repo) {
        this.repo = repo;
    }

    public Factura emitir(Factura f) {
        f.setFecha(LocalDate.now()); // agrega la fecha actual automáticamente
        f.setEstado("EMITIDA");
        return repo.save(f);
    }

    public Factura obtener(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Factura id=" + id + " no encontrada"));
    }

    public List<Factura> listarPorCliente(Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

}
