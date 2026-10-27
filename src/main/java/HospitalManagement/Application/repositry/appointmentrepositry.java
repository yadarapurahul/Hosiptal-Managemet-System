package HospitalManagement.Application.repositry;

import HospitalManagement.Application.entity.appointment;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface appointmentrepositry extends JpaRepository<appointment, String> {

    Boolean existsByAppointmentcode(String appointmentcode);

    Optional<appointment> findByAppointmentcode(String appointmentcode);
}
