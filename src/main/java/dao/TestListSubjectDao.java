package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql =

        "SELECT " +

        "st.ENT_YEAR, " +
        "st.CLASS_NUM, " +

        "st.STUDENT_NO, " +
        "st.STUDENT_NAME, " +

        "sub.SUBJECT_CD, " +
        "sub.SUBJECT_NAME, " +

        "t.NO AS TEST_NO, " +
        "t.POINT " +

        "FROM TEST t " +

        "JOIN STUDENT st " +
        "ON t.STUDENT_NO = st.STUDENT_NO " +

        "JOIN SUBJECT sub " +
        "ON t.SUBJECT_CD = sub.SUBJECT_CD ";

    public List<TestListSubject> filter(
            int entYear,
            String classNum,
            String subjectCd,
            int num)
            throws Exception {

        List<TestListSubject> list =
            new ArrayList<>();

        Connection con =
            getConnection();

        String sql =
            baseSql +

            "WHERE st.ENT_YEAR = ? " +
            "AND st.CLASS_NUM = ? " +
            "AND sub.SUBJECT_CD = ? " +
            "AND t.NO = ? " +

            "ORDER BY st.STUDENT_NO";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, subjectCd);
        st.setInt(4, num);

        ResultSet rs =
            st.executeQuery();

        while (rs.next()) {

            TestListSubject bean =
                new TestListSubject();

            bean.setEntYear(
                rs.getInt("ENT_YEAR"));

            bean.setClassNum(
                rs.getString("CLASS_NUM"));

            bean.setStudentNo(
                rs.getString("STUDENT_NO"));

            bean.setStudentName(
                rs.getString("STUDENT_NAME"));

            bean.setSubjectCd(
                rs.getString("SUBJECT_CD"));

            bean.setSubjectName(
                rs.getString("SUBJECT_NAME"));

            bean.setNum(
                rs.getInt("TEST_NO"));

            bean.setPoint(
                rs.getInt("POINT"));

            list.add(bean);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}