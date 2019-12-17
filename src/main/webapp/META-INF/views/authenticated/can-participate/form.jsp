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

	<acme:form-textbox code="authenticated.canParticipate.form.button.username" path="authenticated.userAccount.username"/>
	<acme:form-hidden path="messageThread.id"/>	

		
	<jstl:if test="${command == 'create'}">		   	
	   	<acme:form-submit code="authenticated.canParticipate.form.button.create"
			action="/authenticated/can-participate/create"/>
   	</jstl:if>
   	
   	 <%-- TO DO:--%>
   	<jstl:if test="${command == 'delete'}">
   		<acme:form-submit code="authenticated.canParticipate.form.button.delete"
   			action="/authenticated/can-participate/delete"/>
   	</jstl:if>
   	
	<acme:form-return code="authenticated.canParticipate.form.button.return"/>
</acme:form>
