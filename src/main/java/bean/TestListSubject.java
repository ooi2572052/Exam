package bean;

/**
 * クラス別成績一覧表示用Bean
 */
public class TestListSubject {

	
    /** 入学年度 */
    private int entYear;

    /** クラス番号 */
    private String classNum;

    /** 学籍番号 */
    private String studentNo;

    /** 学生氏名 */
    private String studentName;

    /** 科目コード */
    private String subjectCd;

    /** 科目名 */
    private String subjectName;

    /** 回数 */
    private int num;

    /** 点数 */
    private int point;

    // getter setter

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}