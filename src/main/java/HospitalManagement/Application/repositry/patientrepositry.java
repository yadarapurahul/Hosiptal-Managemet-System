package HospitalManagement.Application.repositry;

import HospitalManagement.Application.entity.patient;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface patientrepositry extends JpaRepository<patient, Long> {

    //    Optional is a interface which give us ispresent, isequal, etc.
    Optional<patient> findByPatientCode(String patientcode);

    Optional<patient> findByEmail(String email);

    Optional<patient> findByPhonenumber(String phonenumber);

    Optional<patient> findByAadharnumber(String aadharnumber);

    boolean existsByPatientCode(String patientCode);
}
