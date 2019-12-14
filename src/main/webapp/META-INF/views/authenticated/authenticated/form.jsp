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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.involved.form.label.username" path="userAccount.username" />		
	<acme:form-hidden path="messageThread.id"/>
   	
   	<acme:form-submit code="authenticated.involved.canParticipate.form.button.create"
		action="/authenticated/can-participate/create?authenticatedId=${id}&messageThreadId=${messageThread.id}" method="get"/>
   	
	<acme:form-return code="authenticated.involved.form.button.return"/>
</acme:form>
