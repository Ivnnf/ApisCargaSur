package CargaSurTransporte.repository;

import CargaSurTransporte.model.GuiasDeDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiasDeDespachoRepository extends JpaRepository<GuiasDeDespacho, Long> {

}
