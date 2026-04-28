package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
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
        // 1. セッションとDAOの準備
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        TestDao tDao = new TestDao();
        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        // 2. リクエストパラメータの取得
        // 入学年度、クラス、科目の値を受け取る
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");

        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 3. バリデーション（設計書通りのチェック）
        // どれか1つでも未選択（0またはnull）がある場合
        if (entYear == 0 || classNum == null || classNum.equals("0") || subjectCd == null || subjectCd.equals("0")) {
            errors.put("f1", "入学年度とクラスと科目を選択してください");
        } else {
            // 全て選択されている場合、検索実行
            List<Test> tests = tDao.filter(entYear, classNum, subjectCd, teacher.getSchool());
            // 検索結果をリクエストにセット（0件でもリストを渡す）
            req.setAttribute("tests", tests);
        }

        // 4. 画面表示に必要な選択肢の再取得
        // 画面に戻った時にセレクトボックスが空にならないようにする
        List<Integer> entYearSet = sDao.getEntYearSet(); // 入学年度リスト（DAOに実装が必要）
        List<String> classNumSet = sDao.getClassNumSet(teacher.getSchool()); // クラスリスト
        List<Subject> subjectSet = sDao.filter(teacher.getSchool()); // 科目リスト

        // 5. リクエスト属性のセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_set", subjectSet);
        req.setAttribute("errors", errors);

        // 6. JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}