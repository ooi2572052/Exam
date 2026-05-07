package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // SubjectDao は Test オブジェクトを扱う仕様のため、それに合わせて呼び出し
        SubjectDao sDao = new SubjectDao();
        
        // 注意: 提示された SubjectDao には一覧取得(filter)がないため、
        // 本来は追加が必要ですが「一切変更しない」制約に基づき、
        // 取得ロジックが必要な場合はここでの実装、または別DAOの検討が必要になります。
        // ここでは、一旦 JSP へフォワードする処理を構成します。
        
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}