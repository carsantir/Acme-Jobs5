
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select c from CommercialBanner c where c.id=?1")
	CommercialBanner findOneById(int commercialBannerId);

	@Query("select c from CommercialBanner c where c.sponsor.id=?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

}
