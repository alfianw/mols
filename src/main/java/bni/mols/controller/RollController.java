/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.controller;

import bni.mols.model.Roll;
import bni.mols.service.RollService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alfia
 */

@RequestMapping("/roll")
@RestController
@AllArgsConstructor
public class RollController{
    
    @Autowired
    private RollService rollService;
    
    @GetMapping
    public ResponseEntity<List<Roll>> findAll(){
        return new ResponseEntity(rollService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Roll> findById(@PathVariable("id") Long  id){
        return new ResponseEntity(rollService.findById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Roll> save(@RequestBody Roll entity){
        return new ResponseEntity(rollService.save(entity), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Roll> update(@PathVariable("id") Long id, @RequestBody Roll entity){
        return new ResponseEntity(rollService.update(id, entity), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Roll> delete(@PathVariable("id") Long id){
        return new ResponseEntity(rollService.delete(id), HttpStatus.OK);
    }
}
