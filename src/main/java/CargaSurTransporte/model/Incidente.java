package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long viajeId;

    private String descripcion;
    private Double latitud;
    private Double longitud;
    private LocalDateTime timestamp;
}