//import org.assertj.core.api.Assertions;
//import org.example.Application;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//public class CourseIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void getAllCourses_ShouldReturnCourses_WhenCoursesExist() {
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/admin/courses", String.class);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//}
