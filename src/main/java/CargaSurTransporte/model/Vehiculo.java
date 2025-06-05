package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehiculo",
        uniqueConstraints = @UniqueConstraint(columnNames = "patente"))
@Data               // getters + setters +
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String patente;

    @Column(nullable = false, length = 30)
    private String marca;

    @Column(nullable = false, length = 30)
    private String modelo;

    @Column(nullable = false)
    private Double capacidadKg;

    @Column(nullable = false)
    private Boolean disponible;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTecnico estadoTecnico;

    @Column(nullable = false)
    private LocalDate fechaUltRevision;

    @Column(nullable = false)
    private Integer kilometraje;

    /* --- catálogo de estados --- */
    public enum EstadoTecnico {
        OK,
        MANTENCION,
        FUERA_SERVICIO
    }
}
