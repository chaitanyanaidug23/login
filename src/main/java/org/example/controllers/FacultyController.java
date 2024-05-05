package org.example.controllers;

import org.example.models.Faculty;
import org.example.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Faculty> getAllFaculty() {
        return facultyService.findAllFaculty();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable String id) {
        Optional<Faculty> faculty = facultyService.findFacultyById(id);
        return faculty.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.saveFaculty(faculty);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String id, @RequestBody Faculty updatedFaculty) {
        return facultyService.findFacultyById(id)
                .map(existingFaculty -> {
                    existingFaculty.setName(updatedFaculty.getName());
                    existingFaculty.setDepartment(updatedFaculty.getDepartment());
                    Faculty savedFaculty = facultyService.saveFaculty(existingFaculty);
                    return ResponseEntity.ok().body(savedFaculty);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFaculty(@PathVariable String id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
