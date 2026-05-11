package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 成績登録画面の表示と検索処理を行うアクションクラス
 */
public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // ログインチェック
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        
        String schoolCd = teacher.getSchool().getSchoolCd();

        // --- 1. 選択肢データの準備 ---
        // 入学年度リスト (2016年〜現在+1年程度)
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = 2016; i <= 2026; i++) {
            entYearList.add(i);
        }
        
        // クラス一覧の取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        // 科目一覧の取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.findAll(schoolCd);

        // 回数リスト (1回目, 2回目)
        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);

        // --- 2. 検索処理 ---
        // JSPの検索フォームからのパラメータ取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // 検索ボタンが押され、かつ入学年度が選択されている場合
        if (entYearStr != null && !entYearStr.equals("0")) {
            TestDao tDao = new TestDao();
            
            // 回数のパース（未選択時は0）
            int no = (numStr != null && !numStr.equals("0")) ? Integer.parseInt(numStr) : 0;
            
            // ★修正ポイント：Daoの引数6個に合わせて呼び出し
            // 引数順: 入学年度, クラス, 科目, 回数(Integer), 学生番号(null), 学校コード
            List<Test> students = tDao.filter(entYearStr, classNum, subjectCd, no, null, schoolCd);
            
            // 検索結果と検索条件をリクエストにセット（JSPでの表示・保持用）
            req.setAttribute("students", students);
            req.setAttribute("subject_cd", subjectCd);
            req.setAttribute("num", no);
            
            // 選択状態の保持用
            req.setAttribute("f1", entYearStr); 
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("f4", numStr);
            
            // 画面表示用の科目名を取得
            Subject subject = sDao.get(subjectCd, schoolCd);
            if (subject != null) {
                req.setAttribute("subject_name", subject.getSubjectName());
            }
        }

        // --- 3. 画面表示用データのセット ---
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subjects", subjectList);
        req.setAttribute("num_set", numList);

        // JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}