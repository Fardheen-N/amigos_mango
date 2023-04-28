package com.example.amigos_mango;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepo studentRepo;
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }
}
