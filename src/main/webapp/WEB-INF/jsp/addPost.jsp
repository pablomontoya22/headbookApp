<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<jsp:include page="headTag.jsp"/>
	<body>
		<jsp:include page="header.jsp">
			<jsp:param name="post_class" value="active"/>
		</jsp:include>
		<div class="container col-5">
			<h3><spring:message code="addPost.title"/></h3>
			<form action="/posts" method="post">
				<div class="form-group">
					<input class="form-control" type="text" name="title"
					placeholder="<spring:message code="addPost.commentTitle"/>"/>
				</div>
				<div class="form-group">
					<textarea name="content" class="form-control"></textarea>
				</div>
				<div class="form-group row">
					<label for="user" class="col-3 col-form-label">
						<spring:message code="addPost.postAs"/>:
					</label>
					<div class="col-9">
						<select id="user" name="user" class="form-control">
						<c:choose>
					    <c:when test="${empty users}">
					        <option>
					        	<spring:message code="addPost.thereAreNotUsers"/>
					        </option>
					    </c:when>
					    <c:otherwise>
					    	<option>
					    		<spring:message code="common.selectOne"/>
					    	</option>
					        <c:forEach var="user" items="${users}">
					        <option value="${user.id}">
					        	${user.name} ${user.surname}
					        </option>
				            </c:forEach>
					    </c:otherwise>
						</c:choose>
						</select>
					</div>
				</div>
				<div class="form-group row justify-content-center">
					<input type="submit" class="btn btn-primary col-3" value="<spring:message code="addPost.button"/>"/>
				</div>
			</form>
		</div>
	</body>
</html>
