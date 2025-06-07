package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;  // PDF, XML, etc.

    private String url;   // Ruta de almacenamiento o base64

    private Long solicitudId; // o cualquier relación relevante
}
