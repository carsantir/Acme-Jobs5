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

	<acme:form-hidden path="messageThread.id"/>	
	<acme:form-hidden path="authenticated.id"/>
	<acme:form-errors path=""/>
   	
   	<acme:form-submit code="authenticated.canParticipate.form.button.create"
		action="/authenticated/can-participate/create?authenticatedId=${id}&messageThreadId=${messageThread.id}" method="get"/>
   	
	<acme:form-return code="authenticated.canParticipate.form.button.return"/>
</acme:form>
