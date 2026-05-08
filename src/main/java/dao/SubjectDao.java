package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 1. 一覧取得
    public List<Subject> findAll(String schoolCd) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT * FROM SUBJECT WHERE SCHOOL_CD=?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, schoolCd);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectCd(rs.getString("SUBJECT_CD"));
                s.setSubjectName(rs.getString("SUBJECT_NAME"));
                s.setSchoolCd(rs.getString("SCHOOL_CD"));
                list.add(s);
            }
        }
        return list;
    }

    // 2. 1件取得
    public Subject get(String subjectCd, String schoolCd) throws Exception {
        Subject subject = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM SUBJECT WHERE SUBJECT_CD=? AND SCHOOL_CD=?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subjectCd);
            st.setString(2, schoolCd);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setSubjectCd(rs.getString("SUBJECT_CD"));
                subject.setSubjectName(rs.getString("SUBJECT_NAME"));
                subject.setSchoolCd(rs.getString("SCHOOL_CD"));
            }
        }
        return subject;
    }

    // 3. 保存（新規登録）
    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        String sql = "INSERT INTO SUBJECT (SUBJECT_CD, SUBJECT_NAME, SCHOOL_CD) VALUES (?, ?, ?)";
        int count = 0;
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subject.getSubjectCd());
            st.setString(2, subject.getSubjectName());
            st.setString(3, subject.getSchoolCd());
            count = st.executeUpdate();
        }
        return count > 0;
    }

    // 4. 更新（ここが重複していた箇所です。1つだけ残します）
    public boolean update(Subject subject) throws Exception {
        Connection con = getConnection();
        String sql = "UPDATE SUBJECT SET SUBJECT_NAME=? WHERE SUBJECT_CD=? AND SCHOOL_CD=?";
        int count = 0;
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subject.getSubjectName());
            st.setString(2, subject.getSubjectCd());
            st.setString(3, subject.getSchoolCd());
            count = st.executeUpdate();
        }
        return count > 0;
    }

    // 5. 削除
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        String sql = "DELETE FROM SUBJECT WHERE SUBJECT_CD=? AND SCHOOL_CD=?";
        int count = 0;
        try (PreparedStatement st = con.prepareStatement("DELETE FROM SUBJECT WHERE SUBJECT_CD=? AND SCHOOL_CD=?")) {
            st.setString(1, subject.getSubjectCd());
            st.setString(2, subject.getSchoolCd());
            count = st.executeUpdate();
        }
        return count > 0;
    }
}