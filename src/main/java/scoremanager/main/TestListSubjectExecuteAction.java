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
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2");   // クナス
        String subjectCd = request.getParameter("f3");  // 科目
        String noStr = request.getParameter("f4");      // 回数
 
        // 回数(no)を String から Integer に変換（未選択時は null または 0 にする）
        Integer no = null;
        if (noStr != null && !noStr.isEmpty()) {
            try {
                no = Integer.parseInt(noStr);
            } catch (NumberFormatException e) {
                no = 0; // DAO側で「no != 0」の判定があるため0でも除外される
            }
        }

        // DAOの初期化
        TestDao dao = new TestDao();
        
        // 修正点: DAOの引数の順序・型に完全に合わせる
        // 引数: (entYear, classNum, subjectCd, no, studentNo, schoolCd)
        List<Test> list = dao.filter(entYearStr, classNum, subjectCd, no, null,teacher.getSchool().getSchoolCd()
        );
 
        // JSPで表示するためにリクエスト属性にセット
        request.setAttribute("test_list", list);
        
        // 検索条件を保持（JSPのプルダウンの選択状態を維持するため）
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", noStr);
 
        // 結果が空の場合のエラーメッセージ
        if (list == null || list.isEmpty()) {
            request.setAttribute("error", "成績情報が存在しません。");
        }
 
        // 共通の test_list.jsp へ遷移
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}