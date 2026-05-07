package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        SubjectDao dao = new SubjectDao();
        // 1. DBからログインユーザーの学校の科目をすべて取得
        List<Subject> subjects = dao.findAll(teacher.getSchool().getSchoolCd());

        // 2. リクエスト属性に "subjects" という名前でセット（JSPの items="${subjects}" と一致させる）
        req.setAttribute("subjects", subjects);

        // 3. JSPへフォワード
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}