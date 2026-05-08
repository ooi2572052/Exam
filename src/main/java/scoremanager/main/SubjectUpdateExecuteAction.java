package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログインユーザー
        Teacher teacher =
            (Teacher) req.getSession().getAttribute("user");

        // パラメータ取得（JSP と一致）
        String subjectCd = req.getParameter("subjectCd");
        String subjectName = req.getParameter("subjectName");

        // エラー格納
        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (subjectName == null || subjectName.isEmpty()) {
            errors.put("subjectName", "このフィールドを入力してください。");
        }

        // Bean作成
        Subject subject = new Subject();
        subject.setSubjectCd(subjectCd);
        subject.setSubjectName(subjectName);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        // エラーあり → 再表示
        if (!errors.isEmpty()) {

            req.setAttribute("subject", subject);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);

            return;
        }

        // 更新
        SubjectDao dao = new SubjectDao();
        dao.update(subject);

        // 完了画面へ
        req.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
           .forward(req, res);
    }
}
