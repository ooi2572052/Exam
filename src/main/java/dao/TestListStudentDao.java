package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {
 
    private String baseSql =

        "SELECT " +

        "st.STUDENT_NO AS STUDENT_NO, " +
        "st.STUDENT_NAME AS STUDENT_NAME, " +

        "sub.SUBJECT_NAME AS SUBJECT_NAME, " +

        "t.NO AS TEST_NO, " +
        "t.POINT " +

        "FROM TEST t " +

        "JOIN STUDENT st " +
        "ON t.STUDENT_NO = st.STUDENT_NO " +

        "JOIN SUBJECT sub " +
        "ON t.SUBJECT_CD = sub.SUBJECT_CD ";

    public List<TestListStudent> filter(
            String studentNo)
            throws Exception {

        List<TestListStudent> list =
            new ArrayList<>();

        Connection con =
            getConnection();

        String sql =
            baseSql +
            "WHERE st.STUDENT_NO = ? " +
            "ORDER BY t.NO";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1, studentNo);

        ResultSet rs =
            st.executeQuery();

        while (rs.next()) {

            TestListStudent bean =
                new TestListStudent();

            bean.setStudentNo(
                rs.getString("STUDENT_NO"));

            bean.setStudentName(
                rs.getString("STUDENT_NAME"));

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