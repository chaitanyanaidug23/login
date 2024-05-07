const baseUrl = '/faculty';
const facultyDropdown = document.getElementById('facultyDropdown');
const coursesContainer = document.getElementById('coursesContainer');
const studentCourseSelect = document.getElementById('studentCourseSelect');
const gradesCourseSelect = document.getElementById('gradesCourseSelect');
const studentsContainer = document.getElementById('studentsContainer');
const gradesContainer = document.getElementById('gradesContainer');
const announcementCourseSelect = document.getElementById('announcementCourseSelect');
const announcementText = document.getElementById('announcementText');
const welcomeMessage = document.getElementById('welcomeMessage');

function updateFacultyCourses() {
    const facultyId = facultyDropdown.value;
    welcomeMessage.textContent = facultyId ? `Welcome, ${facultyId}` : '';
    if (!facultyId) return;

    axios.get(`${baseUrl}/${facultyId}/courses`)
        .then(response => {
            const courses = response.data;
            const courseOptions = courses.map(course => `<option value="${course.id}">${course.name}</option>`).join('');
            [studentCourseSelect, gradesCourseSelect, announcementCourseSelect].forEach(select => {
                select.innerHTML = '<option value="">Select a course</option>' + courseOptions;
            });
            coursesContainer.innerHTML = courses.map(course => `<p>${course.name} - ${course.semester}</p>`).join('');
        })
        .catch(error => {
            console.error('Error fetching courses:', error);
            showMessage('Failed to fetch courses', 'error');
        });
}

function fetchStudents(courseId) {
    if (!courseId) {
        studentsContainer.innerHTML = '';
        return;
    }
    axios.get(`${baseUrl}/course/${courseId}/students`)
        .then(response => {
            const students = response.data;
            studentsContainer.innerHTML = students.map(student => `<li>${student.name} - ${student.email}</li>`).join('');
        })
        .catch(error => {
            console.error('Error fetching students:', error);
            showMessage('Failed to fetch students', 'error');
        });
}

function fetchGrades(courseId) {
    if (!courseId) {
        gradesContainer.innerHTML = '';
        return;
    }
    axios.get(`${baseUrl}/course/${courseId}/grades`)
        .then(response => {
            const grades = response.data;
            gradesContainer.innerHTML = grades.map(grade => `<li>${grade.studentId}: ${grade.value}</li>`).join('');
        })
        .catch(error => {
            console.error('Error fetching grades:', error);
            showMessage('Failed to fetch grades', 'error');
        });
}

function assignGrade() {
    const courseId = gradesCourseSelect.value;
    const studentId = document.getElementById('studentSelect').value;
    const grade = document.getElementById('gradeSelect').value;
    if (!courseId || !studentId || !grade) {
        alert('Please select all fields.');
        return;
    }
    axios.post(`${baseUrl}/course/${courseId}/student/${studentId}/grade`, { grade })
        .then(response => {
            alert('Grade assigned successfully!');
            fetchGrades(courseId); // Refresh the grade list
        })
        .catch(error => {
            console.error('Assigning grade error:', error);
            alert('Failed to assign grade');
        });
}

function postAnnouncement() {
    const courseId = announcementCourseSelect.value;
    const announcement = announcementText.value;
    if (!courseId) {
        alert('Please select a course.');
        return;
    }
    axios.post(`${baseUrl}/course/${courseId}/announcements`, { text: announcement })
        .then(response => {
            alert('Announcement posted successfully!');
            announcementText.value = ''; // Clear the input after posting
        })
        .catch(error => {
            alert('Failed to post announcement');
            console.error('Posting announcement error:', error);
        });
}

function showMessage(message, type) {
    const messageBox = document.createElement('div');
    messageBox.className = `alert ${type === 'error' ? 'error' : 'success'}`;
    messageBox.textContent = message;
    document.body.appendChild(messageBox);
    setTimeout(() => messageBox.remove(), 3000);
}
