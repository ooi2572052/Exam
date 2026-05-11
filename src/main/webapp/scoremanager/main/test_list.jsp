<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp をインポート --%>
<c:import url="/common/base.jsp">
    <%-- タイトル --%>
    <c:param name="title">成績管理</c:param>

    <%-- コンテンツ --%>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 科目検索 --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestRegist.action" method="get">
                <div class="row border-bottom pb-4 mb-4 align-items-end flex-nowrap">
                    <div class="col-auto pb-2 me-2">
                        <span class="fw-bold" style="white-space: nowrap;">科目検索</span>
                    </div>

                    <%-- 入学年度 --%>
                    <div class="col-2">
                        <label class="form-label" for="ent-year-select">入学年度</label>
                        <select name="f1" id="ent-year-select" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス --%>
                    <div class="col-2">
                        <label class="form-label" for="class-num-select">クラス</label>
                        <select name="f2" id="class-num-select" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="str" items="${class_num_set}">
                                <option value="${str}" <c:if test="${str == f2}">selected</c:if>>${str}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div class="col">
                        <label class="form-label" for="subject-select">科目</label>
                        <select name="f3" id="subject-select" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectCd}" <c:if test="${subject.subjectCd == f3}">selected</c:if>>
                                    ${subject.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 回数 --%>
                    <div class="col-2">
                        <label class="form-label" for="num-select">回数</label>
                        <select name="f4" id="num-select" class="form-select">
                            <option value="0">--------</option>
                            <c:forEach var="n" items="${num_set}">
                                <option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 検索ボタン --%>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary px-4">検索</button>
                    </div>
                </div>
            </form>

            <%-- 学生別成績検索 --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="get">
                <div class="row border-bottom pb-4 mb-4 align-items-end flex-nowrap">
                    <div class="col-auto pb-2 me-3">
                        <span class="fw-bold" style="white-space: nowrap;">学生別成績</span>
                    </div>

                    <%-- 学生番号 --%>
                    <div class="col-3">
                        <label class="form-label" for="student-no-input">学生番号</label>
                        <input type="text" name="studentNo" id="student-no-input" class="form-control" placeholder="学生番号を入力" required>
                    </div>

                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary px-4">検索</button>
                    </div>
                    <div class="col"></div>
                </div>
            </form>

            <%-- エラー表示 --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

			 <%-- 学生別検索結果 --%>
			<c:if test="${not empty studentList}">
			    <div class="mt-5">
			        <h3 class="h5 mb-3 bg-info bg-opacity-10 py-2 px-3">学生別成績一覧</h3>
			        
			        <div class="mb-3">
			            <strong>氏名：${student.studentName} （${student.studentNo}）</strong>
			        </div>
		
			        <table class="table table-hover">
			            <thead>
			                <tr>
			                    <th>科目名</th>
			                    <th>科目コード</th>
			                    <th>回数</th>
			                    <th>点数</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="s" items="${studentList}">
			                    <tr>
			                        <td>
			                            ${s.subjectName}
			                        </td>
			                        <td>
			                            ${s.subjectCd} 
			                        </td>
			                        <td>
			                            ${s.num}
			                        </td>
			                        <td>
			                            ${s.point}
			                        </td>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
			    </div>
			</c:if>
            <%-- バリデーションエラー --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <c:forEach var="e" items="${errors}">
                        <div>${e}</div>
                    </c:forEach>
                </div>
            </c:if>
        </section>
    </c:param>
</c:import>