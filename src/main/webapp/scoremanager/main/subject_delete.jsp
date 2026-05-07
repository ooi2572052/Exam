<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <div class="row">
            <aside class="col-md-2 border-end" style="min-height: 80vh;">
                <div class="p-3">
                    <ul class="list-unstyled">
                        <li class="mb-2"><a href="Menu.action" class="text-decoration-none text-secondary">メニュー</a></li>
                        <li class="mb-2"><a href="StudentList.action" class="text-decoration-none text-secondary">学生管理</a></li>
                        <li class="mb-1 text-muted small fw-bold">成績管理</li>
                        <li class="mb-2 ms-3"><a href="TestRegist.action" class="text-decoration-none text-secondary small">成績登録</a></li>
                        <li class="mb-2 ms-3"><a href="TestList.action" class="text-decoration-none text-secondary small">成績参照</a></li>
                        <li class="mb-2"><a href="SubjectList.action" class="text-decoration-none fw-bold">科目管理</a></li>
                    </ul>
                </div>
            </aside>

            <main class="col-md-10 p-4">
                <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
                
                <div class="mx-3">
                    <p class="mb-4">
                        <c:out value="${subject.subjectName}" />を削除してよろしいですか？
                    </p>

                    <form action="SubjectDeleteExecute.action" method="post">
                        <input type="hidden" name="cd" value="${subject.subjectCd}">
                        
                        <button type="submit" class="btn btn-danger px-4">削除</button>
                    </form>

                    <div class="mt-3">
                        <a href="SubjectList.action" class="text-decoration-none">戻る</a>
                    </div>
                </div>
            </main>
        </div>
    </c:param>
</c:import>