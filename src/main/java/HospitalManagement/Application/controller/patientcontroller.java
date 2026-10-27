package HospitalManagement.Application.controller;

import HospitalManagement.Application.API.APIResponse;
import HospitalManagement.Application.DTO.PatientDto.patientRequest;
import HospitalManagement.Application.DTO.PatientDto.patientResponse;
import HospitalManagement.Application.entity.patient;
import HospitalManagement.Application.services.patientservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class patientcontroller {

    private final patientservice ps;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<patientResponse>> add(@RequestBody patientRequest par){
        patientResponse pr = ps.add(par);
        APIResponse<patientResponse> ap= new APIResponse<>(HttpStatus.CREATED.value(),true,"Patient is added successfully", LocalDateTime.now(),pr);

        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @GetMapping("/all/patient")
    public ResponseEntity<APIResponse<List<patientResponse>>> getall(){
        List<patientResponse> pr = ps.getall();
        APIResponse<List<patientResponse>> ap= new APIResponse<>(HttpStatus.CREATED.value(),true,"All patient in our hospital", LocalDateTime.now(),pr);

        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
//        return ps.getall();
    }

    @GetMapping("/get/{patientCode}")
    public ResponseEntity<APIResponse<patientResponse>> findById(@PathVariable String patientCode){
        patientResponse pr = ps.getId(patientCode);
        APIResponse<patientResponse> ap= new APIResponse<>(HttpStatus.CREATED.value(),true,"Patient id"+patientCode+"details", LocalDateTime.now(),pr);

        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
//        return ps.getId(id);
    }

    @PutMapping("/update/{patientCode}")
    public ResponseEntity<APIResponse<patientResponse>> update(@RequestBody patientRequest par, @PathVariable String patientCode){
        patientResponse pr = ps.update(par,patientCode);
        APIResponse<patientResponse> ap= new APIResponse<>(HttpStatus.CREATED.value(),true,"patient "+patientCode+" is updated", LocalDateTime.now(),pr);

        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
//        return ps.update(par, id);
    }

    @DeleteMapping("/delete/{patientCode}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable String patientCode){
        String s = ps.delete(patientCode);
        APIResponse<String> ap= new APIResponse<>(HttpStatus.OK.value(),true,"patient "+patientCode+" is deleted successfully", LocalDateTime.now(),s);

        return ResponseEntity.status(HttpStatus.OK).body(ap);
    }
}
