package CargaSurTransporte.service;

import CargaSurTransporte.model.GuiasDeDespacho;
import CargaSurTransporte.repository.GuiasDeDespachoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuiasDeDespachoService {

    private final GuiasDeDespachoRepository repo;

    public GuiasDeDespachoService(GuiasDeDespachoRepository repo) {
        this.repo = repo;
    }

    /* ───── buscar por ID ───── */
    public GuiasDeDespacho obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Guía de despacho id=" + id + " no encontrada"));
    }



    public GuiasDeDespacho generar(GuiasDeDespacho datos) {

        GuiasDeDespacho g = new GuiasDeDespacho();

        /* Datos que SÍ vienen en el JSON -------------- */
        g.setOrdenTransporteId(datos.getOrdenTransporteId());
        g.setRemitente(         datos.getRemitente()        );
        g.setDestinatario(      datos.getDestinatario()     );
        g.setOrigen(            datos.getOrigen()           );
        g.setDestino(           datos.getDestino()          );
        g.setPesoKg(            datos.getPesoKg()           );

        /* Datos que fija el servidor ------------------ */
        g.setFechaEmision( LocalDate.now() );
        g.setEstado(       GuiasDeDespacho.EstadoGuia.GENERADA );

        /* Número correlativo: “GDS-000xxx” */
        long sec = repo.count() + 1;   // ‼️ sencillo pero suficiente
        g.setNumeroGuia( String.format("GDS-%06d", sec) );

        /* Persistir y devolver */
        return repo.save(g);
    }


    // GuiaDespachoService.java  (agrega después de generar, obtenerPorId…)

    public GuiasDeDespacho actualizar(Long id, GuiasDeDespacho cambios) {

        GuiasDeDespacho g = obtenerPorId(id);        // ya lanza 404 si no existe

        /* Solo campos editables */
        g.setRemitente   ( cambios.getRemitente()    );
        g.setDestinatario( cambios.getDestinatario() );
        g.setOrigen      ( cambios.getOrigen()       );
        g.setDestino     ( cambios.getDestino()      );
        g.setPesoKg      ( cambios.getPesoKg()       );

        /* Cambio de estado opcional: GENERADA → EMITIDA */
        if (g.getEstado() == GuiasDeDespacho.EstadoGuia.GENERADA)
            g.setEstado(GuiasDeDespacho.EstadoGuia.EMITIDA);

        return repo.save(g);                       // persiste y devuelve
    }


    /* ───────── registrar firma digital ───────── */
    public GuiasDeDespacho firmarGuia(Long id, String firmaB64) {

        GuiasDeDespacho g = obtenerPorId(id);          // 404 si no existe

        // Solo se puede firmar una guía GENERADA
        if (g.getEstado() != GuiasDeDespacho.EstadoGuia.EMITIDA)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La guía id=" + id + " no está en estado EMITIDA");

        g.setFirmaDigital(firmaB64);
        g.setFechaFirma(LocalDateTime.now());
        g.setEstado(GuiasDeDespacho.EstadoGuia.ENTREGADA);

        return repo.save(g);
    }

    public List<GuiasDeDespacho> listarTodas() {
        return repo.findAll();
    }

    /* ─────────── eliminar ─────────── */
    public void eliminar(Long id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Guía de despacho id=" + id + " no encontrada");
        }

        repo.deleteById(id);
    }

    public GuiasDeDespacho anular(Long id) {
        GuiasDeDespacho g = obtenerPorId(id);

        if (g.getEstado() == GuiasDeDespacho.EstadoGuia.ENTREGADA) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La guía ya fue entregada y no puede anularse.");
        }

        g.setEstado(GuiasDeDespacho.EstadoGuia.ANULADA);
        return repo.save(g);
    }



}
