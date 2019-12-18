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

	<acme:form-textbox code="authenticated.can-participate.form.label.userAccount.username" path="authenticated.userAccount.username"/>
	<acme:form-textbox code="authenticated.can-participate.form.label.messageThread" path="messageThread.title"/>

	<jstl:if test="${command == 'show'}">
   		<acme:form-submit code="authenticated.can-participate.form.button.delete"
   			action="/authenticated/can-participate/delete"/>
   	</jstl:if>
   	
   	<jstl:if test="${command == 'delete'}">
   		<acme:form-submit code="authenticated.can-participate.form.button.delete"
   			action="/authenticated/can-participate/delete" />
   	</jstl:if>
   	
	<acme:form-return code="authenticated.can-participate.form.button.return"/>
</acme:form>
