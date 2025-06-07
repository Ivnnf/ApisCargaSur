package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "guia_despacho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuiasDeDespacho {

    /* PK autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Relación con la orden de transporte */
    @Column(nullable = false)
    private Long ordenTransporteId;

    /* Datos de la guía */
    @Column(nullable = false, unique = true, length = 20)
    private String numeroGuia;              // EJ: GDS-000123

    @Column(nullable = false)
    private LocalDate fechaEmision;

    /* Remitente / Destinatario */
    @Column(nullable = false, length = 100)
    private String remitente;

    @Column(nullable = false, length = 100)
    private String destinatario;

    /* Ruta */
    @Column(nullable = false, length = 100)
    private String origen;

    @Column(nullable = false, length = 100)
    private String destino;

    /* Carga */
    @Column(nullable = false)
    private Double pesoKg;

    /* Estado de la guía */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoGuia estado;      // GENERADA, EMITIDA, ENTREGADA, ANULADA

    /* Firma digital del receptor */
    @Column(columnDefinition = "TEXT")      // Base-64 o JSON con info de firma
    private String firmaDigital;            // null hasta que se firme

    private LocalDateTime fechaFirma;       // se completa al firmar

    /* ---------------- ENUM ---------------- */
    public enum EstadoGuia {
        GENERADA,
        EMITIDA,      // después de /PUT (si requieres “emitir”)
        ENTREGADA,// después de /firma-digital
        ANULADA
    }



}
