package CargaSurTransporte.repository;

import CargaSurTransporte.model.OrdenTransporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenTransporteRepository
        extends JpaRepository<OrdenTransporte, Long> {
}
