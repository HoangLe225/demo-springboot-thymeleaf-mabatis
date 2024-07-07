package com.example.demo_spring_boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo_spring_boot.entity.Employee;

@Mapper
public interface EmployeeMapper {
	@Select("SELECT * FROM employee")
	@Results({ @Result(property = "firstName", column = "first_name"),
			@Result(property = "lastName", column = "last_name") })
	List<Employee> findAll();

	@Select("SELECT * FROM employee WHERE id = #{id}")
	@Results({ @Result(property = "firstName", column = "first_name"),
			@Result(property = "lastName", column = "last_name") })
	Employee findById(long id);

	@Insert("INSERT INTO employee(first_name, last_name, email) VALUES(#{firstName}, #{lastName}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Employee employee);

	@Update("UPDATE employee SET first_name = #{firstName}, last_name = #{lastName}, email = #{email} WHERE id = #{id}")
	void update(Employee employee);

	@Delete("DELETE FROM employee WHERE id = #{id}")
	void deleteById(long id);
	
	// Pagination
    @Select("SELECT * FROM employee LIMIT #{limit} OFFSET #{offset}")
    @Results({
    	@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name")
    })
    List<Employee> selectAllPaginated(@Param("limit") int limit, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM employee")
    long count();
}
