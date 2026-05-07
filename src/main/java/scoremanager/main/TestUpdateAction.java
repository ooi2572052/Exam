package scoremanager.main;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");

        // 修正ポイント👇
        String schoolCd = teacher.getSchool().getSchoolCd();

        // DAO
        TestDao dao = new TestDao();

        // 成績取得
        Test test = dao.get(studentNo, subjectCd, schoolCd);

        // JSPへ渡す
        req.setAttribute("test", test);

        req.getRequestDispatcher("test_update.jsp").forward(req, res);
    }
}