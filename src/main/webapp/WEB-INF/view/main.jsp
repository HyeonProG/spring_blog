<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/view/layout/header.jsp"%>

<div class="container p-5">

	<c:choose>
		<c:when test="${boardList != null}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.id}</td>
							<td>${board.title}</td>
							<td>${board.content}</td>
							<td>${board.author}</td>
							<td>
								<div class="d-flex">
									<form action="/board/${board.id}/delete">
										<button class="btn btn-danger">삭제</button>
									</form>
									<form action="/board/${board.id}/updateForm" method="get">
										<button class="btn btn-warning">수정</button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron display-4">
				<h5>게시글이 없습니다.</h5>
			</div>
		</c:otherwise>
	</c:choose>
		<div class="d-flex justify-content-center">
			<ul class="pagination">
				<!-- 이전 페이지 링크 -->
				<li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
				<a class="page-link" href="?page=${currentPage - 1}&size=${size}">이전</a>
				</li>
				<!-- 현재 페이지 -->
				<c:forEach begin="1" end="${totalPages}" var="page">
				<li class="page-item <c:if test='${page == currentPage}'> active </c:if>">
				<a class="page-link" href="?page=${page}&size=${size}">${page}</a>
				</li>
				</c:forEach>
				<!-- 다음 페이지 -->
				<li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="?page=${currentPage + 1}&size=${size}">다음</a>
				</li>
			</ul>
		</div>
</div>

<%@include file="/WEB-INF/view/layout/footer.jsp"%>