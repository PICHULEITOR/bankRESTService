package pe.com.jorgeberrios.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.com.jorgeberrios.crud.entity.BranchOffice;

@Repository
public interface BranchOfficeDao extends JpaRepository<BranchOffice,Long> {
	BranchOffice findByCode(String code);
	void deleteByCode(String code);
	boolean existsByCode(String code);
	@Query(value="SELECT id FROM branch_office WHERE code= :code",nativeQuery=true)
	Long findIdByCode(@Param("code") String code);
}
