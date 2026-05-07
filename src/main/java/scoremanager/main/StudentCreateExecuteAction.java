package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(); 
		Teacher teacher = (Teacher)session.getAttribute("user");
		int ent_year = 0; 
		String student_no = ""; 
		String student_name = ""; 
		String class_num = ""; 
		Student student = new Student();
		StudentDao studentDao = new StudentDao();
		Map<String, String> errors = new HashMap<>(); 

		ent_year = Integer.parseInt(req.getParameter("ent_year"));
		student_no = req.getParameter("no");
		student_name = req.getParameter("name");
		class_num = req.getParameter("class_num");

		if (ent_year == 0) { 
			errors.put("1", "入学年度を選択してください");
			req.setAttribute("errors", errors);
		} else {
			if (studentDao.get(student_no) != null) { 
				errors.put("2", "学生番号が重複しています");
				req.setAttribute("errors", errors);
			} else {
				student.setStudentNo(student_no);
				student.setStudentName(student_name);
				student.setEntYear(ent_year);
				student.setClassNum(class_num);
				student.setAttend(true);
				student.setSchool(teacher.getSchool());
				studentDao.save(student);
			}
		}

		req.setAttribute("ent_year", ent_year);

		req.setAttribute("no", student_no);

		req.setAttribute("name", student_name);

		req.setAttribute("class_num", class_num);

		if (errors.isEmpty()) { 

			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
		} else { 

			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
		}
	}

}
