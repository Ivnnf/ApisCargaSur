package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ordenTransporteId;
    private Long vehiculoId;
    private Long conductorId;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estado;

    private Double latitudInicio;
    private Double longitudInicio;
    private Double latitudFin;
    private Double longitudFin;

    private String observaciones;

    public enum EstadoViaje { EN_CURSO, FINALIZADO, CANCELADO }
}
