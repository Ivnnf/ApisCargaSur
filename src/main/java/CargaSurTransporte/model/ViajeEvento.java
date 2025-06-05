package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "viaje_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long viajeId;
    private LocalDateTime timestamp;
    private String tipo;          // "GPS", "INCIDENTE", etc.
    private String descripcion;
    private Double latitud;
    private Double longitud;
}
