///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package bni.mols.controller.GenericController;
//
//import bni.mols.model.BaseEntity.BaseEntity;
//import bni.mols.service.GenericService.GenericService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// *
// * @author alfia
// */
//@ResponseBody
//public class GenericController<T extends BaseEntity>{
//    
//    @Autowired
//    private GenericService<T> genericService;
//    
//    @GetMapping
//    public ResponseEntity<List<T>> findAll(){
//        return new ResponseEntity(genericService.findAll(), HttpStatus.OK);
//    }
//    
//    @GetMapping("/{id}")
//    public ResponseEntity<T> findById(@PathVariable("id") Long  id){
//        return new ResponseEntity(genericService.findById(id), HttpStatus.OK);
//    }
//    
//    @PostMapping
//    public ResponseEntity<T> save(@RequestBody T entity){
//        return new ResponseEntity(genericService.save(entity), HttpStatus.CREATED);
//    }
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<T> update(@PathVariable("id") Long id, @RequestBody T entity){
//        return new ResponseEntity(genericService.update(id, entity), HttpStatus.CREATED);
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<T> delete(@PathVariable("id") Long id){
//        return new ResponseEntity(genericService.delete(id), HttpStatus.OK);
//    }
//}
