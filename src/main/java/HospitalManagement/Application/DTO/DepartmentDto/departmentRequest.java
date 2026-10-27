package HospitalManagement.Application.DTO.DepartmentDto;

import HospitalManagement.Application.Enum.DepartmentCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class departmentRequest {

    @NotNull(message = "Department Category is required")
    private DepartmentCategory departmentcategory;

//    Null for a root department
    private String parentdepartmentcode;

    @NotBlank(message = "Department Name is required")
    @Size(max = 100, message = "Department name must not exceed 100 characters")
    private String departmentname;

    @NotNull(message = "Status of Department is required")
    private String status;
//    @NotNull(message = "Created Date is required")
//    private LocalDate createdAt;
//    @NotNull(message = "Updated Date is required")
//    private LocalDate updatedAt;
}
