<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<jsp:include page="headTag.jsp"/>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="container col-6">
            <c:choose>
			    <c:when test="${empty posts}">
			        <h2><spring:message code="welcome.thereAreNotPosts"/></h2>
			    </c:when>
			    <c:otherwise>
			        <c:forEach var="post" items="${posts}">
						<div class="post">
				            <div class="row post-header">
				            	<div class="img-frame">
				            		<img src="${post.user.avatar}"/>
				            	</div>
				            	<div class="post-description">
					            	<div class="post-title">
					            		<c:out value="${post.title}"/>
					            	</div>
					            	<div class="author">
					            		${post.user.name} ${post.user.surname}
					            	</div>
				            	</div>
				            </div>
				            <div class="post-content">
				            	<c:out value="${post.content}"/>
				            </div>
				            <div class="like-counter">
			            		<span id="counter-${post.id}">
			            			${post.likes.size()}
			            		</span>
			            		<spring:message code="welcome.likeIt"/>
				            </div>
				    		<div>
				            	<a class="like-link" id="${post.id}">
				            		<spring:message code="welcome.giveLike"/>
				            	</a>
				            </div>
			            </div>
		            </c:forEach>
			    </c:otherwise>
			</c:choose>
		</div>
		<jsp:include page="usersPopUp.jsp"/>
	</body>
</html>
