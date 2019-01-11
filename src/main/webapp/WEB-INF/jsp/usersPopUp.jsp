<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="users-list">
	<div class="form-group row">
		<label for="user-to-like" class="col-3 col-form-label">
			<spring:message code="usersPopUp.likeFrom"/>:
		</label>
		<div class="col-9">
			<select id="user-to-like" name="user" class="form-control">
			<c:choose>
		    <c:when test="${empty users}">
		        <option>
		        	<spring:message code="addPost.thereAreNotUsers"/>
		        </option>
		    </c:when>
		    <c:otherwise>
		    	<option value="">
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
</div>