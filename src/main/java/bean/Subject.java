package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String subjectCd;     // 科目コード
    private String subjectName;   // 科目名
    private String schoolCd;      // 学校コード

    // getter / setter

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
}