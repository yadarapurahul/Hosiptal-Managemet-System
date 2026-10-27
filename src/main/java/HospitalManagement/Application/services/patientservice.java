package HospitalManagement.Application.services;

import HospitalManagement.Application.DTO.PatientDto.patientMapper;
import HospitalManagement.Application.DTO.PatientDto.patientRequest;
import HospitalManagement.Application.DTO.PatientDto.patientResponse;
import HospitalManagement.Application.Exceptions.DuplicateResourceException;
import HospitalManagement.Application.Exceptions.ResourceNotFound;
import HospitalManagement.Application.entity.patient;
import HospitalManagement.Application.repositry.patientrepositry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class patientservice {

    private final patientrepositry pr;
    private final patientMapper pm;

    private String generatepatientCode(){
        List<patient> p = pr.findAll();

        int maxNum = p.stream().map(patient::getPatientCode).filter(code-> code != null && code.matches("PA\\d+")).mapToInt(code-> Integer.parseInt(code.substring(2))).max().orElse(0);

        return String.format("PA%03d", maxNum + 1);
    }

    public patientResponse add(patientRequest par){

        String patientCode = generatepatientCode();

        // Duplicate check
        if(pr.existsByPatientCode(patientCode)){
            log.warn("duplicate patient");
            throw new DuplicateResourceException("Patient Code is already exists");
        }

        if(pr.findByEmail(par.getEmail()).isPresent()){
            log.warn("duplicate Email");
            throw new DuplicateResourceException("Email is already exists");
        }

        if (pr.findByAadharnumber(par.getAadharnumber()).isPresent()){
            log.warn("duplicate Aadhar Number");
            throw new DuplicateResourceException("AadharNumber is already exists.");
        }

        if (pr.findByPhonenumber(par.getPhonenumber()).isPresent()){
            log.warn("duplicate phone number");
            throw new DuplicateResourceException("Phone Number is already exists");
        }

        // Convert DTO to entity
        patient p = pm.toEntity(par);
        // Main fix: assign the generated primary key
        p.setPatientCode(patientCode);
        patient savedpatient = pr.save(p);
        return pm.toResponse(savedpatient);



    }

    public List<patientResponse> getall(){
        log.info("Patient all details");
        return pr.findAll().stream().map(pm::toResponse).toList();
    }

    public patientResponse getId(String patientCode){
        log.info("Patient "+patientCode+" details");
        patient p=pr.findByPatientCode(patientCode).orElseThrow(()-> new ResourceNotFound("id is not found"+patientCode));
        return pm.toResponse(p);
    }


    public patientResponse update(patientRequest par, String patientCode) {
        log.debug("patient details updated");
        patient p = pr.findByPatientCode(patientCode).orElseThrow(() -> new ResourceNotFound(patientCode + "not found"));
        pm.updatePatientFromRequests(par, p);
//        patient p_id = pr.findById(id).orElseThrow(()-> new RuntimeException("id is not found"+id));
//        p_id.setPatientCode(p.getPatientCode());
//        p_id.setFirstname(p.getFirstname());
//        p_id.setLastname(p.getLastname());
//        p_id.setAge(p.getAge());
//        p_id.setGender(p.getGender());
//        p_id.setPhonenumber(p.getPhonenumber());
//        p_id.setEmail(p.getEmail());
//        p_id.setAadharnumber(p.getAadharnumber());
//        p_id.setBloodgroup(p.getBloodgroup());
//        p_id.setAddress(p.getAddress());
//        p_id.setAdmitDate(p.getAdmitDate());
//        p_id.setEmergencyContact(p.getEmergencyContact());
//        p_id.setStatus(p.getStatus());
//
//        return pr.save(p_id);
        patient updated=pr.save(p);

        return pm.toResponse(updated);

    }

    public String delete(String patientCode){
        log.info("patient is deleted");
        patient p = pr.findByPatientCode(patientCode).orElseThrow(()-> new ResourceNotFound(patientCode + "not found"));
        pr.delete(p);
        return "Patient "+patientCode+" id is deleted successfully";

    }
}
