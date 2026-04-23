package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String attendStr = req.getParameter("isAttend");

        // 型変換
        int entYear = (entYearStr != null && !entYearStr.isEmpty())
                ? Integer.parseInt(entYearStr)
                : 0;

        boolean isAttend = attendStr != null;

        // Studentにセット
        Student student = new Student();
        student.setStudentNo(no);
        student.setStudentName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);

        // DB更新
        StudentDao dao = new StudentDao();
        dao.save(student);

        // 完了画面へ
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
    }
}