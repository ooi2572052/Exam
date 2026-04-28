package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {
    public List<Test> filter(String studentNo, String schoolCd, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();

        String sql = "SELECT * FROM TEST WHERE STUDENT_NO=? AND SCHOOL_CD=? AND SUBJECT_CD=? AND NO=?";
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, studentNo);
            st.setString(2, schoolCd);
            st.setString(3, subjectCd);
            st.setInt(4, no);
            
            ResultSet rs = st.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }
        return list;
    }

    public boolean save(Test test) throws Exception {
        Connection con = getConnection();
        String sql = "INSERT INTO TEST (STUDENT_NO, SCHOOL_CD, SUBJECT_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        
        int row = 0;
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, test.getStudentNo());
            st.setString(2, test.getSchoolCd());
            st.setString(3, test.getSubjectCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());
            
            row = st.executeUpdate();
        } finally {
            con.close();
        }
        return row > 0;
    }
}