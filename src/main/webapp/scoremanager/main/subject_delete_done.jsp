<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレートである base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <%-- base.jsp の ${param.title} に渡す値 --%>
    <c:param name="title">科目削除完了</c:param>

    <%-- base.jsp の ${param.content} に渡すHTMLコンテンツ --%>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>
            
            <%-- 削除完了メッセージ --%>
            <div class="alert alert-success" role="alert">
                削除が完了しました
            </div>

            <%-- リンク --%>
            <div class="mt-4">
                <a href="SubjectList.action" class="text-decoration-none">科目一覧へ</a>
            </div>
        </section>
    </c:param>
</c:import>