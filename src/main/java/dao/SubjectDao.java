package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Test;

public class TestDao extends Dao {

    public Test get(String studentNo, String subjectCd, String schoolCd) throws Exception {

        Test test = null;

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, studentNo);
            statement.setString(2, subjectCd);
            statement.setString(3, schoolCd);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                test = new Test();
                test.setStudentNo(rs.getString("student_no"));
                test.setSubjectCd(rs.getString("subject_cd"));
                test.setSchoolCd(rs.getString("school_cd"));
                test.setNo(rs.getInt("no"));
                test.setPoint(rs.getInt("point"));
                test.setClassNum(rs.getString("class_num"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return test;
    }

  
    public boolean save(Test test) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Test old = get(test.getStudentNo(), test.getSubjectCd(), test.getSchoolCd());

            if (old == null) {
                // INSERT
                String sql = "INSERT INTO test(student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);

                statement.setString(1, test.getStudentNo());
                statement.setString(2, test.getSubjectCd());
                statement.setString(3, test.getSchoolCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());

            } else {
                // UPDATE
                String sql = "UPDATE test SET no = ?, point = ?, class_num = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ?";
                statement = connection.prepareStatement(sql);

                statement.setInt(1, test.getNo());
                statement.setInt(2, test.getPoint());
                statement.setString(3, test.getClassNum());
                statement.setString(4, test.getStudentNo());
                statement.setString(5, test.getSubjectCd());
                statement.setString(6, test.getSchoolCd());
            }

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    public boolean delete(Test test) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchoolCd());

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
}