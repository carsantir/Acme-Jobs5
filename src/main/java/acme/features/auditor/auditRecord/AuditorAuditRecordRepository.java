
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.id=?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select ar from AuditRecord ar where ar.job.id=?1 and ar.draft=false")
	Collection<AuditRecord> findManyActiveByJobId(int jobId);

	@Query("select id from Auditor a where a.enabled=0")
	Collection<Integer> findOneAuditorByEnabled();

}
