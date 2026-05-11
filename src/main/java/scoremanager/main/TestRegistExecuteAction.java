package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        String[] studentNos = req.getParameterValues("student_nos");
        String[] points = req.getParameterValues("points");
        String[] classNums = req.getParameterValues("class_nums");

        String subjectCd = req.getParameter("subject_cd");
        int no = Integer.parseInt(req.getParameter("num"));
        String schoolCd = teacher.getSchool().getSchoolCd();

        List<Test> testList = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < studentNos.length; i++) {

            if (points[i] == null || points[i].isEmpty()) {
                continue;
            }

            int point;

            try {
                point = Integer.parseInt(points[i]);
            } catch (Exception e) {
                errors.add(studentNos[i]);
                continue;
            }

            if (point < 0 || point > 100) {
                errors.add(studentNos[i]);
                continue;
            }

            Test t = new Test();
            t.setStudentNo(studentNos[i]);
            t.setSubjectCd(subjectCd);
            t.setSchoolCd(schoolCd);
            t.setNo(no);
            t.setPoint(point);
            t.setClassNum(classNums[i]);

            testList.add(t);
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("TestRegist.action").forward(req, res);
            return;
        }

        TestDao dao = new TestDao();
        dao.save(testList);

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);;
    }
}