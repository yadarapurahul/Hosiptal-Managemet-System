package HospitalManagement.Application.DTO.DoctorDto;

import HospitalManagement.Application.Enum.DoctorStatus;
import HospitalManagement.Application.Enum.Gender;
import HospitalManagement.Application.Enum.Qualification;
import HospitalManagement.Application.Enum.Specialization;
import jakarta.validation.constraints.*;
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
public class doctorRequest {


    @NotBlank(message = "First Name is required")
    private String firstname;
    @NotBlank(message = "Second Name is required")
    private String lastname;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Doctor must be at least 18 years old")
    @Max(value = 100, message = "Age Cannit exceed 100")
    private int age;
    @Email(message = "Enter the valid email")
    private String email;
    @NotBlank(message = "Aadhar number is required")
    private String aadharnumber;
    @NotBlank(message = "Pan Number is required")
    private String pannumber;
    @Pattern(regexp = "^[6_9]\\d{9}$", message = "Phone Number is required")
    private String phonenumber;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Experience is required")
    private BigDecimal experience;
    @NotNull(message = "Specialization is required")
    private Specialization specialization;
    @NotNull(message = "Qualification is required")
    private Qualification qualification;
    @NotBlank(message = "Licence Number is required")
    private String licencenumber;
    @NotNull(message = "Consultatioon Fee is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Consultation Fee must be greater than 0")
    private BigDecimal consultationfee;
    @NotNull(message = "Joining Date is required")
    private LocalDate joiningdate;
    @NotBlank(message = "Doctor's Status is required")
    private DoctorStatus doctorStatus;
    @NotBlank(message = "Department Code is required")
    private String departmentcode;
//    @NotNull(message = "Doctor's Created is required")
//    private LocalDate createdAt;
//    @NotNull(message = "Doctor's Updated is required")
//    private LocalDate updatedAt;
}
