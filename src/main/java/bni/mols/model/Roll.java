/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.model;

import bni.mols.model.BaseEntity.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alfia
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roll extends BaseEntity{
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "rolls")
    private List<User> users;
    
}
