package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String schoolCd = teacher.getSchool().getSchoolCd();

        // ▼ プルダウン
        List<Integer> entYearList = new ArrayList<>();
        for (int i = 2016; i <= LocalDate.now().getYear() + 1; i++) {
            entYearList.add(i);
        }

        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.findAll(schoolCd);

        List<Integer> numList = List.of(1, 2);

        // ▼ パラメータ
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // ★修正：フォワード元(TestRegistExecuteAction)のerrorsを引き継ぐ
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) req.getAttribute("errors");
        if (errors == null) {
            errors = new ArrayList<>();
        }

        // ▼ 未入力チェック（フォワード元からのerrorsがない場合のみ）
        if (errors.isEmpty() && req.getParameter("f1") != null) {
            if ("0".equals(entYearStr) || "0".equals(classNum)
                    || "0".equals(subjectCd) || "0".equals(numStr)) {
                errors.add("入学年度・クラス・科目・回数を選択してください");
            }
        }

        // ▼ 検索処理
        if (errors.isEmpty() && entYearStr != null && !"0".equals(entYearStr)) {

            int entYear = Integer.parseInt(entYearStr);
            int no = Integer.parseInt(numStr);

            StudentDao studentDao = new StudentDao();
            TestDao testDao = new TestDao();

            List<Student> studentList = studentDao.filter(
                    teacher.getSchool(),
                    entYear,
                    classNum,
                    true
            );
            if (studentList.isEmpty()) {
                errors.add("該当する学生が存在しません");
            }

            List<Test> list = new ArrayList<>();

            for (Student s : studentList) {

                Test t = testDao.get(s.getStudentNo(), subjectCd, schoolCd, no);

                if (t == null) {
                    t = new Test();
                    t.setStudentNo(s.getStudentNo());
                    t.setPoint(-1);
                }

                t.setClassNum(s.getClassNum());
                list.add(t);

                req.setAttribute("name_" + s.getStudentNo(), s.getStudentName());
                req.setAttribute("year_" + s.getStudentNo(), s.getEntYear());
            }

            req.setAttribute("students", list);
            req.setAttribute("subject_cd", subjectCd);
            req.setAttribute("num", no);

            Subject subject = sDao.get(subjectCd, schoolCd);
            if (subject != null) {
                req.setAttribute("subject_name", subject.getSubjectName());
            }

            // 選択保持
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("f4", numStr);
        }

        // ▼ 共通
        req.setAttribute("errors", errors);
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subjects", subjectList);
        req.setAttribute("num_set", numList);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}