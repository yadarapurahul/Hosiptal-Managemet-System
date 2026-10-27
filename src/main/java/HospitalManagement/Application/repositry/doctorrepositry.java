    package HospitalManagement.Application.repositry;

    import HospitalManagement.Application.entity.doctor;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.Optional;

    public interface doctorrepositry extends JpaRepository<doctor,String> {

        Optional<doctor> findByDoctorcode(String doctorcode);

        Optional<doctor> findByEmail(String email);

        Optional<doctor> findByPhonenumber(String Phonenumber);

        Optional<doctor> findByAadharnumber(String aadharnumber);

        Optional<doctor> findByPannumber(String pannumber);

        boolean existsByDoctorcode(String doctorcode);

    }
