package CargaSurTransporte.repository;

import CargaSurTransporte.model.Vehiculo;
import CargaSurTransporte.model.Vehiculo.EstadoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // “/vehiculos/disponibles”
    List<Vehiculo> findByDisponibleTrueAndEstadoTecnico(EstadoTecnico estado);

    /* para validar unicidad */
    boolean existsByPatenteIgnoreCase(String patente);
}
