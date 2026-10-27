package HospitalManagement.Application.entity;


import HospitalManagement.Application.Enum.DepartmentCategory;
import HospitalManagement.Application.Enum.departmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class department {

    @Id
    private String departmentcode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartmentCategory departmentcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_dept_code")
    private department parentdepartment;

    @Column(nullable = false)
    private String departmentname;

    @OneToMany(mappedBy = "parentdepartment")
    private List<department> subdepartments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private departmentStatus status;

    @Column(nullable = false,updatable = false)
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
