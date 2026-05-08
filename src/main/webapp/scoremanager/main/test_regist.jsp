<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<section class="me-4">
    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

    <%-- 検索条件指定エリア --%>
    <form action="TestRegist.action" method="get">
        <div class="row border-bottom pb-4 mb-4">
            <%-- （前回作成した入学年度、クラス、科目、回数のセレクトボックスをここに配置） --%>
            <%-- 例：科目 --%>
            <div class="col-4">
                <label class="form-label">科目</label>
                <select name="f3" class="form-select">
                    <option value="0">--------</option>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.subjectCd}" <c:if test="${subject.subjectCd == subject_cd}">selected</c:if>>
                            ${subject.subjectName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <%-- （中略） --%>
            <div class="col-2 d-flex align-items-end">
                <button type="submit" class="btn btn-secondary">検索</button>
            </div>
        </div>
    </form>

    <%-- ★ここから：検索結果（成績入力）エリア★ --%>
    <%-- 検索が行われ、学生リストが存在する場合のみ表示 --%>
    <c:if test="${not empty students}">
        
        <%-- ① 科目・回数情報 --%>
        <div class="mb-3 fs-5 fw-bold text-secondary">
            科目：${subject_name}（${num}回）
        </div>

        <%-- 成績登録用のフォーム --%>
        <form action="TestRegistExecute.action" method="post">
            <%-- 登録時に必要な隠しパラメータ --%>
            <input type="hidden" name="subject_cd" value="${subject_cd}">
            <input type="hidden" name="num" value="${num}">

            <table class="table table-hover mt-3">
                <thead>
                    <tr class="text-secondary">
                        <th class="col-2">入学年度</th>
                        <th class="col-1">クラス</th>
                        <th class="col-2">学生番号</th>
                        <th class="col-3">氏名</th>
                        <th class="col-4">点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}" varStatus="status">
                        <tr>
                            <%-- ⑧ 入学年度 --%>
                            <td>${student.entYear}</td>
                            <%-- ⑨ クラス --%>
                            <td>${student.classNum}</td>
                            <%-- ⑩ 学生番号 --%>
                            <td>${student.studentNo}</td>
                            <%-- ⑪ 氏名 --%>
                            <td>${student.studentName}</td>
                            <%-- ⑫ 点数入力欄 --%>
                            <td>
                                <%-- 登録時にリストとして受け取るため name を配列形式にする --%>
                                <input type="number" name="points" value="${student.point}" class="form-control" style="max-width: 150px;">
                                <%-- 学生番号も隠しパラメータでペアにして送る --%>
                                <input type="hidden" name="student_nos" value="${student.studentNo}">
                                
                                <%-- ★画像⑤のエラーメッセージ表示エリア★ --%>
                                <%-- Action側でセットされたエラーマップ（errors）を参照 --%>
                                <c:if test="${not empty errors[student.studentNo]}">
                                    <div class="text-danger mt-1 small">
                                        0〜100の範囲で入力してください
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%-- ⑬ 登録して終了ボタン --%>
            <div class="mt-4 mb-5">
                <button type="submit" class="btn btn-secondary">登録して終了</button>
            </div>
        </form>
    </c:if>
</section>