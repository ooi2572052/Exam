package bean;

/**
 * 学生一覧表示用Bean
 */

public class TestListStudent {
	
	// 科目名
	private String subjectName;

	// 回数
	private int num;

	// 点数
	private int point;

    /** 入学年度 */
    private int entYear;

    /** 学籍番号 */
    private String studentNo;

    /** 氏名 */
    private String studentName;

    /** クラス番号 */
    private String classNum;

    /** 在籍状態 */
    private boolean attend;

    /** 学校 */
    private School school;

    // --- getter / setter ---
    
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

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
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

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}