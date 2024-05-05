////package org.example.controllers;
//
//import org.example.controllers.AdminController;
//import org.example.models.Course;
//import org.example.models.Faculty;
//import org.example.repositories.CourseRepository;
//import org.example.repositories.FacultyRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AdminController.class)
//public class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CourseRepository courseRepository;
//
//    @MockBean
//    private FacultyRepository facultyRepository;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void getAllCourses_ShouldReturnCourses() throws Exception {
//        Course course1 = new Course("1", "Introduction to Computer Science", "Fall 2024", "101");
//        Course course2 = new Course("2", "Data Structures", "Fall 2024", "102");
//
//        List<Course> allCourses = Arrays.asList(course1, course2);
//
//        given(courseRepository.findAll()).willReturn(allCourses);
//
//        mockMvc.perform(get("/api/admin/courses")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].name", is("Introduction to Computer Science")))
//                .andExpect(jsonPath("$[1].name", is("Data Structures")));
//    }
//
//    @Test
//    public void getAllFaculty_ShouldReturnFaculty() throws Exception {
//        Faculty faculty1 = new Faculty("101", "John Doe", "Computer Science");
//        Faculty faculty2 = new Faculty("102", "Jane Smith", "Computer Science");
//
//        List<Faculty> allFaculty = Arrays.asList(faculty1, faculty2);
//
//        given(facultyRepository.findAll()).willReturn(allFaculty);
//
//        mockMvc.perform(get("/api/admin/faculty")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].name", is("John Doe")))
//                .andExpect(jsonPath("$[1].name", is("Jane Smith")));
//    }
//}
