/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.controller;

import bni.mols.model.ModelDTO.UserDTO;
import bni.mols.model.Roll;
import bni.mols.model.User;
import bni.mols.service.UserService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.management.relation.Role;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.findById(id), HttpStatus.OK);
    }
//
//    @PostMapping
//    public ResponseEntity<UserDTO> save(@RequestBody UserDTO entity){
//        return new ResponseEntity(userService.save(entity), HttpStatus.CREATED);
//    }
    
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User entity){
        return new ResponseEntity(userService.save(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User entity) {
        return new ResponseEntity(userService.update(id, entity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
    
    @GetMapping("/download")
    public String downloadPage() {
        return "download"; // Nama file HTML tanpa ekstensi
    }
    
    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadUserExcel() throws IOException {
        List<User> users = userService.findAll();

        // Create Excel workbook and sheet
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("User Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Username");
            headerRow.createCell(2).setCellValue("Password");
            headerRow.createCell(3).setCellValue("Roles"); // Tambahkan kolom Roles

            // Fill data
            int rowNum = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUserName());
                row.createCell(2).setCellValue(user.getPassword());

                // Gabungkan informasi peran (roles) ke dalam satu string
                String roles = user.getRolls().stream()
                        .map(Roll::getName)
                        .collect(Collectors.joining(", "));
                row.createCell(3).setCellValue(roles);
            }

            // Create output stream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "user_data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        }
    }
}
