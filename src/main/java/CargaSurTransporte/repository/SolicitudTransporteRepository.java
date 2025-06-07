package CargaSurTransporte.repository;

import CargaSurTransporte.model.SolicitudTransporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudTransporteRepository
        extends JpaRepository<SolicitudTransporte, Long> {

    List<SolicitudTransporte> findAll();
    List<SolicitudTransporte> findByClienteId(Long clienteId);

}
