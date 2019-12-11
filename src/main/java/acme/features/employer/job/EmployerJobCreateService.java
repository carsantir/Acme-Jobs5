
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "description", "draft");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;

		result = new Job();

		result.setEmployer(this.repository.findEmployerbyEmployerId(request.getPrincipal().getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean workloadFinal;
		Collection<Duty> duties = this.repository.findDutiesFromJob(entity.getId());

		if (!entity.isDraft()) {
			workloadFinal = duties.stream().mapToDouble(d -> d.getPercentage()).sum() == 100.00;
			errors.state(request, workloadFinal, "draft", "employer.job.error.workload");
		}

		boolean titleSpam, referenceSpam, descriptionSpam;

		String spamWords = this.repository.findConfigurationParameters().stream().findFirst().get().getSpamWords();
		Double threshold = this.repository.findConfigurationParameters().stream().findFirst().get().getSpamThreshold();

		String[] spamArray = spamWords.toLowerCase().split(",");

		double numSpamTitle = 0;
		double numSpamReference = 0;
		double numSpamDescription = 0;

		String title = entity.getTitle().toLowerCase();
		String reference = entity.getReference().toLowerCase();
		String description = entity.getDescription().toLowerCase();

		if (entity.getTitle() != null && entity.getReference() != null && entity.getDescription() != null) {
			for (String element : spamArray) {

				while (title.indexOf(element) > -1) {
					title = title.substring(title.indexOf(element) + element.length(), title.length());
					numSpamTitle++;
				}

				while (reference.indexOf(element) > -1) {
					reference = reference.substring(reference.indexOf(element) + element.length(), reference.length());
					numSpamReference++;
				}

				while (description.indexOf(element) > -1) {
					description = description.substring(description.indexOf(element) + element.length(), description.length());
					numSpamDescription++;
				}
			}

			titleSpam = numSpamTitle / entity.getTitle().split(" ").length < threshold;
			errors.state(request, titleSpam, "title", "employer.job.error.spam");

			referenceSpam = numSpamReference / entity.getReference().split(" ").length < threshold;
			errors.state(request, referenceSpam, "reference", "employer.job.error.spam");

			descriptionSpam = numSpamDescription / entity.getDescription().split(" ").length < threshold;
			errors.state(request, descriptionSpam, "description", "employer.job.error.spam");

		}
		Date deadLineMoment;
		Boolean isFutureDate;

		deadLineMoment = request.getModel().getDate("deadline");

		if (deadLineMoment != null) {
			isFutureDate = deadLineMoment.after(Calendar.getInstance().getTime());
			errors.state(request, isFutureDate, "deadline", "employer.job.error.future");
		}

		boolean isEuro;

		if (entity.getSalary() != null) {
			isEuro = entity.getSalary().getCurrency().equals("€") || entity.getSalary().getCurrency().equals("EUR");
			errors.state(request, isEuro, "salary", "employer.job.error.must-be-euro");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {

		this.repository.save(entity);
	}

}
