package HospitalManagement.Application.DTO.PatientDto;

import HospitalManagement.Application.Enum.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class patientRequest {


    @NotBlank(message = "Name is required")
    private String firstname;
    @NotBlank(message = "required")
    private String lastname;
    @NotNull(message = "required")
    private int age;
    @NotBlank(message = "gender is required")
    private Gender gender;
    @Pattern(regexp = "^[6_9]\\d{9}$", message = "invalid phone Number")
    private String phonenumber;
    @Email(message = "invalid mail format")
    private String email;
    @NotBlank(message = "required")
    private String aadharnumber;
    @NotNull(message = "required")
    private String bloodgroup;
    @NotBlank(message = "requires")
    private String address;
    @Pattern(regexp = "^[6_9]\\d{9}$", message = "invalid Phonenumber")
    private String emergencyContact;
    @NotBlank(message = "required")
    private String admitDate;
    @NotBlank(message = "required")
    private String status;


}
