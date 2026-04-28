package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // DAOのインスタンス化
        TestDao tDao = new TestDao();
        SubjectDao sDao = new SubjectDao(); // 選択肢取得用
        Map<String, String> errors = new HashMap<>();

        // リクエストパラメータの取得
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String studentNo = req.getParameter("student_no"); // 学生番号

        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // バリデーション
        if (subjectCd == null || subjectCd.equals("0")) {
            errors.put("f1", "科目を選択してください");
        } else {
            // 【重要】TestDao.filter(studentNo, schoolCd, subjectCd, no) の順序で呼び出し
            // 「no」には入学年度(entYear)を割り当てる等、既存DAOの型に合わせます
            List<Test> tests = tDao.filter(
                studentNo != null ? studentNo : "", // 第1: studentNo
                teacher.getSchool().getSchoolCd(),   // 第2: schoolCd
                subjectCd,                           // 第3: subjectCd
                entYear                              // 第4: no (int)
            );
            req.setAttribute("tests", tests);
        }

        // リクエスト属性のセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("errors", errors);

        // JSPへフォワード
        req.getRequestDispatcher("subject_test_list.jsp").forward(req, res);
    }
}