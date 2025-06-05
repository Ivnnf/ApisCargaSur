package CargaSurTransporte.repository;

import CargaSurTransporte.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    /* ¿La orden ya tiene un viaje?  */
    boolean existsByOrdenTransporteId(Long ordenTransporteId);

    /* Consultar por orden si lo necesitas en el futuro */
    Optional<Viaje> findByOrdenTransporteId(Long ordenTransporteId);

}
