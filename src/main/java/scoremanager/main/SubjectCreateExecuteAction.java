package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res)
                        throws Exception {

        // ログインユーザー
        Teacher teacher =
            (Teacher) req.getSession().getAttribute("user");

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // Bean
        Subject subject = new Subject();

        subject.setSubjectCd(cd);
        subject.setSubjectName(name);

        // ← これ重要
        subject.setSchoolCd(
            teacher.getSchool().getSchoolCd()
        );

        // 保存
        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        // 一覧へ
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}