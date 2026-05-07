package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログインユーザー
        Teacher teacher =
            (Teacher) req.getSession().getAttribute("user");

        // パラメータ
        String subjectCd = req.getParameter("subjectCd");

        // DAO
        SubjectDao dao = new SubjectDao();

        // 科目取得
        Subject subject =
            dao.get(subjectCd, teacher.getSchool().getSchoolCd());

        // JSPへ渡す
        req.setAttribute("subject", subject);

        // 画面表示
        req.getRequestDispatcher("subject_update.jsp")
           .forward(req, res);
    }
}