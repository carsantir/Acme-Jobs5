<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="mtId"/>
	<acme:form-select code="authenticated.add-user.form.select" path="users">
	
		<jstl:forEach var="item" items="${users}">
		
			<acme:form-option code="${item.userAccount.username}" value="${item.id}"/>
		
		</jstl:forEach>
	
	</acme:form-select>
	
	<jstl:if test="${command == 'create' }">
		<acme:form-submit code="authenticated.can-participate.form.button.add"
			action="/authenticated/can-participate/create" />
	</jstl:if>
   	
	<acme:form-return code="authenticated.involved.form.button.return"/>
</acme:form>
