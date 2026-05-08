<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">科目情報変更完了</c:param>

    <c:param name="content">

        <!-- タイトル -->
        <h2 class="h5 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報変更
        </h2>

        <div class="mx-3">

            <!-- 完了メッセージ -->
            <div class="text-center py-2 mb-4 bg-success bg-opacity-25">
                変更が完了しました
            </div>

            <!-- 戻る -->
            <div>
                <a href="SubjectList.action"
                   class="text-primary text-decoration-underline">
                    科目一覧
                </a>
            </div>

        </div>

    </c:param>

</c:import>