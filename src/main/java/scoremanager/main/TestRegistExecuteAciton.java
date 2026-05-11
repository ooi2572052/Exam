package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAciton extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        
        String subjectCd = req.getParameter("subject_cd");
        int num = Integer.parseInt(req.getParameter("num"));
        String[] studentNos = req.getParameterValues("student_nos");
        String[] pointsStr = req.getParameterValues("points");
        String[] classNums = req.getParameterValues("class_nums"); // ★JSPから取得

        List<Test> testList = new ArrayList<>();
        for (int i = 0; i < studentNos.length; i++) {
            Test t = new Test();
            t.setStudentNo(studentNos[i]);
            t.setSubjectCd(subjectCd);
            t.setSchoolCd(teacher.getSchool().getSchoolCd());
            t.setNo(num);
            t.setClassNum(classNums[i]); // ★セット
            
            // 入力チェック（未入力は0点とする等の処理）
            int point = (pointsStr[i] == null || pointsStr[i].isEmpty()) ? 0 : Integer.parseInt(pointsStr[i]);
            t.setPoint(point);
            testList.add(t);
        }

        TestDao dao = new TestDao();
        dao.save(testList);

        // 完了画面、または一覧画面へフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}