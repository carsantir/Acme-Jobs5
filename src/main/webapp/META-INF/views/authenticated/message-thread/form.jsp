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
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title" />		
  <jstl:if test="${command == 'show'}">
   		<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment" />
    </jstl:if>
   	
	<acme:form-submit code="authenticated.messageThread.form.label.messages" 
		action="/authenticated/message/list?id=${id}" method="get" />			
			
	<acme:form-submit code="authenticated.messageThread.form.button.notusers" 
		action="/authenticated/authenticated/list-not-involved?mtId=${id}" method="get" />		
			
	<acme:form-submit code="authenticated.messageThread.form.button.users" 
		action="/authenticated/can-participate/list-involved?mtId=${id}" method="get" />
	
	<acme:form-submit code="authenticated.message.form.button.create"
		action="/authenticated/message/create?id=${id}" method="get"/>
		
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>
