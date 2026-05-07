<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <div class="row">
            <%-- サイドメニュー --%>

            <%-- メインコンテンツ --%>
            <main class="col-md-10 p-4">
                <h2 class="h3 mb-4 bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
                
                <div class="mt-3">
                    <p>登録が完了しました</p>
                </div>

                <div class="mt-4">
                    <a href="SubjectCreate.action" class="text-decoration-none">戻る</a>
                    <span class="mx-2">|</span>
                    <a href="SubjectList.action" class="text-decoration-none">科目一覧へ</a>
                </div>
            </main>
        </div>
    </c:param>
</c:import>