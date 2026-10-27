package HospitalManagement.Application.services;


import HospitalManagement.Application.DTO.DoctorDto.doctorMapper;
import HospitalManagement.Application.DTO.DoctorDto.doctorRequest;
import HospitalManagement.Application.DTO.DoctorDto.doctorResponse;
import HospitalManagement.Application.Exceptions.DuplicateResourceException;
import HospitalManagement.Application.Exceptions.ResourceNotFound;
import HospitalManagement.Application.entity.department;
import HospitalManagement.Application.entity.doctor;
import HospitalManagement.Application.repositry.departmentrepositry;
import HospitalManagement.Application.repositry.doctorrepositry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class doctorservice {

    private final doctorrepositry dry;
    private final departmentrepositry dptr;
    private final doctorMapper dm;

    private String generatedoctorcode(){
        List<doctor> doc = dry.findAll();

        int maxNum = doc.stream().map(doctor::getDoctorcode).filter(doccode -> doccode != null && doccode.matches("DOC\\d+")).mapToInt(doccode -> Integer.parseInt(doccode.substring(3))).max().orElse(0);

        return String.format("DOC%03d", maxNum + 1);
    }

    public doctorResponse add(doctorRequest docr){

//    1. Find the department and validate its code
        department dept = dptr.findByDepartmentcode(docr.getDepartmentcode()).orElseThrow(()->new ResourceNotFound("Department Code"+docr.getDepartmentcode()+" not found"));

//        2. Genrate the doctor code
        String doctorcode = generatedoctorcode();


//        3. check duplicate doctor code
        if (dry.existsByDoctorcode(doctorcode)){
            log.warn(doctorcode+" already exists");
            throw new DuplicateResourceException("Doctor Code is already exists");
        }

//        4. check duplicate doctor's phone number
        if (dry.findByPhonenumber(docr.getPhonenumber()).isPresent()){
            log.warn(docr.getPhonenumber()+" already exists");
            throw new DuplicateResourceException("Phone Number is already exists");
        }

        //        5. check duplicate doctor's Aadhar number
        if (dry.findByAadharnumber(docr.getAadharnumber()).isPresent()){
            log.warn(docr.getAadharnumber()+" already exists");
            throw new DuplicateResourceException("Aadhar Number is already exists");
        }

        //        6. check duplicate doctor's Email ID
        if (dry.findByEmail(docr.getEmail()).isPresent()){
            log.warn(docr.getEmail()+" already exists");
            throw new DuplicateResourceException("Email is already exists");
        }

        //        7. check duplicate doctor's pan number

        if (dry.findByPannumber(docr.getPannumber()).isPresent()){
            log.warn(docr.getPannumber()+" already exists");
            throw new DuplicateResourceException("Pan Number is already exists");
        }



//        8. convert requests DTO to entity
        doctor doc = dm.toEntity(docr);

//        9. Assign generated code and department
        doc.setDoctorcode(doctorcode);
        doc.setDepartment(dept);

//       10. Save and return response
        doctor saveddoc = dry.save(doc);

        log.info("Doctor created successfully: {}", saveddoc.getDoctorcode());
        return dm.toResponse(saveddoc);
    }

    public List<doctorResponse> getAll(){
        log.info("Doctor All details");
        return dry.findAll().stream().map(dm::toResponse).toList();
    }

    public doctorResponse getbyid(String doctorcode){
        log.info("Doctor Details by doctor code");
        doctor d = dry.findById(doctorcode).orElseThrow(()->new ResourceNotFound("Doctor code "+doctorcode+" is not found" ));
        return dm.toResponse(d);
    }

    public doctorResponse update(doctorRequest docr, String  doctorcode){
        log.info("Updating doctor: {}", doctorcode);
        doctor doc = dry.findById(doctorcode).orElseThrow(()->new ResourceNotFound("Doctor code "+doctorcode+" is not found"));
        dm.updateDoctorFromRequests(docr,doc);

        if (docr.getDepartmentcode()!=null && !docr.getDepartmentcode().isBlank()){
            department dept = dptr.findByDepartmentcode(docr.getDepartmentcode()).orElseThrow(()->new ResourceNotFound("Department Code"+docr.getDepartmentcode()+" not found"));
            doc.setDepartment(dept);

            log.info("Doctor department changed to: {}",
                    docr.getDepartmentcode());
        }

        doctor saveddoc = dry.save(doc);
        return dm.toResponse(saveddoc);


    }

    public String deleteById(String doctorcode){
        log.info("Deleting doctor: {}", doctorcode);
        doctor doc = dry.findById(doctorcode).orElseThrow(()->new ResourceNotFound("Doctor code "+doctorcode+" is not found"));
        dry.delete(doc);
        return "Doctor Code "+doctorcode+" has been deleted";
    }

}
