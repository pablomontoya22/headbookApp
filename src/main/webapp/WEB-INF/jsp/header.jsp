<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/">
		<spring:message code="header.appTitle"/>
	</a>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a href="/posts" class="nav-link ${param.post_class}">
					<spring:message code="header.createPost"/>
				</a>
			</li>
		</ul>
	</div>
</nav>