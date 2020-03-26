package com.spring.jpa.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<loginInfo, Integer>{
	Long countByIddAndPasswd(String idd,String passwd);	
	List<loginInfo> findByIddAndPasswd(String idd,String passwd);
	
//	@Query(value = "select * from logininfo a where a.name = :name and a.passwd= :passwd")
//	List<loginInfo> findByIddAndPasswd(@Param("idd") String idd,@Param("passwd") String passwd);
}
