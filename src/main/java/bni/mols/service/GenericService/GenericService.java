/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.service.GenericService;

import bni.mols.model.BaseEntity.BaseEntity;
import bni.mols.repository.GenericRepository.GenericRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author alfia
 */
@Service
public class GenericService<T extends BaseEntity> {
    
    @Autowired
    protected  GenericRepository<T> genericRepository;
       
    public  List<T> findAll(){
    return genericRepository.findAll();
    }
    
    public T findById(long id){
        return genericRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        
    }
    
    public T save(T entity){
        return genericRepository.save(entity);
    }
    
    public T update(long id, T entity){
        if(!genericRepository.findById(id).isPresent()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        entity.setId(id);
        return genericRepository.save(entity);
    }
    
    public  T delete(Long id){
        T data = genericRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        genericRepository.delete(data);
        return data;
        
    }
    
}
