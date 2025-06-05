package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden_transporte")
@Data                       // Lombok genera getters/setters/equal/hashCode/toString
@NoArgsConstructor
@AllArgsConstructor
public class OrdenTransporte {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private Long solicitudId;


    private Long vehiculoId;
    private Long conductorId;

    @Column(nullable = false) private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoOrden estado;

    @Column(length = 255) private String observaciones;

    public enum EstadoOrden { CREADA, ASIGNADA, EN_VIAJE, CERRADA, ANULADA }
}


