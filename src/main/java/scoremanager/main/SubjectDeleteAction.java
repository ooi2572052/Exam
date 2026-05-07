package scoremanager.main;

import bean.Subject; // Test から Subject に変更
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. セッションからログインユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 2. リクエストパラメータから削除対象の科目コード（cd）を取得
        String cd = req.getParameter("cd");

        // 3. SubjectDaoをインスタンス化
        SubjectDao sDao = new SubjectDao();

        // 4. get メソッドを呼び出す
        // teacher.getSchool() ではなく .getSchoolCd() を使って文字列を渡します
        Subject subject = sDao.get(cd, teacher.getSchool().getSchoolCd());

        // 5. 取得したデータをリクエスト属性にセットしてJSPへ渡す
        req.setAttribute("subject", subject);

        // 6. 削除確認画面（subject_delete.jsp）へフォワード
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}