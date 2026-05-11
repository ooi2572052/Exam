package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction
	
extends Action {
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        // 文字コード
        request.setCharacterEncoding("UTF-8");

        // 学籍番号取得
        String studentNo =
            request.getParameter("studentNo");

        // DAO
        TestListStudentDao dao =
            new TestListStudentDao();

        // 検索実行
        List<TestListStudent> list =
            dao.filter(studentNo);

        // 検索結果格納
        request.setAttribute(
            "studentList",
            list);

        // 学生情報表示用
        if (!list.isEmpty()) {

            Student student =
                new Student();

            student.setStudentNo(
                list.get(0).getStudentNo());

            student.setStudentName(
                list.get(0).getStudentName());

            request.setAttribute(
                "student",
                student);
        }

        // 件数0
        if (list.isEmpty()) {

            request.setAttribute(
                "error",
                "該当する成績情報が存在しません。");
        }

        // JSPへ遷移
        request.getRequestDispatcher(
            "test_list_student.jsp")
            .forward(request, response);
    }
}