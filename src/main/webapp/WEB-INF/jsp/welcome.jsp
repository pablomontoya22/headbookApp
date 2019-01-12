<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
						<div class="post" id="post-${post.id}">
				            <div class="row post-header">
				            	<div class="img-frame">
				            		<img src="${post.user.avatar}"/>
				            	</div>
				            	<div class="post-description">
					            	<div class="post-info">
					            		<span class="user">
						            		${post.user.name} ${post.user.surname}
						            		<spring:message code="common.said"/>
					            		</span>
					            		<span class="title">
					            			<c:out value="${post.title}"/>
					            		</span>
					            	</div>
					            	<div class="content-cut">
						            	<c:out value="${fn:substring(post.content, 0, 50)}"/>
						            	<c:set var="contentLength" scope="session" value="${fn:length(post.content)}"/>
						            	<c:if test ="${contentLength>=50}">
						            	<span class="see-more">
						            		<spring:message code="common.seeMore"/>
						            	</span>
						            	</c:if>
						            </div>
				            	</div>
				            </div>
				            <div class="like-counter">
				            	<span>
				            		<span id="counter-${post.id}">
				            			${post.likes.size()}
				            		</span>
				            		<spring:message code="welcome.likeIt"/>
				            	</span>
			            		<span>
			            			<a class="like-link" id="${post.id}">
				            		<spring:message code="welcome.giveLike"/>
				            	</a>
			            		</span>
			            		<span class="date">
			            			<fmt:formatDate value="${post.date}" pattern="dd-MM-yyyy HH:mm"/>
			            		</span>
				            </div>
			            </div>
		            </c:forEach>
			    </c:otherwise>
			</c:choose>
		</div>
		<jsp:include page="usersPopUp.jsp"/>
	</body>
</html>
