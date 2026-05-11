package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");
        String f3 = req.getParameter("f3");
        String f4 = req.getParameter("f4");

        List<String> errors = new ArrayList<>();

        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();
        StudentDao studentDao = new StudentDao();

        // プルダウン
        List<String> entYearSet = studentDao.getEntYearSet();
        List<String> classNumSet = studentDao.getClassNumSet();
        List<Subject> subjects = subjectDao.findAll(teacher.getSchool().getSchoolCd());
        List<Integer> numSet = testDao.getNumSet();

        List<Test> list = new ArrayList<>();

        if (req.getParameter("search-btn") != null) {

            if ((f1 == null || f1.equals("0")) &&
                (f2 == null || f2.equals("0")) &&
                (f3 == null || f3.equals("0")) &&
                (f4 == null || f4.equals("0"))) {

                errors.add("1つ以上条件を選択してください");

            } else {

            	Integer no = (f4 == null || f4.equals("0")) ? null : Integer.parseInt(f4);

                list = testDao.filter(
                        f1,
                        f2,
                        f3,
                        no,
                        null,
                        teacher.getSchool().getSchoolCd()
                );

                if (list.isEmpty()) {
                    errors.add("成績情報が存在しませんでした。");
                }
            }
        }

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subjects", subjects);
        req.setAttribute("num_set", numSet);

        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        req.setAttribute("test_list", list);
        req.setAttribute("errors", errors);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}