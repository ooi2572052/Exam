<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title">
    学生別成績一覧
</c:set>

<c:set var="content">

<div class="container mt-3">
    <h2 class="mb-4">
        学生別成績一覧
    </h2>
    <!-- エラー -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
    <!-- 学生情報 -->
    <c:if test="${not empty student}">
        <div class="mb-4">
            <h5>
                氏名：
                ${student.studentName}
                （${student.studentNo}）
            </h5>
        </div>
    </c:if>
    <!-- 成績一覧 -->
    <table
        class="table table-bordered table-hover">
        <thead class="table-primary">
        <tr>
            <th>科目名</th>
            <th>回数</th>
            <th>点数</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach
            var="s"
            items="${studentList}">
            <!-- 点数未入力除外 -->
            <c:if test="${s.point != 0}">
                <tr>
                    <td>
                        ${s.subjectName}
                    </td>
                    <td>
                        ${s.num}
                    </td>
                    <td>
                        ${s.point}
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
</c:set>
<c:import url="/common/base.jsp">
	<c:param
	    name="title"
	    value="${title}" />
    <c:param
	    name="content"
	    value="${content}" />
</c:import>