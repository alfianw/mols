/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.service;

import bni.mols.model.Roll;
import bni.mols.model.User;
import bni.mols.repository.RollRepository;
import java.util.List;
import java.util.stream.Stream;
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
public class RollService{
    
    @Autowired
    protected RollRepository rollRepository;
       
    public  List<Roll> findAll(){
    return rollRepository.findAll();
    }
    
    public Roll findById(long id){
        return rollRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        
    }
    
    public Roll save(Roll entity){
        return rollRepository.save(entity);
    }
    
    public Roll update(long id, Roll entity){
        if(!rollRepository.findById(id).isPresent()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        entity.setId(id);
        return rollRepository.save(entity);
    }
    
    public  Roll delete(Long id){
        Roll data = rollRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        rollRepository.delete(data);
        return data;
        
    }

    public Stream<Object> save(String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object save(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
