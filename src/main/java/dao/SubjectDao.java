package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Subject;

//


public class SubjectDao extends Dao {

    public boolean update(Subject subject) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            String sql = "UPDATE subject SET subject_name = ? WHERE subject_cd = ? AND school_cd = ?";
            st = con.prepareStatement(sql);

            st.setString(1, subject.getSubjectName());
            st.setString(2, subject.getSubjectCd());
            st.setString(3, subject.getSchoolCd());

            count = st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return count > 0;
    }
}