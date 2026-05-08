package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
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

        // 1. 入学年度のリストを作成（現在の年から過去10年分など）
        List<Integer> entYearList = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        // 2. クラス一覧の取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        // 3. 科目一覧の取得
        SubjectDao sDao = new SubjectDao();
        List<bean.Subject> subjectList = sDao.findAll(schoolCd);

        // 4. 回数リスト（1回、2回など固定）
        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);

        // JSPへ渡すデータをセット
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("subjects", subjectList);
        req.setAttribute("num_set", numList);
        
        req.setAttribute("title", "成績管理");

        // チームの共通ルール（base.jsp経由）でフォワード
        req.getRequestDispatcher("base.jsp?content=/scoremanager/main/test_regist.jsp")
           .forward(req, res);
    }
}