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
        String subjectCd = req.getParameter("cd");

        // DAO
        SubjectDao dao = new SubjectDao();

        // 科目取得
        Subject subject =
            dao.get(subjectCd,
                    teacher.getSchool().getSchoolCd());

        // リクエストへセット（これはOK）
        req.setAttribute("subject", subject);

        // ★ base.jspに渡すのは「URLパラメータ」
        String content = "/scoremanager/main/subject_update.jsp";
        String title = "科目情報変更";

        // 画面表示（paramで渡す）
        req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
        .forward(req, res);
    }
}