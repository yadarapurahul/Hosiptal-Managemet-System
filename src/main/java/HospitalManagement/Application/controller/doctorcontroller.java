package HospitalManagement.Application.controller;


import HospitalManagement.Application.API.APIResponse;
import HospitalManagement.Application.DTO.DoctorDto.doctorRequest;
import HospitalManagement.Application.DTO.DoctorDto.doctorResponse;
import HospitalManagement.Application.services.doctorservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class doctorcontroller {

    private final doctorservice docs;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<doctorResponse>> add(@RequestBody doctorRequest docr){
        doctorResponse dr = docs.add(docr);
        APIResponse<doctorResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(), true, "Doctor Details are added successfully", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<doctorResponse>>> getaall(){
        List<doctorResponse> dr = docs.getAll();
        APIResponse<List<doctorResponse>> ap = new APIResponse<>(HttpStatus.CREATED.value(),  true, "All Doctors Details", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @GetMapping("/doctor/{doctorcode}")
    public ResponseEntity<APIResponse<doctorResponse>> getDoctorById(@PathVariable String doctorcode){
        doctorResponse dr = docs.getbyid(doctorcode);
        APIResponse<doctorResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(),  true, "Doctor Details By Doctor code", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @PutMapping("/update/{doctorcode}")
    public ResponseEntity<APIResponse<doctorResponse>> updateDoctorById(@RequestBody doctorRequest docr, @PathVariable String doctorcode){
        doctorResponse dr = docs.update(docr,doctorcode);
        APIResponse<doctorResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(),true, "Doctor Details Are Updated", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @DeleteMapping("/delete/{doctorcode}")
    public ResponseEntity<APIResponse<String>> deleteDoctorById(@PathVariable String doctorcode){
        String s = docs.deleteById(doctorcode);
        APIResponse<String> ap = new APIResponse<>(HttpStatus.OK.value(),true, "Doctor Details Are Deleted By Doctor code", LocalDateTime.now(),s);
        return ResponseEntity.status(HttpStatus.OK).body(ap);

    }







}
