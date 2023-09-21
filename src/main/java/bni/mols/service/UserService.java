/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.mols.service;

import bni.mols.model.Roll;
import bni.mols.model.User;
import bni.mols.repository.UserRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class UserService {

    @Autowired
    protected UserRepository userRepository;
//    protected RollService rollService;
//    protected ModelMapper modelMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));

    }

    public User save(User entity){
        return userRepository.save(entity);
    }
    
//    public User save(UserDTO userDTO) {
//        User user = modelMapper.map(userDTO, User.class);
//
//        List<Roll> roll = new ArrayList();
//        roll.add(rollService.findById(1L));
//        user.setRolls(roll);
//
//
//        User u = userRepository.save(user);
//        return u;
//    }

    public User update(long id, User entity) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        entity.setId(id);
        return userRepository.save(entity);
    }

    public User delete(Long id) {
        User data = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!"));
        userRepository.delete(data);
        return data;

    }
    
    public ByteArrayOutputStream getUsersExcelFile() throws IOException {
        List<User> users = userRepository.findAll();

        // Buat workbook Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("User");

            // Buat header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Username");
            headerRow.createCell(2).setCellValue("Password");
            headerRow.createCell(3).setCellValue("Roles");

            // Isi data
            int rowNum = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUserName());
                row.createCell(2).setCellValue(user.getPassword());

                // Gabungkan nama peran menjadi satu string
                StringBuilder rolesStringBuilder = new StringBuilder();
                for (Roll role : user.getRolls()) {
                    rolesStringBuilder.append(role.getName()).append(", ");
                }
                String roles = rolesStringBuilder.toString();
                if (roles.length() > 2) {
                    roles = roles.substring(0, roles.length() - 2); // Hapus koma dan spasi terakhir
                }

                row.createCell(3).setCellValue(roles);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // Cetak pesan ke konsol
            System.out.println("Excel file created successfully with " + (rowNum - 1) + " rows");

            return outputStream;
        } catch (IOException e) {
            // Tangani kesalahan IO jika diperlukan
            System.err.println("Error creating Excel file: " + e.getMessage());
            throw e;
        }
    }


}
