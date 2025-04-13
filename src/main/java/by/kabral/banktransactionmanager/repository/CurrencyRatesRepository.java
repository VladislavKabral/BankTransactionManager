package by.kabral.banktransactionmanager.repository;

import by.kabral.banktransactionmanager.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CurrencyRatesRepository extends JpaRepository<CurrencyRate, UUID> {
  Optional<CurrencyRate> findFirstByTargetOrderByDatetimeDesc(String target);
}
