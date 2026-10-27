package HospitalManagement.Application.DTO.DepartmentDto;

import HospitalManagement.Application.Enum.DepartmentCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class departmentResponse {

    private String departmentcode;
    private DepartmentCategory departmentcategory;
    private String parentdepartmentcode;
    private String parentdepartmentname;
    private String departmentname;
//    private String subdepartment;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
