package HospitalManagement.Application.DTO.DoctorDto;

import HospitalManagement.Application.entity.doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface doctorMapper {

    doctor toEntity(doctorRequest docr);

    @Mapping(target = "departmentcode",
            source = "department.departmentcode")
    @Mapping(target = "departmentname",
            source = "department.departmentname")
    @Mapping(target = "parentdepartmentcode",
            source = "department.parentdepartment.departmentcode")
    @Mapping(target = "parentdepartmentname",
            source = "department.parentdepartment.departmentname")
    doctorResponse toResponse(doctor doc);


    void updateDoctorFromRequests(doctorRequest docr, @MappingTarget doctor doc);
}
