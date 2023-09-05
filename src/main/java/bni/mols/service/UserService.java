/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.service;

import bni.mols.model.User;
import bni.mols.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author alfia
 */
@Service
@AllArgsConstructor
public class UserService{
    
    @Autowired
    protected UserRepository userRepository;
       
    public  List<User> findAll(){
    return userRepository.findAll();
    }
    
    public User findById(long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        
    }
    
    public User save(User entity){
        return userRepository.save(entity);
    }
    
    public User update(long id, User entity){
        if(!userRepository.findById(id).isPresent()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        entity.setId(id);
        return userRepository.save(entity);
    }
    
//    public  User delete(Long id){
//        User data = userRepository.findById(id)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
//        userRepository.delete(data);
//        return data;
//        
//    }
    
    public User delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }
}
