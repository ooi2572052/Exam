<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <div class="row">
            

            <%-- メインコンテンツエリア --%>
            <main class="col-md-10">
                <div class="p-4">
                    <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
                    
                    <form action="SubjectCreateExecute.action" method="post">
                        <%-- 入力項目を中央寄りにしすぎず、左側に寄せる設定 --%>
                        <div style="max-width: 600px;">
                            
                            <%-- 科目コード --%>
                            <div class="mb-4">
                                <label class="form-label" for="subject-cd-input">科目コード</label>
                                <input class="form-control" type="text" id="subject-cd-input" name="cd" 
                                       placeholder="科目コードを入力してください" maxlength="3" required>
                            </div>

                            <%-- 科目名 --%>
                            <div class="mb-4">
                                <label class="form-label" for="subject-name-input">科目名</label>
                                <input class="form-control" type="text" id="subject-name-input" name="name" 
                                       placeholder="科目名を入力してください" required>
                            </div>

                            <%-- 登録ボタン --%>
                            <div class="mt-4">
                                <button class="btn btn-primary px-4" type="submit">登録</button>
                            </div>
                        </div>
                    </form>

                    <%-- 戻るリンク --%>
                    <div class="mt-3">
                        <a href="SubjectList.action" class="text-decoration-none">戻る</a>
                    </div>
                </div>
            </main>
        </div>
    </c:param>
</c:import>