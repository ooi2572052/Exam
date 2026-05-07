<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績参照
			</h2>

			<!-- 検索フォーム -->
			<form action="TestList.action" method="post">

				<div class="row border mx-3 mb-3 py-3 align-items-end rounded">

					<!-- 入学年度 -->
					<div class="col-3">
						<label class="form-label">入学年度</label>
						<select name="entYear" class="form-select">
							<option value="">--------</option>
							<c:forEach var="y" items="${ent_year_set}">
								<option value="${y}" <c:if test="${y==param.entYear}">selected</c:if>>
									${y}
								</option>
							</c:forEach>
						</select>
					</div>

					<!-- クラス -->
					<div class="col-3">
						<label class="form-label">クラス</label>
						<select name="classNum" class="form-select">
							<option value="">--------</option>
							<c:forEach var="cnum" items="${class_num_set}">
								<option value="${cnum}" <c:if test="${cnum==param.classNum}">selected</c:if>>
									${cnum}
								</option>
							</c:forEach>
						</select>
					</div>

					<!-- 科目 -->
					<div class="col-3">
						<label class="form-label">科目</label>
						<select name="subjectCd" class="form-select">
							<option value="">--------</option>
							<c:forEach var="s" items="${subjectList}">
								<option value="${s.subjectCd}"
									<c:if test="${s.subjectCd==param.subjectCd}">selected</c:if>>
									${s.subjectName}
								</option>
							</c:forEach>
						</select>
					</div>

					<!-- 検索ボタン -->
					<div class="col-3 text-center">
						<button class="btn btn-secondary w-100">検索</button>
					</div>

				</div>

				<!-- 学生番号検索 -->
				<div class="row border mx-3 mb-3 py-3 align-items-end rounded">

					<div class="col-4">
						<label class="form-label">学生番号</label>
						<input type="text" name="studentNo"
							value="${param.studentNo}"
							class="form-control">
					</div>

					<div class="col-2 text-center">
						<button class="btn btn-secondary w-100">検索</button>
					</div>

				</div>

			</form>

			<!-- 結果表示 -->
			<c:choose>

				<c:when test="${list != null && list.size() > 0}">

					<div class="px-3 mb-2">検索結果：${list.size()}件</div>

					<table class="table table-hover">

						<tr>
							<th>学生番号</th>
							<th>科目</th>
							<th>回数</th>
							<th>点数</th>
						</tr>

						<c:forEach var="t" items="${list}">
							<tr>
								<td>${t.studentNo}</td>
								<td>${t.subjectCd}</td>
								<td>${t.no}</td>
								<td>${t.point}</td>
							</tr>
						</c:forEach>

					</table>

				</c:when>

				<c:otherwise>
					<div class="px-3">成績情報が存在しませんでした。</div>
				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>