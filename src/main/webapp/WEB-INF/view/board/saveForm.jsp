<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>	
<h2>글쓰기</h2>
<form action="/board/save" method="post">
  <div class="mb-3 mt-3">
    <label for="title" class="form-label">제목:</label>
    <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
  </div>
  <div class="mb-3">
    <label for="content" class="form-label">내용:</label>
    <input type="text" class="form-control" id="content" placeholder="Enter content" name="content">
  </div>
  <div class="mb-3">
    <label for="author" class="form-label">작성자:</label>
    <input type="text" class="form-control" id="author" placeholder="Enter author" name="author">
  </div>
  <button type="submit" class="btn btn-primary">글쓰기</button>
</form>

</body>
</html>