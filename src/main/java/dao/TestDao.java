package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    /**
     * 条件に合致する成績一覧を取得する
     */
    public List<Test> filter(String entYear, String classNum, String subjectCd, String studentNo, String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TEST WHERE SCHOOL_CD=?");

        List<Object> params = new ArrayList<>();
        params.add(schoolCd);

        // 学生番号の絞り込み
        if (studentNo != null && !studentNo.isEmpty()) {
            sql.append(" AND STUDENT_NO=?");
            params.add(studentNo);
        }

        // 科目の絞り込み
        if (subjectCd != null && !subjectCd.isEmpty()) {
            sql.append(" AND SUBJECT_CD=?");
            params.add(subjectCd);
        }

        // クラスの絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            sql.append(" AND CLASS_NUM=?");
            params.add(classNum);
        }

        // 入学年度（STUDENTテーブルをサブクエリで参照）
        if (entYear != null && !entYear.isEmpty()) {
            sql.append(" AND STUDENT_NO IN (");
            sql.append(" SELECT STUDENT_NO FROM STUDENT WHERE ENT_YEAR=?");
            sql.append(")");
            params.add(entYear);
        }

        // Connectionをtryのカッコ内に入れることで自動クローズを確実にする
        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(sql.toString())) {

            // パラメータのセット
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                    list.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }

    /**
     * 成績情報を登録する
     */
    public boolean save(Test test) throws Exception {
        String sql = "INSERT INTO TEST (STUDENT_NO, SCHOOL_CD, SUBJECT_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        int row = 0;

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, test.getStudentNo());
            st.setString(2, test.getSchoolCd());
            st.setString(3, test.getSubjectCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());

            row = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return row > 0;
    }

    /**
     * 特定の成績情報を1件取得する
     */
    public Test get(String studentNo, String subjectCd, String schoolCd) throws Exception {
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=?";
        Test test = null;

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, schoolCd);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return test;
    }
}