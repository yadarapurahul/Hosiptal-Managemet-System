package HospitalManagement.Application.controller;


import HospitalManagement.Application.API.APIResponse;
import HospitalManagement.Application.DTO.DepartmentDto.departmentRequest;
import HospitalManagement.Application.DTO.DepartmentDto.departmentResponse;
import HospitalManagement.Application.services.departmentservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class departmentcontroller {

    private final departmentservice ds;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<departmentResponse>> add(@RequestBody departmentRequest dptr){
        departmentResponse dr = ds.add(dptr);
        APIResponse<departmentResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(), true, "Department Details Successfully added", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<departmentResponse>>> getall(){
        List<departmentResponse> dr = ds.findAll();
        APIResponse<List<departmentResponse>> ap = new APIResponse<>(HttpStatus.OK.value(),  true, "All Department Details Successfully ", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.OK).body(ap);
    }

    @GetMapping("/{departmentcode}")
    public ResponseEntity<APIResponse<departmentResponse>> getByDepartmentcode(@PathVariable String departmentcode){
        departmentResponse dr = ds.findByDepartmentcode(departmentcode);
        APIResponse<departmentResponse> ap = new APIResponse<>(HttpStatus.OK.value(), true, "Department Code "+departmentcode+" Details", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.OK).body(ap);
    }

    @PutMapping("/update/{departmentcode}")
    public ResponseEntity<APIResponse<departmentResponse>> update(@Valid @RequestBody departmentRequest dptr, @PathVariable String departmentcode){
        departmentResponse dr = ds.updateByDepartmentcode(dptr,departmentcode);
        APIResponse<departmentResponse> ap = new APIResponse<>(HttpStatus.CREATED.value(),  true, "Department Details Updated Successfully By Department Code", LocalDateTime.now(),dr);
        return ResponseEntity.status(HttpStatus.CREATED).body(ap);
    }

    @DeleteMapping("/delete/{departmentcode}")
    public ResponseEntity<APIResponse<String>> deleteByDepartmentcode(@PathVariable String departmentcode){
        String msg = ds.DeleteByDepartmentcode(departmentcode);
        APIResponse<String> ap = new APIResponse<>(HttpStatus.OK.value(), true,"Department Code "+departmentcode+" Deleted Successfully", LocalDateTime.now(),msg);
        return ResponseEntity.status(HttpStatus.OK).body(ap);
    }

}
