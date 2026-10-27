package HospitalManagement.Application.DTO.PatientDto;

import HospitalManagement.Application.entity.patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface patientMapper {

    patient toEntity(patientRequest par);
    patientResponse toResponse(patient p);

    void updatePatientFromRequests(patientRequest par, @MappingTarget patient pt);
}
