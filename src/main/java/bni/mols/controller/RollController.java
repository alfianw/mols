/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.controller;

import bni.mols.controller.GenericController.GenericController;
import bni.mols.model.Roll;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alfia
 */

@RequestMapping("/roll")
@RestController
public class RollController extends GenericController<Roll>{
    
}
