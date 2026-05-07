package bean;

import java.io.Serializable;


public class Test implements Serializable {
    private static final long serialVersionUID = 1L;

    // フィールド
    private String studentNo; //  学生番号
    private String schoolCd;  //  学校コード
    private String subjectCd; //  科目コード
    private int no;           //  回数
    private int point;        //  得点（定義書の修正案に基づきint型）
    private String classNum;  //  クラス番号

    // デフォルトコンストラクタ
    public Test() {}

    // ゲッター・セッター
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}