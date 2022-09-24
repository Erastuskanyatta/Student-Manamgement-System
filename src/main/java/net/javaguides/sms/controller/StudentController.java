package net.javaguides.sms.controller;

		import net.javaguides.sms.service.impl.StudentServiceImpl;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.stereotype.Controller;
		import org.springframework.ui.Model;
		import org.springframework.web.bind.annotation.*;

		import net.javaguides.sms.entity.Student;
		import net.javaguides.sms.service.StudentService;

		import java.util.HashMap;
		import java.util.Map;

@Controller
public class StudentController {

	private StudentService studentService;
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";

	}
	@PostMapping("/newStudent")
	public ResponseEntity<?> newStudent(@RequestBody Student student){
		Map<String, Object> creatingNewStudent = new HashMap<>();
		Student newStudents = studentServiceImpl.createNewStudent(student);
		creatingNewStudent.put("data", newStudents);
		creatingNewStudent.put("status", HttpStatus.CREATED);
		return new ResponseEntity<>(creatingNewStudent, HttpStatus.CREATED);
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student, Model model) {
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());

		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	// handler method to handle delete student request

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

}
