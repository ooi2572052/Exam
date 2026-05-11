package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {


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

    // 回数プルダウン用
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
}