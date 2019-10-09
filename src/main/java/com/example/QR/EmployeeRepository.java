package com.example.QR;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value="select * from qr where empno=?1", nativeQuery = true)
    public List<Employee> findById1(int empno);
	
	@Query(value="select * from qr where name=?1", nativeQuery = true)
    public List<Employee> findBe(String name);
	
	@Query(value="select * from qr", nativeQuery = true)
    public List<Employee> find();
	
	@Modifying
	@Transactional
	@Query(value = "update qr t set"
			+ " t.name = :name,"
			+ " t.time = :time"
			+ " where t.empno = :empno", nativeQuery = true)

    public int update(@Param("name") String name,
    		@Param("time") String time,
    		@Param("empno") int empno);
	
	@Modifying
	@Transactional
	@Query(value = "delete from qr where empno = :empno", nativeQuery = true)
    public int delete(@Param("empno") int empno);
	
	@Query(value="select * from qr where name like %?1% AND ( number like %?2% || time like %?3% ) ORDER BY empno DESC", nativeQuery = true)
    public List<Employee> findSearch(String name,String number,String time);
	/*
	@Query(value="select * from QR where name like %?1% ORDER BY empno DESC", nativeQuery = true)
    public List<Employee> findById2(String name);
	
	@Query(value="select attendance from QR where empno=?1", nativeQuery = true)
    public String findComp(int empno);
	
	
	@Modifying
	@Transactional
	@Query(value = "update QR t set"
			+ " t.name = :name,"
			+ " t.untildate = :untildate"
			+ " where t.empno = :empno", nativeQuery = true)

    public int update(@Param("name") String name,
    		@Param("untildate") String untildate,
    		@Param("empno") int empno);
	

	@Modifying
	@Transactional
	@Query(value = "update QR t set"
			+ " t.complete = :complete,"
			+ " t.color = :color"
			+ " where t.empno = :empno", nativeQuery = true)
	public int update2(
			@Param("complete") int complete,
    		@Param("color") String color,
    		@Param("empno") int empno);
    		*/
}

