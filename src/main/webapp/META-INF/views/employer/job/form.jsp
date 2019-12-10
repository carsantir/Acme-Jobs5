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
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="employer.job.form.label.description" path="description" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="employer.job.form.label.username" path="employer.userAccount.username" />
	</jstl:if>
	<acme:form-checkbox code="employer.job.form.label.draft" path="draft" />
	<acme:form-hidden path="id"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-submit code="employer.job.form.label.duty" action="/employer/duty/list?id=${id}" method="get"/>
		<acme:form-submit code="employer.job.form.button.create.duty" action="/employer/duty/create?jobId=${id}" method="get"/>
		<acme:form-submit code="employer.job.form.button.link" action="/employer/audit-record/list-all-active?id=${id}" method="get" />
	</jstl:if>
	<acme:form-submit test="${command == 'create' }"
		code="employer.job.form.button.create"
		action="/employer/job/create"/>
	
	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>
