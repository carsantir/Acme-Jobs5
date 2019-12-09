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
	<acme:form-textarea code="sponsor.commercial-banner.form.label.pictureUrl" path="pictureUrl"/>		
   	<acme:form-moment code="sponsor.commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-textarea code="sponsor.commercial-banner.form.label.targetUrl" path="targetUrl"/>	
	<acme:form-textbox code="sponsor.commercial-banner.form.label.creditCard" path="creditCard"/>	
	<acme:form-textbox code="sponsor.commercial-banner.form.label.sponsor.userAccount.username" path="sponsor.userAccount.username" />
	
	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
</acme:form>
