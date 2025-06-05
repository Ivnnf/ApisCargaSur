package CargaSurTransporte.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudtransporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false, length = 100)
    private String origen;

    @Column(nullable = false, length = 100)
    private String destino;

    @Column(nullable = false, length = 50)
    private String tipoCarga;

    @Column(nullable = false)
    private Double pesoEstimadoKg;

    @Column(nullable = false)
    private LocalDateTime fechaRequerida;

    @Column(nullable = false, length = 20)
    private String estado; // Ej: PENDIENTE, PROCESADA

    @Column(length = 255)
    private String observaciones;

    @Column(length = 10)
    private String prioridad; // Ej: ALTA, MEDIA, BAJA

}
