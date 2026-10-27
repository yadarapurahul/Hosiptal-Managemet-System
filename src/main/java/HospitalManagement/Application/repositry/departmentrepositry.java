package HospitalManagement.Application.repositry;


import HospitalManagement.Application.Enum.DepartmentCategory;
import HospitalManagement.Application.entity.department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface departmentrepositry extends JpaRepository<department, String> {

    Optional<department> findByDepartmentcode(String departmentcode);

//    Optional<department> findByDepartmentname(String departmentname);

    boolean existsBydepartmentcode(String departmentcode);

    boolean existsByDepartmentnameAndParentdepartment(String departmentname, department parentdepartment);

    boolean existsByDepartmentcategoryAndParentdepartmentIsNull(String departmentname);

    List<department> findByDepartmentcategoryAndParentdepartmentIsNull(DepartmentCategory departmentcategory);

    List<department> findByParentdepartment_Departmentcode(String departmentcode);
}
