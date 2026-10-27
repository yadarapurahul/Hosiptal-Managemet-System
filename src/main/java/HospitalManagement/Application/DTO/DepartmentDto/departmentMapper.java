package HospitalManagement.Application.DTO.DepartmentDto;

import HospitalManagement.Application.entity.department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface departmentMapper {

    @Mapping(target = "parentdepartment", ignore = true)
    @Mapping(target = "departmentcode", ignore = true)
    @Mapping(target = "subdepartments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    department toEntity(departmentRequest dert);

    @Mapping(target = "parentdepartmentcode", source = "parentdepartment.departmentcode")
    @Mapping(target = "parentdepartmentname", source = "parentdepartment.departmentcode")

    departmentResponse toResponse(department dept);

    @Mapping(target = "departmentcode",ignore = true)
    @Mapping(target = "parentdepartment", ignore = true)
    @Mapping(target = "subdepartments",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)


    void updateDepartmentFromRequests(departmentRequest dert, @MappingTarget department dept);


}
