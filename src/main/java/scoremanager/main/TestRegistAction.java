package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
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
        String schoolCd = teacher.getSchool().getSchoolCd();

        // 1-4. 選択肢データの準備（既存のロジック）
        List<Integer> entYearList = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = 2016; i <= 2026; i++) entYearList.add(i);
        
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        List<bean.Subject> subjectList = sDao.findAll(schoolCd);

        List<Integer> numList = new ArrayList<>();
        numList.add(1); numList.add(2);

        // --- 検索処理 ---
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // 検索ボタンが押され、かつ入学年度が選択されている場合
        if (entYearStr != null && !entYearStr.equals("0")) {
            TestDao tDao = new TestDao();
            // TestDao.filter を呼び出して学生と点数のリストを取得
            List<Test> students = tDao.filter(entYearStr, classNum, subjectCd, null, schoolCd);
            
            req.setAttribute("students", students);
            req.setAttribute("subject_cd", subjectCd);
            req.setAttribute("num", numStr);
            req.setAttribute("f1", entYearStr); // 選択状態保持用
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("f4", numStr);
            
            // 科目名表示用
            req.setAttribute("subject_name", sDao.get(subjectCd, schoolCd).getSubjectName());
        }

        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subjects", subjectList);
        req.setAttribute("num_set", numList);
        req.setAttribute("title", "成績管理");

        req.getRequestDispatcher("base.jsp?content=/scoremanager/main/test_regist.jsp").forward(req, res);
    }
}