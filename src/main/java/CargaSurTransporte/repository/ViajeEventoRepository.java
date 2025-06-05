package CargaSurTransporte.repository;

import CargaSurTransporte.model.ViajeEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeEventoRepository extends JpaRepository<ViajeEvento, Long> {

    /* Traer todos los eventos de un viaje, ordenados por fecha/hora ASC */
    List<ViajeEvento> findByViajeIdOrderByTimestampAsc(Long viajeId);
}
