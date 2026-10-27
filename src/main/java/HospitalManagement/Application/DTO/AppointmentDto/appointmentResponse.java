package HospitalManagement.Application.DTO.AppointmentDto;

import HospitalManagement.Application.Enum.AppointmentStatus;
import HospitalManagement.Application.Enum.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class appointmentResponse {

    private String appointmentcode;

    private String patientCode;
    private String patientname;

    private String doctorcode;
    private String doctorname;

    private Specialization specialty;
    private String reason;
    private AppointmentStatus appointmentstatus;

    private LocalDate appointmentdate;
    private LocalTime appointmenttime;

    private LocalDateTime createdatetime;
    private LocalDateTime updatedatetime;
}

