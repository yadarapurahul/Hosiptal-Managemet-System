package HospitalManagement.Application.DTO.AppointmentDto;

import HospitalManagement.Application.entity.appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface appointmentMapper {


    appointment toEntity(appointmentRequest art);

    @Mapping(target = "patientCode", source = "patient.patientCode")
    @Mapping(target = "patientname", expression = "java(app.getPatient().getFirstname() + \" \" + app.getPatient().getLastname())")

    @Mapping(target = "doctorcode", source = "doctor.doctorcode")
    @Mapping(target = "doctorname", expression = "java(app.getDoctor().getFirstname() + \" \" + app.getDoctor().getLastname())")

    @Mapping(target = "specialty", source = "doctor.specialization")

    appointmentResponse toResponse(appointment app);


    void updateAppointmentFromRequests(appointmentRequest art, @MappingTarget appointment app);
}
