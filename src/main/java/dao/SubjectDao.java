package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

//

public class SubjectDao extends Dao {

    // 科目一覧を取得（SubjectListActionで使用）
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("SELECT * FROM subject WHERE school_cd = ?");
            st.setString(1, school.getSchoolCd());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectCd(rs.getString("cd"));
                s.setSubjectName(rs.getString("name"));
                s.setSchoolCd(rs.getString("school_cd"));
                list.add(s);
            }
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }

    // 入学年度の選択肢リストを取得（検索画面用）
    public List<Integer> getEntYearSet() {
        List<Integer> list = new ArrayList<>();
        for (int i = 2014; i <= 2034; i++) {
            list.add(i);
        }
        return list;
    }

    // クラス番号の選択肢リストを取得（検索画面用）
    public List<String> getClassNumSet(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            // 学生テーブル等から重複なくクラス名を取得する例
            st = con.prepareStatement("SELECT DISTINCT class_num FROM student WHERE school_cd = ?");
            st.setString(1, school.getSchoolCd());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("class_num"));
            }
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }
}