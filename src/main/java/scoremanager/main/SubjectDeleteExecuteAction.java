package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 削除する科目コードを取得
        String cd = req.getParameter("cd");

        Subject subject = new Subject();
        subject.setSubjectCd(cd);
        subject.setSchoolCd(teacher.getSchool().getSchoolCd());

        SubjectDao sDao = new SubjectDao();
        // DBから削除
        sDao.delete(subject);

        // 一覧へ戻る
        res.sendRedirect("SubjectList.action");
    }
}