<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            
            <%-- 検索フォーム --%>
            <form action="TestListExecute.action" method="post">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded">
                    <div class="col-3">
                        <label>入学年度</label>
                        <select class="form-select" name="ent_year">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label>クラス</label>
                        <select class="form-select" name="class_num">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label>科目</label>
                        <select class="form-select" name="subject_cd">
                            <option value="0">--------</option>
                            <c:forEach var="sub" items="${subject_set}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == subject_cd}">selected</c:if>>${sub.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>
                    
                    <%-- エラーメッセージ表示エリア --%>
                    <div class="text-danger mt-2">${errors.get("f1")}</div>
                </div>
            </form>

            <%-- 0件表示判定 --%>
            <c:if test="${!empty tests && tests.size() == 0}">
                <div class="text-danger mt-3">学生情報が存在しませんでした</div>
            </c:if>
            
            <%-- 結果テーブルの表示（省略） --%>
        </section>
    </c:param>
</c:import>