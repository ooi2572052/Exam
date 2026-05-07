package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 科目一覧取得
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

        con.close();

        return list;
    }

    // 科目1件取得
    public Subject get(String subjectCd, String schoolCd) throws Exception {

        Subject subject = null;

        Connection con = getConnection();

        String sql =
            "SELECT * FROM SUBJECT "
          + "WHERE SUBJECT_CD=? AND SCHOOL_CD=?";

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

        con.close();

        return subject;
    }

    // 科目更新
    public boolean update(Subject subject) throws Exception {

        Connection con = getConnection();

        String sql =
            "UPDATE SUBJECT "
          + "SET SUBJECT_NAME=? "
          + "WHERE SUBJECT_CD=? AND SCHOOL_CD=?";

        int count = 0;

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getSubjectName());
            st.setString(2, subject.getSubjectCd());
            st.setString(3, subject.getSchoolCd());

            count = st.executeUpdate();
        }

        con.close();

        return count > 0;
    }
}