package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        String no = req.getParameter("no");

        // DAO
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // DBから対象学生を取得
        Student student = studentDao.get(no);

        // クラス一覧
        List<String> classList = classNumDao.filter(teacher.getSchool());

        // 年リスト
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 11; i++) {
            entYearSet.add(i);
        }

        // JSPへ渡す
        req.setAttribute("student", student);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}