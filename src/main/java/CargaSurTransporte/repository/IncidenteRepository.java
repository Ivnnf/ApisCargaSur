package CargaSurTransporte.repository;

import CargaSurTransporte.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    List<Incidente> findByViajeIdOrderByTimestampAsc(Long viajeId);
}