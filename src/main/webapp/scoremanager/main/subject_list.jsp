<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
            
            <%-- 新規登録ボタン --%>
            <div class="my-2 text-end px-4">
                <a href="SubjectCreate.action">新規登録</a>
            </div>

            <%-- 科目情報の表示判定 --%>
            <c:choose>
                <%-- 科目が1件以上ある場合 --%>
                <c:when test="${subjects.size() > 0}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th></th> <%-- 更新用ボタン列 --%>
                                <th></th> <%-- 削除用ボタン列 --%>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="subject" items="${subjects}">
                                <tr>
                                    <td>${subject.cd}</td>
                                    <td>${subject.name}</td>
                                    <%-- 更新・削除などのリンク --%>
                                    <td class="text-center">
                                        <a href="SubjectUpdate.action?cd=${subject.cd}">更新</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="SubjectDelete.action?cd=${subject.cd}">削除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                
                <%-- 科目が0件（画像の状態）の場合 --%>
                <c:otherwise>
                    <div class="mx-4">科目情報が存在しませんでした。</div>
                    <%-- 画像のように空のテーブルのヘッダーだけ出したい場合は以下を追加 --%>
                    <table class="table table-hover mt-3">
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                            </tr>
                        </thead>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>