package com.example.amigos_mango;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student,String> {
    Optional<Student> findStudentByEmail(String email);
}
