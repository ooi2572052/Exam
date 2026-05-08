<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">科目情報変更</c:param>

    <c:param name="content">

        <!-- タイトル -->
        <h2 class="h5 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報変更
        </h2>

        <!-- フォーム -->
        <form action="SubjectUpdateExecute.action" method="post" class="mx-3">

            <!-- 科目コード -->
            <div class="mb-3">
                <label class="form-label">科目コード</label>
                <input type="text" name="subjectCd"
                       class="form-control"
                       value="${subject.subjectCd}" readonly>
            </div>

            <!-- 科目名 -->
            <div class="mb-3">
                <label class="form-label">科目名</label>
                <input type="text" name="subjectName"
                       class="form-control"
                       value="${subject.subjectName}">
            </div>

            <!-- 学校コード（必要なら） -->
            <input type="hidden" name="schoolCd" value="${subject.schoolCd}">

            <!-- 変更ボタン -->
			<button type="submit" class="btn btn-primary">
			    変更
			</button>

    		<!-- 戻るリンク -->
		    <div class="mt-3">
		        <a href="SubjectList.action"
		           class="text-primary text-decoration-underline">
		            戻る
		        </a>
		    </div>
            

        </form>

    </c:param>

</c:import>