<%-- 成績管理JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<form action="<%-- test_regist.java --%>" method="post">
				<div>
					<label for="ent_year">入学年度</label>
					<select class="form-select" id="ent_year" name="ent_year">
						<option value="0">--------</option>
						<c:forEach var="year" items="${ent_year_set }">
							<%-- 現在のyearと選択されていたent_yearが一致していた場合selectedを追記 --%>
							<option value="${year}" <c:if test="${year == student.entYear}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mx-auto py-2">
					<label for="class_num">クラス</label>
					<select class="form-select" id="class_num" name="class_num">
						<c:forEach var="num" items="${class_num_set}">
						<%-- 現在のnumと選択されていたclass_numが一致していた場合selectedを追記 --%>
			                <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
			            </c:forEach>
					</select>
				</div>
				<div class="mt-2 text-warning">${errors.get("2") }</div>
				<div>
					<label for="sunject">科目</label><br>
					<select class="form-control" id="subject" name="subject"/
						<option value>
				</div>
				 <div>
        			<label>在学中</label>
       				 <input type="checkbox" name="is_attend" <c:if test="${student.isAttend()}">checked</c:if> />
   				 </div>
				<div class="mx-auto py-2">
					<button type="submit" class="btn btn-primary" id="create-button" name="end">変更</button>
				</div>
			</form>
			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>