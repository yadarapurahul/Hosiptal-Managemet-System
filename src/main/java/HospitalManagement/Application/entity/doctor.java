package HospitalManagement.Application.entity;

import HospitalManagement.Application.Enum.DoctorStatus;
import HospitalManagement.Application.Enum.Gender;
import HospitalManagement.Application.Enum.Qualification;
import HospitalManagement.Application.Enum.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class doctor {

    @Id
    @Column(nullable = false, unique = true)
    private String doctorcode;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String aadharnumber;
    @Column(nullable = false, unique = true)
    private String pannumber;
    @Column(nullable = false, unique = true)
    private String phonenumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private BigDecimal experience;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Column(nullable = false)
    private String licencenumber;
    @Column(nullable = false)
    private BigDecimal consultationfee;
    @Column(nullable = false)
    private LocalDate joiningdate;
    @Enumerated(EnumType.STRING)
    private DoctorStatus doctorStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_code", nullable = false)
    private department department;

    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDate today = LocalDate.now();
        createdAt = today;
        updatedAt = today;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }


}
