package HospitalManagement.Application.DTO.PatientDto;

import HospitalManagement.Application.Enum.BloodGroups;
import HospitalManagement.Application.Enum.Gender;
import HospitalManagement.Application.Enum.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class patientResponse {


    private String patientCode;
    private String firstname;
    private String lastname;
    private int age;
    private Gender gender;
    private String phonenumber;
    private String email;
    private String aadharnumber;
    private BloodGroups bloodgroup;
    private String address;
    private String emergencyContact;
    private String admitDate;
    private PatientStatus status;

}
