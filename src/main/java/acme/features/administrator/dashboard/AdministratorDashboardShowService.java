
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	private AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalAnnouncements", "totalCompanyRecords", "totalInvestorRecords", "minRewardRequest", "maxRewardRequest", "averageRewardRequest", "sdRewardRequest", "minRewardOffer", "maxRewardOffer", "averageRewardOffer",
			"sdMinRewardOffer", "sdMaxRewardOffer", "companiesPerSector", "companySectors", "investorsPerSector", "investorSectors", "avgJobsPerEmployer", "avgApplicationsPerEmployer", "avgApplicationsPerWorker", "ratioOfPendingApplications",
			"ratioOfAcceptedApplications", "ratioOfRejectedApplications", "ratioOfDraftJobs", "ratioOfPublishedJobs");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {

		assert request != null;

		Dashboard result = new Dashboard();

		result.setTotalAnnouncements(this.repository.getTotalAnnouncement());
		result.setTotalCompanyRecords(this.repository.getTotalCompanyRecord());
		result.setTotalInvestorRecords(this.repository.getTotalInvestorRecord());

		result.setMinRewardRequest(this.repository.getMinRewardRequest());
		result.setMaxRewardRequest(this.repository.getMaxRewardRequest());

		Money a = new Money();
		a.setAmount(this.repository.getAverageRewardRequestAmount());
		a.setCurrency(this.repository.getAverageRewardRequestCurrency());
		result.setAverageRewardRequest(a);

		Money c = new Money();
		c.setAmount(this.repository.getSdRewardRequestAmount());
		c.setCurrency(this.repository.getAverageRewardRequestCurrency());
		result.setSdRewardRequest(c);

		result.setMinRewardOffer(this.repository.getMinRewardOffer());
		result.setMaxRewardOffer(this.repository.getMaxRewardOffer());

		Money b = new Money();
		b.setAmount(this.repository.getAverageRewardOfferAmount());
		b.setCurrency(this.repository.getAverageRewardOfferCurrency());
		result.setAverageRewardOffer(b);

		Money d = new Money();
		d.setAmount(this.repository.getMinSdRewardRequestAmount());
		d.setCurrency(this.repository.getAverageRewardOfferCurrency());
		result.setSdMinRewardOffer(d);

		Money e = new Money();
		e.setAmount(this.repository.getMaxSdRewardRequestAmount());
		e.setCurrency(this.repository.getAverageRewardOfferCurrency());
		result.setSdMaxRewardOffer(e);

		result.setCompaniesPerSector(this.repository.getCompaniesPerSector());

		result.setCompanySectors(this.repository.getCompanySectors());

		result.setInvestorsPerSector(this.repository.getInvestorPerSector());

		result.setInvestorSectors(this.repository.getInvestorSectors());

		result.setAvgJobsPerEmployer(this.repository.averageNumberOfJobsPerEmployer());

		result.setAvgApplicationsPerEmployer(this.repository.averageNumberOfApplicationsPerEmployer());

		result.setAvgApplicationsPerWorker(this.repository.averageNumberOfApplicationsPerWorker());

		result.setRatioOfAcceptedApplications(this.repository.ratioOfAcceptedApplications());

		result.setRatioOfPendingApplications(this.repository.ratioOfPendingApplications());

		result.setRatioOfRejectedApplications(this.repository.ratioOfRejectedApplications());

		result.setRatioOfDraftJobs(this.repository.ratioOfDraftJobs());

		result.setRatioOfPublishedJobs(this.repository.ratioOfPublishedJobs());

		return result;
	}

}
