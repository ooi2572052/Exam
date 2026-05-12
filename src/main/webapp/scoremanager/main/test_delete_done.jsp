<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績削除完了</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績削除
            </h2>
            
            <!-- 完了メッセージ -->
            <div class="alert alert-success" role="alert">
                成績の削除が完了しました
            </div>

            <!-- ボタン -->
            <div class="mt-4">
                <a href="TestRegist.action" class="btn btn-secondary">成績登録へ戻る</a>
                <a href="Menu.action" class="btn btn-outline-secondary ms-2">メニューへ</a>
            </div>
        </section>
    </c:param>
</c:import>