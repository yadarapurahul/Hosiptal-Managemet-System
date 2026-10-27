package HospitalManagement.Application.services;


import HospitalManagement.Application.DTO.DepartmentDto.departmentMapper;
import HospitalManagement.Application.DTO.DepartmentDto.departmentRequest;
import HospitalManagement.Application.DTO.DepartmentDto.departmentResponse;
import HospitalManagement.Application.Exceptions.DuplicateResourceException;
import HospitalManagement.Application.Exceptions.ResourceNotFound;
import HospitalManagement.Application.entity.department;
import HospitalManagement.Application.repositry.departmentrepositry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class departmentservice {

    private final departmentrepositry dptr;
    private final departmentMapper dm;

    private String generatedeptcode(){
        List<department> dept = dptr.findAll();

        int maxnum = dept.stream().map(department::getDepartmentcode).filter(de->de != null && de.matches("DEPT\\d+")).mapToInt(d->Integer.parseInt(d.substring(4))).max().orElse(0);
        return  String.format("DEPT%03d",maxnum + 1);
    }

    public departmentResponse add(departmentRequest dert){

//        1. Find and validate the parent department

        department dept = null;

        if(dert.getParentdepartmentcode() != null && !dert.getParentdepartmentcode().isBlank()){
            dept = dptr.findByDepartmentcode(dert.getParentdepartmentcode()).orElseThrow(()->new ResourceNotFound("Parent Department Code Not Found"));

//            2. Validate parent and child categories

            if (dept.getDepartmentcategory() != dert.getDepartmentcategory()){
                throw new IllegalArgumentException("Parent and child must have the same category");
            }
        }

//        3. Check duplicate names

        boolean duplicateName;

        if(dept == null){
            duplicateName = dptr.existsByDepartmentcategoryAndParentdepartmentIsNull(dert.getDepartmentname());
        }
        else{
            duplicateName = dptr.existsByDepartmentnameAndParentdepartment(dert.getDepartmentname(),dept);
        }

        if(duplicateName){
            throw new DuplicateResourceException("Department Name already Exists under this parent");
        }

//        4. Generate department code
        String departmentcode = generatedeptcode();

//        5. Check the duplicate department code

        if (dptr.existsBydepartmentcode(departmentcode)){
            log.warn(departmentcode + " already exists");
            throw new DuplicateResourceException("Department Code already exists");
        }


//        6. convert requests DTO to entity
        department dep = dm.toEntity(dert);
        dep.setDepartmentcode(departmentcode);
        dep.setParentdepartment(dept);

//        7. Save department
        department savedep=dptr.save(dep);

//      8. Return Repsonse DTO

        log.info("Department Created: {}", departmentcode);
        return dm.toResponse(savedep);
    }

    public List<departmentResponse> findAll(){
        log.info("All Departments Details");
        return dptr.findAll().stream().map(dm::toResponse).toList();
    }

    public departmentResponse findByDepartmentcode(String departmentcode){
        log.info("Get Department by Department Code");
        department d = dptr.findByDepartmentcode(departmentcode).orElseThrow(()-> new ResourceNotFound("Department Code "+departmentcode+" Not Found"));
        return dm.toResponse(d);
    }

    public departmentResponse updateByDepartmentcode(departmentRequest dert, String departmentcode){
        log.debug("Update Department by Department Code");
        // 1. Find the existing department
        department d = dptr.findByDepartmentcode(departmentcode).orElseThrow(()-> new ResourceNotFound("Department Code "+departmentcode+" Not Found"));

//        2. Update the existing entity using MapStruct
        dm.updateDepartmentFromRequests(dert,d);

//        3. Save the updated entity to the database
        department updated = dptr.save(d);

//        4. Covert the saved entity into a response DTO
        return dm.toResponse(updated);
    }

    public String DeleteByDepartmentcode(String departmentcode){
        log.info("Department deleted: {}", departmentcode);
        department dept = dptr.findByDepartmentcode(departmentcode).orElseThrow(()-> new ResourceNotFound("Department Code "+departmentcode+ " Not Found"));
        dptr.delete(dept);
        return "Department "+departmentcode+ " code is Deleted Successfully";
    }

}
