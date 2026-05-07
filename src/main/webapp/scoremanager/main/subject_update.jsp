<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>科目情報変更</title>
</head>

<body>

<h2>科目情報変更</h2>

<form action="SubjectUpdateExecute.action" method="post">

    <p>科目コード</p>

    ${subject.subjectCd}

    <input type="hidden"
           name="subjectCd"
           value="${subject.subjectCd}">

    <p>科目名</p>

    <input type="text"
           name="subjectName"
           value="${subject.subjectName}">

    <br>

    <font color="red">
        ${errors.subjectName}
    </font>

    <br><br>

    <input type="submit" value="変更">

</form>

<br>

<a href="SubjectList.action">戻る</a>

</body>
</html>