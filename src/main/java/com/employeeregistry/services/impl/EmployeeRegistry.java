package com.employeeregistry.services.impl;

import com.employeeregistry.dto.UserDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import com.employeeregistry.repository.EmployeeRepository;
import com.employeeregistry.repository.SignUpRepository;
import com.employeeregistry.services.EmployeeRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeRegistry implements EmployeeRegistryService {

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private EmployeeRepository employeeRepository;



    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public UserDTO getUser(String userId) {
        SignUpEntity signUpEntity=signUpRepository.findByUserId(userId);
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(signUpEntity.getEmail());
        userDTO.setName(signUpEntity.getUserName());
        userDTO.setUserId(userId);
        return userDTO;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) throws UnAuthorizedException {
        SignUpEntity signUpEntity=signUpRepository.findByUserIdAndPassword(userLoginDTO.getUserId(),userLoginDTO.getPassword());
        if(signUpEntity==null) throw new UnAuthorizedException("userid or password is incorrect");
        return true;
    }

    @Override
    public boolean signUp(SignUpEntity signUpEntity) throws UserExistsException {
        SignUpEntity checkedUserEntity=signUpRepository.findByUserId(signUpEntity.getUserId());
        signUpEntity.setCreatedTime(new Date(System.currentTimeMillis()));
        if(checkedUserEntity!=null) throw new UserExistsException("user id : ["+signUpEntity.getUserId()+"] already exists");
        else signUpRepository.save(signUpEntity);
        return true;
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity) throws EmployeeExistsException {
        employeeEntity.setModifiedTime(new Date(System.currentTimeMillis()));
        employeeEntity.setCreatedTime(new Date(System.currentTimeMillis()));
        EmployeeEntity checkedEmployee=employeeRepository.findByEmployeeId(employeeEntity.getEmployeeId());
        if(checkedEmployee!=null) throw new EmployeeExistsException("employee id : ["+employeeEntity.getEmployeeId()+"] already exists");
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        employeeEntity.setModifiedTime(new Date(System.currentTimeMillis()));
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity getEmployee(String employeeId) throws EmployeeNotFoundException {
        EmployeeEntity employeeEntity=employeeRepository.findByEmployeeId(employeeId);
        if(employeeEntity==null) throw new EmployeeNotFoundException("employee id : ["+employeeEntity.getEmployeeId()+"] not found");
        return employeeEntity;
    }

    @Override
    public List<EmployeeEntity> searchEmployees(String keyword) {
        return employeeRepository.findByEmployeeIdLikeOrFirstNameLikeOrLastNameLike(keyword,keyword,keyword);
    }

    @Override
    public Page<EmployeeEntity> getEmployeesByPage(Pageable page) {
        return employeeRepository.findAll(page);
    }
}
