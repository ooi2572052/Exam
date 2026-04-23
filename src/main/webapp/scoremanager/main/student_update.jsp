<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
	<c:param name="title">
	学生情報変更
	</c:param>

	<c:param name="content">
	<section class="me-4">
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
			学生情報変更
		</h2>

		<form action="StudentUpdateExecute.action" method="post">
			<div class="mx-3">

				<!-- 学生番号 -->
				<div class="mb-3">
					<label class="form-label">学生番号</label>
					<input type="text" class="form-control" value="${student.studentNo}" readonly>
					<input type="hidden" name="no" value="${student.studentNo}">
				</div>

				<!-- 氏名 -->
				<div class="mb-3">
					<label class="form-label">氏名</label>
					<input type="text" class="form-control" name="name"
						value="${student.studentName}" required maxlength="30">
				</div>

				<!-- 入学年度（←ここが今回のポイント） -->
				<div class="mb-3">
					<label class="form-label">入学年度</label>
					<select class="form-select" name="entYear">
						<option value="">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}"
								<c:if test="${year == student.entYear}">selected</c:if>>
								${year}
							</option>
						</c:forEach>
					</select>
				</div>

				<!-- クラス -->
				<div class="mb-3">
					<label class="form-label">クラス</label>
					<select class="form-select" name="classNum">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}"
								<c:if test="${num == student.classNum}">selected</c:if>>
								${num}
							</option>
						</c:forEach>
					</select>
				</div>

				<!-- 在学中 -->
				<div class="form-check mb-3">
					<input class="form-check-input" type="checkbox"
						name="isAttend" value="true"
						<c:if test="${student.attend}">checked</c:if>>
					<label class="form-check-label">在学中</label>
				</div>

				<!-- エラー表示 -->
				<div class="text-danger mb-3">
					${errors.name}
				</div>

				<!-- ボタン -->
				<div class="text-end">
					<button type="submit" class="btn btn-primary">更新</button>
					<a href="StudentList.action" class="btn btn-secondary">戻る</a>
				</div>

			</div>
		</form>
	</section>
	</c:param>
</c:import>