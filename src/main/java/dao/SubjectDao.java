package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 科目一覧取得（プルダウン用）
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

        } finally {
            con.close();
        }

        return list;
    }
}