<%-- 中略 --%>
<c:forEach var="subject" items="${subjects}">
    <tr>
        <td>${subject.subjectCd}</td>   <%-- cd から修正 --%>
        <td>${subject.subjectName}</td> <%-- name から修正 --%>
        <td class="text-center">
            <a href="SubjectUpdate.action?cd=${subject.subjectCd}">更新</a>
        </td>
        <td class="text-center">
            <a href="SubjectDelete.action?cd=${subject.subjectCd}">削除</a>
        </td>
    </tr>
</c:forEach>
<%-- 中略 --%>
<c:otherwise>
    <div class="mx-4">科目情報が存在しませんでした。</div>
    <%-- 表のヘッダーを表示 --%>
</c:otherwise>