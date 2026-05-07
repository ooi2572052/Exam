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

        // パラメータ取得
        String subjectCd = req.getParameter("subjectCd");

        // DAO
        SubjectDao dao = new SubjectDao();

        // 科目取得
        Subject subject =
            dao.get(subjectCd,
                    teacher.getSchool().getSchoolCd());

        // リクエストへセット
        req.setAttribute("subject", subject);

        // base.jspへ渡す
        req.setAttribute("title", "科目情報変更");
        req.setAttribute("content", "/scoremanager/main/subject_update.jsp");

        // 画面表示
        req.getRequestDispatcher("/common/base.jsp")
           .forward(req, res);
    }
}