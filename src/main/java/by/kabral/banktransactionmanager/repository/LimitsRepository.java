package by.kabral.banktransactionmanager.repository;

import by.kabral.banktransactionmanager.model.Limit;
import by.kabral.banktransactionmanager.util.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimitsRepository extends JpaRepository<Limit, UUID> {
  Limit findFirstByTypeOrderByDatetimeDesc(ExpenseCategory type);
}
