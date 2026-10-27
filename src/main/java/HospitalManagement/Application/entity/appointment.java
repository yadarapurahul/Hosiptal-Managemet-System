package HospitalManagement.Application.entity;


import HospitalManagement.Application.Enum.AppointmentStatus;
import HospitalManagement.Application.Enum.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class appointment {

    @Id
    private String appointmentcode ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pat_code", nullable = false)
    private patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code", nullable = false)
    private doctor doctor;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus appointmentstatus;

    @Column(nullable = false)
    private LocalDate appointmentdate;

    @Column(nullable = false)
    private LocalTime appointmenttime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdatetime;

    @Column(nullable = false)
    private LocalDateTime updateddatetime;

    @PrePersist
    public void OnCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdatetime = now;
        updateddatetime = now;
    }

    @PreUpdate
    public void OnUpdate() {
        updateddatetime = LocalDateTime.now();
    }


}
