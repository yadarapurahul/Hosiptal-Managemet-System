package HospitalManagement.Application.services;

import HospitalManagement.Application.DTO.AppointmentDto.appointmentMapper;
import HospitalManagement.Application.DTO.AppointmentDto.appointmentRequest;
import HospitalManagement.Application.DTO.AppointmentDto.appointmentResponse;
import HospitalManagement.Application.Enum.AppointmentStatus;
import HospitalManagement.Application.Exceptions.DuplicateResourceException;
import HospitalManagement.Application.Exceptions.ResourceNotFound;
import HospitalManagement.Application.entity.appointment;
import HospitalManagement.Application.entity.doctor;
import HospitalManagement.Application.entity.patient;
import HospitalManagement.Application.repositry.appointmentrepositry;
import HospitalManagement.Application.repositry.doctorrepositry;
import HospitalManagement.Application.repositry.patientrepositry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class appointmentservice {

    private final appointmentrepositry ary;
    private final patientrepositry pr;
    private final doctorrepositry dr;
    private final appointmentMapper am;

    private String genrerateappcode(){

        List<appointment> app = ary.findAll();

        int maxNum = app.stream().map(appointment::getAppointmentcode)
                .filter(ap->ap != null && ap.matches("APMT\\d+"))
                .mapToInt(appi->Integer.parseInt(appi.substring(4)))
                .max().orElse(0);
        return String.format("APMT%03d", maxNum+1);

    }

//    It will validate the appointment date and time
    private void validateAppointmentDateTime(LocalDate date, LocalTime time){
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (date == null || time == null){
            throw new IllegalArgumentException("Appointment date and time is required");
        }

        if (date.isBefore(today) ||date.isEqual(today) && time.isBefore(now)){
            throw new IllegalArgumentException("Appointment date and time cannot be in the past.");
        }

    }

    public appointmentResponse add(appointmentRequest art){
        log.info("Booking appointment");
//        Finding patient
        patient pa = pr.findByPatientCode(art.getPatientCode()).orElseThrow(()->new ResourceNotFound("Patient Code "+art.getPatientCode()+" not founded"));

//        Finding doctor
        doctor doc = dr.findByDoctorcode(art.getDoctorcode()).orElseThrow(()-> new ResourceNotFound("Doctor Code "+art.getDoctorcode()+" not founded"));

//        It validates the date and time
        validateAppointmentDateTime(art.getAppointmentdate(), art.getAppointmenttime());


        String appointmentcode = genrerateappcode();

        if (ary.existsByAppointmentcode(appointmentcode)){
            log.warn(appointmentcode+" already exists");
            throw new DuplicateResourceException("Duplicate appointment code "+appointmentcode);
        }

        appointment app = am.toEntity(art);
        app.setPatient(pa);
        app.setDoctor(doc);
        app.setAppointmentcode(appointmentcode);
        app.setAppointmentstatus(AppointmentStatus.SCHEDULED);
        appointment appsaved = ary.save(app);

        log.info("Appointment "+appsaved.getAppointmentcode()+" Book Successfully");
        return am.toResponse(appsaved);
    }

    public List<appointmentResponse> getAllappointments(){
        log.info("Get all appointments");
        return ary.findAll().stream().map(am::toResponse).toList();
    }

    public appointmentResponse getByappointmentcode(String appointmentcode){
        log.info("Get appointment Details by appointment code");
        appointment app = ary.findByAppointmentcode(appointmentcode).orElseThrow(()->new ResourceNotFound("Appointment Code "+appointmentcode+" not founded"));
        return am.toResponse(app);
    }

    public appointmentResponse updateByappointmentcode(String appointmentcode, appointmentRequest art){
        log.info("Update appointment Details by appointment code");
        appointment app = ary.findByAppointmentcode(appointmentcode).orElseThrow(()-> new ResourceNotFound("Appointment Code "+appointmentcode+" not founded"));

//        Resolve updated patient reference
        if (art.getPatientCode() != null &&  !art.getPatientCode().isBlank()){
            patient pa = pr.findByPatientCode(art.getPatientCode()).orElseThrow(()-> new ResourceNotFound("Patient Code "+art.getPatientCode()+" not founded"));
            app.setPatient(pa);
        }

//        updated dooctor reference
        if (art.getDoctorcode() != null &&  !art.getDoctorcode().isBlank()){
            doctor doc = dr.findByDoctorcode(art.getDoctorcode()).orElseThrow(()-> new ResourceNotFound("DoctorCode "+art.getDoctorcode()+" not founded"));
            app.setDoctor(doc);
        }


//        prevent booking is the past
       validateAppointmentDateTime(art.getAppointmentdate(), art.getAppointmenttime());

        am.updateAppointmentFromRequests(art, app);

        appointment updated = ary.save(app);

        return am.toResponse(updated);
    }

    public String deleteByappointmentcode(String appointmentcode){
        log.info("Delete appointment by appointment code");
        appointment app = ary.findByAppointmentcode(appointmentcode).orElseThrow(()-> new ResourceNotFound("Appointment Code "+appointmentcode+" not founded"));
        ary.delete(app);
        return "Appointment "+app.getAppointmentcode()+" as Deletec Successfully";
    }
}
