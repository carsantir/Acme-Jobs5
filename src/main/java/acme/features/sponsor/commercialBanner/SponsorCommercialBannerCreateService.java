
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		Principal principal;
		principal = request.getPrincipal();
		String s = this.repository.findCreditCardBySponsorId(principal.getActiveRoleId());
		if (s != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "pictureUrl", "slogan", "targetUrl", "creditCard");

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;
		result = new CommercialBanner();
		result.setSponsor(this.repository.findSponsor(request.getPrincipal().getActiveRoleId()));
		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean sloganSpam;

		String spamWords = this.repository.findConfigurationParameters().stream().findFirst().get().getSpamWords();
		Double threshold = this.repository.findConfigurationParameters().stream().findFirst().get().getSpamThreshold();

		String[] spamArray = spamWords.toLowerCase().split(",");

		double numSpamSlogan = 0;

		String slogan = entity.getSlogan().toLowerCase();

		if (entity.getSlogan() != null) {
			for (String element : spamArray) {

				while (slogan.indexOf(element) > -1) {
					slogan = slogan.substring(slogan.indexOf(element) + element.length(), slogan.length());
					numSpamSlogan++;
				}

			}

			sloganSpam = numSpamSlogan / entity.getSlogan().split(" ").length < threshold;
			errors.state(request, sloganSpam, "slogan", "sponsor.commercialbanner.error.spam");

		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
