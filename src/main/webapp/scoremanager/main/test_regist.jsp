<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績管理</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
 
            <%-- 検索条件指定エリア --%>
            <form action="TestRegist.action" method="get">
                <div class="row border-bottom pb-4 mb-4">
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select name="f1" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select name="f2" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="str" items="${class_num_set}">
                                <option value="${str}" <c:if test="${str == f2}">selected</c:if>>${str}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <select name="f3" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectCd}" <c:if test="${subject.subjectCd == f3}">selected</c:if>>
                                    ${subject.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select name="f4" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="n" items="${num_set}">
                                <option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 d-flex align-items-end">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </div>
            </form>

            <%-- ★修正：メッセージ表示（未検索時） --%>
            <c:if test="${empty students and empty errors}">
                <div class="alert alert-info">
                    入学年度とクラスと科目と回数を選択してください
                </div>
            </c:if>

            <%-- エラー表示 --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <c:forEach var="e" items="${errors}">
                        <div>${e}</div>
                    </c:forEach>
                </div>
            </c:if>
 
            <c:if test="${not empty students}">
                <div class="mb-3 fs-5 fw-bold text-secondary">科目：${subject_name}（${num}回）</div>
                <form action="TestRegistExecute.action" method="post">
                    <input type="hidden" name="subject_cd" value="${subject_cd}">
                    <input type="hidden" name="num" value="${num}">
 
                    <table class="table table-hover mt-3">
                        <thead>
                            <tr class="text-secondary">
                                <th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <c:set var="yearKey" value="year_${student.studentNo}" />
                                    <c:set var="nameKey" value="name_${student.studentNo}" />
                                    <td>${requestScope[yearKey]}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.studentNo}</td>
                                    <td>${requestScope[nameKey]}</td>
                                    <td>
                                        <input type="number" name="points" value="${student.point >= 0 ? student.point : ''}" class="form-control" min="0" max="100" style="max-width: 100px;">
                                        <input type="hidden" name="student_nos" value="${student.studentNo}">
                                        <%-- ★追加：保存時に必要なクラス情報を隠しパラメータで送る --%>
                                        <input type="hidden" name="class_nums" value="${student.classNum}">
                                        <c:if test="${errors.contains(student.studentNo)}">
                                            <div class="text-danger small">0〜100の範囲で入力してください</div>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="TestDelete.action?student_no=${student.studentNo}&subject_cd=${subject_cd}&num=${num}&f1=${f1}&f2=${f2}&f3=${f3}&f4=${f4}" 
                                           class="btn btn-outline-danger btn-sm" 
                                           onclick="return confirm('この成績を削除しますか？');">
                                           削除
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>	
                        </tbody>
                    </table>
                    <div class="mt-4"><button type="submit" class="btn btn-secondary">登録して終了</button></div>
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>