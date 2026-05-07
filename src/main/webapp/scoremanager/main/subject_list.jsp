<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目管理
            </h2>
            <div class="my-2 text-end px-4">
                <a href="SubjectCreate.action">新規登録</a>
            </div>
            <c:choose>
                <c:when test="${not empty subjects && subjects.size() > 0}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="subject" items="${subjects}">
                                <tr>
                                    <td>${subject.subjectCd}</td>

                                    <td>${subject.subjectName}</td>

                                    <td class="text-center">
                                        <a href="SubjectUpdate.action?cd=${subject.subjectCd}">
                                            更新
                                        </a>
                                    </td>

                                    <td class="text-center">
                                        <a href="SubjectDelete.action?cd=${subject.subjectCd}">
                                            削除
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="mx-4 mb-3">
                        科目情報が存在しませんでした。
                    </div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>