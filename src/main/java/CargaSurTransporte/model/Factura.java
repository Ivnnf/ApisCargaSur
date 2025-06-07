package CargaSurTransporte.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "factura")
@Data
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;


    private String cliente;

    @Column(name = "monto_total", nullable = false)
    private Integer montoTotal;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fecha;

    private String estado;

    @Column(name = "viaje_id")
    private Long viajeId;
}
