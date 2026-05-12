package scoremanager.main;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getSchoolCd();

        // 削除に必要なキーを取得
        String studentNo = req.getParameter("student_no");
        String subjectCd = req.getParameter("subject_cd");

        int no = Integer.parseInt(req.getParameter("num"));

        // 削除実行
        Test test = new Test();
        test.setStudentNo(studentNo);
        test.setSubjectCd(subjectCd);
        test.setNo(no);
        test.setSchoolCd(schoolCd);

        TestDao dao = new TestDao();
        dao.delete(test); // 下記のDAOメソッドを呼び出し

        // 元の検索パラメータを維持してリダイレクト
        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");
        String f3 = req.getParameter("f3");
        String f4 = req.getParameter("f4");
        
        res.sendRedirect("test_delete_done.jsp");
    }
}
	