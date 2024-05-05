package org.example.services;

import org.example.models.Faculty;
import org.example.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> findAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFacultyById(String id) {
        return facultyRepository.findById(id);
    }

    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
    }
}
