package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    // --- 既存の filter メソッド (内容を保持) ---
    public List<Test> filter(String entYear, String classNum, String subjectCd, Integer no, String studentNo, String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT T.*, S.CLASS_NUM ");
        sql.append("FROM TEST T ");
        sql.append("JOIN STUDENT S ON T.STUDENT_NO = S.STUDENT_NO ");
        sql.append("WHERE T.SCHOOL_CD=?");

        List<Object> params = new ArrayList<>();
        params.add(schoolCd);

        if (studentNo != null && !studentNo.isEmpty()) {
            sql.append(" AND T.STUDENT_NO=?");
            params.add(studentNo);
        }
        if (subjectCd != null && !subjectCd.equals("0")) {
            sql.append(" AND T.SUBJECT_CD=?");
            params.add(subjectCd);
        }
        if (classNum != null && !classNum.equals("0")) {
            sql.append(" AND S.CLASS_NUM=?");
            params.add(classNum);
        }
        if (entYear != null && !entYear.equals("0")) {
            sql.append(" AND S.ENT_YEAR=?");
            params.add(entYear);
        }
        if (no != null && no != 0) {
            sql.append(" AND T.NO=?");
            params.add(no);
        }

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test t = new Test();
                    t.setStudentNo(rs.getString("STUDENT_NO"));
                    t.setSchoolCd(rs.getString("SCHOOL_CD"));
                    t.setSubjectCd(rs.getString("SUBJECT_CD"));
                    t.setNo(rs.getInt("NO"));
                    t.setPoint(rs.getInt("POINT"));
                    t.setClassNum(rs.getString("CLASS_NUM"));
                    list.add(t);
                }
            }
        }
        return list;
    }


    public List<Integer> getNumSet() throws Exception {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT NO FROM TEST ORDER BY NO";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt("NO"));
            }
        }
        return list;
    }

    // ★追加: 一括保存用 save メソッド (MERGE文を使用)
    public boolean save(List<Test> tests) throws Exception {
        // 主キー（学生番号、科目、学校、回数）が一致すればUPDATE、なければINSERT
        String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) " +
                     "KEY (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO) VALUES (?, ?, ?, ?, ?, ?)";
        int count = 0;

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(sql)) {
            for (Test t : tests) {
                st.setString(1, t.getStudentNo());
                st.setString(2, t.getSubjectCd());
                st.setString(3, t.getSchoolCd());
                st.setInt(4, t.getNo());
                st.setInt(5, t.getPoint());
                st.setString(6, t.getClassNum());
                count += st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return count > 0;
    }

    // ★追加: 特定の成績を1件取得する get メソッド
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
        }
        return test;
    }
}