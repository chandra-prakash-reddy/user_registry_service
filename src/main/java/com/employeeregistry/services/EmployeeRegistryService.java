package com.employeeregistry.services;

import com.employeeregistry.dto.UserDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeRegistryService {

    UserDTO getUser(String userId);

    boolean login(UserLoginDTO userLoginDTO) throws UnAuthorizedException;

    boolean signUp(SignUpEntity signUpEntity) throws UserExistsException;

    EmployeeEntity createEmployee(EmployeeEntity employeeEntity) throws EmployeeExistsException;

    EmployeeEntity updateEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity getEmployee(String employeeId) throws EmployeeNotFoundException;

    List<EmployeeEntity> searchEmployees(String keyword);

    Page<EmployeeEntity> getEmployeesByPage(Pageable page);

}
