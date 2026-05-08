<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 検索フォームエリア --%>
<section class="me-4">
	<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">① 成績管理</h2>

	<form action="TestRegist.action" method="get">
		<div class="row border-bottom pb-4 mb-4">
			<%-- ② 入学年度 --%>
			<div class="col-2">
				<label class="form-label" for="ent-year-select">③ 入学年度</label>
				<select name="f1" id="ent-year-select" class="form-select">
					<option value="0">⑥ --------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>

			<%-- ④ クラス --%>
			<div class="col-2">
				<label class="form-label" for="class-num-select">⑦ クラス</label>
				<select name="f2" id="class-num-select" class="form-select">
					<option value="0">--------</option>
					<c:forEach var="str" items="${class_num_set}">
						<option value="${str}" <c:if test="${str == f2}">selected</c:if>>${str}</option>
					</c:forEach>
				</select>
			</div>

			<%-- ⑤ 科目 --%>
			<div class="col-4">
				<label class="form-label" for="subject-select">⑧ 科目</label>
				<select name="f3" id="subject-select" class="form-select">
					<option value="0">--------</option>
					<c:forEach var="subject" items="${subjects}">
						<option value="${subject.subjectCd}" <c:if test="${subject.subjectCd == f3}">selected</c:if>>
							${subject.subjectName}
						</option>
					</c:forEach>
				</select>
			</div>

			<%-- ⑨ 回数 --%>
			<div class="col-2">
				<label class="form-label" for="num-select">回数</label>
				<select name="f4" id="num-select" class="form-select">
					<option value="0">--------</option>
					<c:forEach var="n" items="${num_set}">
						<option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
					</c:forEach>
				</select>
			</div>

			<%-- ⑩ 検索ボタン --%>
			<div class="col-2 d-flex align-items-end">
				<button type="submit" class="btn btn-secondary" id="search-btn">検索</button>
			</div>
		</div>
	</form>

	<%-- エラーメッセージ表示用 --%>
	<c:if test="${not empty errors}">
		<div class="alert alert-danger">
			<c:forEach var="error" items="${errors}">
				<div>${error}</div>
			</c:forEach>
		</div>
	</c:if>
</section>