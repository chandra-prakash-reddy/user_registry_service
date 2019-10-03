package com.employeeregistry.conrollers;

import com.employeeregistry.dto.PageDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import com.employeeregistry.services.EmployeeRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeRegistryController {

    @Autowired
    EmployeeRegistryService employeeRegistryService;

    @CrossOrigin
    @RequestMapping(path = "/login",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    ResponseEntity<Object> login(@RequestBody  UserLoginDTO userLoginDTO) throws UnAuthorizedException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.login(userLoginDTO));
    }

    @CrossOrigin
    @RequestMapping(path = "/signup",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    ResponseEntity<Object> signUp(@RequestBody SignUpEntity signUpEntity) throws UserExistsException {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.signUp(signUpEntity));
    }

    @CrossOrigin
    @RequestMapping(path = "/create",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    ResponseEntity<Object> createEmployee(@RequestBody  EmployeeEntity employeeEntity) throws EmployeeExistsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.createEmployee(employeeEntity));
    }

    @CrossOrigin
    @RequestMapping(path = "/update",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    ResponseEntity<Object> updateEmployee(@RequestBody EmployeeEntity employeeEntity) throws EmployeeNotFoundException {
        employeeEntity.setCreatedTime(employeeRegistryService.getEmployee(employeeEntity.getEmployeeId()).getCreatedTime());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.updateEmployee(employeeEntity));
    }

    @CrossOrigin
    @RequestMapping(path = "/employee/{employeeId}",method = RequestMethod.GET,produces = "application/json")
    ResponseEntity<Object> getEmployee(@PathVariable  String employeeId) throws EmployeeNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getEmployee(employeeId));
    }

    @CrossOrigin
    @RequestMapping(path = "/user/{userId}",method = RequestMethod.GET,produces = "application/json")
    ResponseEntity<Object> getUser(@PathVariable  String userId)  {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getUser(userId));
    }

    @CrossOrigin
    @RequestMapping(path = "/search",method = RequestMethod.POST,consumes = "text/plain",produces = "application/json")
    ResponseEntity<Object>searchEmployees(@RequestBody String keyword){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.searchEmployees(keyword));
    }

    @CrossOrigin
    @RequestMapping(path = "/employees",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    ResponseEntity<Object> getEmployeesByPage(@RequestBody PageDTO page){
        Pageable pageable= PageRequest.of(page.getPage(),page.getSize());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getEmployeesByPage(pageable));
    }

}
