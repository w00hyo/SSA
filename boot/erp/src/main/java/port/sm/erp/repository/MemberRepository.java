package port.sm.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import port.sm.erp.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

	//Object findByProviderAndProviderId(String string, String providerId);
	Optional<Member> findByProviderAndProviderId(String provider, String providerId);
}