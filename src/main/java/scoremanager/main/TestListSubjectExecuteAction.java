package scoremanager.main;
 
import java.util.List;
 
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからログインユーザー（先生）情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
 
        // 検索パラメータの取得
        int entYear = Integer.parseInt(request.getParameter("f1")); // 入学年度
        String classNum = request.getParameter("f2");               // クラス
        String subjectCd = request.getParameter("f3");              // 科目
        int num = Integer.parseInt(request.getParameter("f4"));     // 回数
 
        // DAOの初期化
        TestDao dao = new TestDao();
        
        // 検索実行（引数は順に：入学年度、クラス、科目、回数、学校コード）
        // ※ TestDaoのfilterメソッドの定義に合わせて調整してください
        List<Test> list = dao.filter(entYear, classNum, subjectCd, num, teacher.getSchool().getSchoolCd());
 
        // JSPで表示するためにリクエスト属性にセット
        request.setAttribute("test_list", list);
        
        // 検索条件を保持（JSPのプルダウンの選択状態を維持するため）
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", num);
 
        // 結果が空の場合のエラーメッセージ
        if (list == null || list.isEmpty()) {
            request.setAttribute("error", "成績情報が存在しません。");//aa
        }
 
        // 共通の test_list.jsp へ遷移
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
 