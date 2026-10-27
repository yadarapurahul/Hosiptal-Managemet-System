package HospitalManagement.Application.controller;

import HospitalManagement.Application.API.APIResponse;
import HospitalManagement.Application.DTO.AppointmentDto.appointmentRequest;
import HospitalManagement.Application.DTO.AppointmentDto.appointmentResponse;
import HospitalManagement.Application.services.appointmentservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class appointmentcontroller {

    private final appointmentservice as;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<appointmentResponse>> add(@RequestBody appointmentRequest art){
        appointmentResponse ar = as.add(art);
        APIResponse<appointmentResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(), true, "Appointment Successfully Booked", LocalDateTime.now(), ar);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<appointmentResponse>>> getall(){
        List<appointmentResponse> ar = as.getAllappointments();
        APIResponse<List<appointmentResponse>> ap = new APIResponse<>(HttpStatus.OK.value(), true, "Get all Appointment Details", LocalDateTime.now(), ar);
        return ResponseEntity.ok(ap);
    }

    @GetMapping("/{appointmentcode}")
    public ResponseEntity<APIResponse<appointmentResponse>> getByappointmentcode(@PathVariable String appointmentcode){
        appointmentResponse ar = as.getByappointmentcode(appointmentcode);
        APIResponse<appointmentResponse> apr = new APIResponse<>(HttpStatus.OK.value(), true, "Get Appointment Deatils By Appointment Code", LocalDateTime.now(), ar);
        return ResponseEntity.ok(apr);

    }

    @PutMapping("/update/{appointmentcode}")
    public ResponseEntity<APIResponse<appointmentResponse>> updateByappointmentcode(@RequestBody appointmentRequest art, @PathVariable String appointmentcode){
        appointmentResponse ar = as.updateByappointmentcode(appointmentcode, art);
        APIResponse<appointmentResponse> apr = new APIResponse<>(HttpStatus.CREATED.value(), true, "Update Appointment Details", LocalDateTime.now(), ar);
        return ResponseEntity.ok(apr);
    }

    @DeleteMapping("/delete/{appointementcode}")
    public ResponseEntity<APIResponse<appointmentResponse>> deleteByappointmentcode(@PathVariable String appointmentcode) {
        appointmentResponse ar = as.getByappointmentcode(appointmentcode);
        APIResponse<appointmentResponse> apr = new APIResponse<>(HttpStatus.OK.value(), true, "Delete Appointment Details", LocalDateTime.now(), ar);
        return ResponseEntity.ok(apr);
    }


}
