package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログインユーザー
        Teacher teacher =
            (Teacher) req.getSession().getAttribute("user");

        // パラメータ取得
        String subjectCd = req.getParameter("subjectCd");
        String subjectName = req.getParameter("subjectName");

        // エラー格納
        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (subjectName == null || subjectName.isEmpty()) {
            errors.put("subjectName", "科目名を入力してください");
        }

        // Bean作成
        Subject subject = new Subject();

        subject.setSubjectCd(subjectCd);
        subject.setSubjectName(subjectName);
        subject.setSchoolCd(
            teacher.getSchool().getSchoolCd()
        );

        // エラーあり
        if (!errors.isEmpty()) {

            req.setAttribute("subject", subject);
            req.setAttribute("errors", errors);

            req.setAttribute("title", "科目情報変更");
            req.setAttribute("content", "/scoremanager/main/subject_update.jsp");

            req.getRequestDispatcher("/common/base.jsp")
               .forward(req, res);

            return;
        }

        // 更新
        SubjectDao dao = new SubjectDao();

        dao.update(subject);

        // 完了画面
        req.setAttribute("title", "変更完了");
        req.setAttribute("content", "/scoremanager/main/subject_update_done.jsp");

        req.getRequestDispatcher("/common/base.jsp")
           .forward(req, res);
    }
}