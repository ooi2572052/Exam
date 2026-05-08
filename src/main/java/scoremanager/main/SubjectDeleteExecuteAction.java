package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 1. ログインユーザーの取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // 2. パラメータ（削除対象の科目コード）取得
        String subjectCd = req.getParameter("cd"); // JSP側のname属性に合わせる

        // 3. Beanの作成
        Subject subject = new Subject();
        subject.setSubjectCd(subjectCd);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        // 4. 削除の実行
        SubjectDao dao = new SubjectDao();
        // dao.update ではなく dao.delete を呼び出す
        dao.delete(subject);

        // 5. 完了画面への設定
        req.setAttribute("title", "削除完了");
        // 表示するコンテンツを削除完了JSPに指定
        req.setAttribute("content", "subject_delete_done.jsp");

        // base.jspへフォワードして全体を表示
        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}