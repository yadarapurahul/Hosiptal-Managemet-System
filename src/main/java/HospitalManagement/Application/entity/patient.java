package HospitalManagement.Application.entity;

import HospitalManagement.Application.Enum.BloodGroups;
import HospitalManagement.Application.Enum.Gender;
import HospitalManagement.Application.Enum.PatientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class patient {

    @Id
    @Column(nullable = false, unique = true)
    private String patientCode;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = true, length = 20)
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false, unique = true)
    private String phonenumber;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String aadharnumber;
    @Enumerated(EnumType.STRING)
    private BloodGroups bloodgroup;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String emergencyContact;
    @Column(nullable = false)
    private String admitDate;
    @Enumerated(EnumType.STRING)
    private PatientStatus status;


}
