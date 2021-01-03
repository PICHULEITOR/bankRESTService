package pe.com.jorgeberrios.crud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.jorgeberrios.crud.entity.PaymentOrder;

@Repository
public interface PaymentOrderDao extends JpaRepository<PaymentOrder,Long>{
	PaymentOrder findByCode(String code);
	void deleteByCode(String code);
	boolean existsByCode(String code);
	@Query(value="SELECT id FROM payment_order WHERE code= :code",nativeQuery=true)
	Long findIdByCode(@Param("code") String code);
	@Query(value="SELECT P from payment_order P INNER JOIN branch_office B ON P.branch_office_id=B.id where B.code= :branchOfficeCode AND P.currency= :currencyType",nativeQuery=true)
	List<PaymentOrder> findByBranchOfficeCodeAndCurrencyType(@Param("branchOfficeCode")String branchOfficeCode,@Param("currencyType")String currencyType);
	
}
