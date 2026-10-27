package HospitalManagement.Application.DTO.AppointmentDto;

import HospitalManagement.Application.Enum.AppointmentStatus;
import HospitalManagement.Application.Enum.Specialization;
import HospitalManagement.Application.entity.appointment;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class appointmentRequest {

    @NotBlank(message = "Patient Code is required")
    private String patientCode;

    @NotBlank(message = "Doctor Code is required")
    private String doctorcode;

    @NotNull(message = "specialization is required")
    private Specialization specialty;

    @NotBlank(message = "Appointment Reason is required")
    private String reason;

    @NotNull(message = "Appointment Status is required")
    private AppointmentStatus appointmentstatus;

    @NotNull(message = "Appointment Date is required")
    @FutureOrPresent(message = "Appointment Date cannot be in the past")
    private LocalDate appointmentdate;

    @NotNull(message = "Appointment Time is required")
    private LocalTime appointmenttime;


}
