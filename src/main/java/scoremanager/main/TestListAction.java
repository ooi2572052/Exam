package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログインユーザー
    	Teacher teacher = (Teacher) req.getSession().getAttribute("user");

    	if (teacher == null || teacher.getSchool() == null) {
    	    res.sendRedirect("login.jsp");
    	    return;
    	}

    	String schoolCd = teacher.getSchool().getSchoolCd();
        // パラメータ取得
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String studentNo = req.getParameter("studentNo");

        // 科目プルダウン用
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.findAll(schoolCd);
        req.setAttribute("subjectList", subjectList);

        // 検索処理
        TestDao testDao = new TestDao();
        List<Test> list = testDao.filter(
        	    entYear,
        	    classNum,
        	    subjectCd,
        	    studentNo,
        	    schoolCd
        	);
        req.setAttribute("list", list);

        // 画面へ
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}