package HospitalManagement.Application.DTO.DoctorDto;

import HospitalManagement.Application.Enum.DoctorStatus;
import HospitalManagement.Application.Enum.Gender;
import HospitalManagement.Application.Enum.Qualification;
import HospitalManagement.Application.Enum.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class doctorResponse {

    private String doctorcode;
    private String firstname;
    private String lastname;
    private Gender gender;
    private int age;
    private String email;
    private String aadharnumber;
    private String pannumber;
    private String phonenumber;
    private String address;
    private BigDecimal experience;
    private Specialization specialization;
    private Qualification qualification;
    private String licencenumber;
    private BigDecimal consultationfee;
    private LocalDate joiningdate;
    private DoctorStatus doctorStatus;

    private String departmentcode;
    private String departmentname;

    private String parentdepartmentcode;
    private String parentdepartmentname;

}
