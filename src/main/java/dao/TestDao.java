package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    /**
     * 【修正】5つの引数を受け取る filter メソッド
     * Actionでの呼び出し: filter(entYear, classNum, subjectCd, studentNo, schoolCd) に対応
     */
    public List<Test> filter(String entYear, String classNum, String subjectCd, String studentNo, String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        // 学生情報(STUDENT)と成績(TEST)を結合して取得
        sql.append("SELECT s.ENT_YEAR, s.CLASS_NUM, s.STUDENT_NO, s.STUDENT_NAME, t.POINT ");
        sql.append("FROM STUDENT s LEFT JOIN TEST t ON s.STUDENT_NO = t.STUDENT_NO ");
        sql.append("AND t.SUBJECT_CD = ? AND t.SCHOOL_CD = ? ");
        sql.append("WHERE s.SCHOOL_CD = ? ");

        List<Object> params = new ArrayList<>();
        params.add(subjectCd);
        params.add(schoolCd);
        params.add(schoolCd);

        if (entYear != null && !entYear.equals("0")) {
            sql.append(" AND s.ENT_YEAR = ?");
            params.add(entYear);
        }
        if (classNum != null && !classNum.equals("0")) {
            sql.append(" AND s.CLASS_NUM = ?");
            params.add(classNum);
        }
        if (studentNo != null && !studentNo.isEmpty()) {
            sql.append(" AND s.STUDENT_NO = ?");
            params.add(studentNo);
        }

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setEntYear(rs.getInt("ENT_YEAR"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setStudentName(rs.getString("STUDENT_NAME"));
                    test.setPoint(rs.getInt("POINT"));
                    list.add(test);
                }
            }
        }
        return list;
    }

    /**
     * 【修正】3つの引数を受け取る get メソッド
     * Actionでの呼び出し: get(studentNo, subjectCd, schoolCd) に対応
     */
    public Test get(String studentNo, String subjectCd, String schoolCd) throws Exception {
        Test test = null;
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setPoint(rs.getInt("POINT"));
                    test.setNo(rs.getInt("NO"));
                }
            }
        }
        return test;
    }

    /**
     * 一括保存メソッド（前回提示したものと同じ）
     */
    public boolean save(List<Test> tests) throws Exception {
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
        }
        return count > 0;
    }
}