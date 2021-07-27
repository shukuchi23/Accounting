package com.example.accounting.dao.orm;

import com.example.accounting.dao.CrudDao;
import com.example.accounting.model.orm.Employee;
import com.example.accounting.model.orm.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeOrmDao implements CrudDao<Integer, Employee> {
    private static final String EMPLOYEE_FROM_CUR_DEPARTMENT
            = "select * \n" +
            "from employee\n" +
            "inner join department on dep_id = emp_dep_id\n" +
            "inner join rank on rank_id = emp_rank_id\n" +
            "where dep_id = ?;";

    private static final String ALL_EMPLOYEES_SQL
            = "select * \n" +
            "from employee\n" +
            "inner join department on dep_id = emp_dep_id\n" +
            "inner join rank on rank_id = emp_rank_id;";

    private static final String EMPLOYEE_BY_ID
            = "select * \n" +
            "from employee\n" +
            "inner join department on dep_id = emp_dep_id\n" +
            "inner join rank on rank_id = emp_rank_id\n" +
            "where emp_id = ?;";

    private static final String GET_BY_ID_SQL = "select * from employee where emp_id = ?;";

    private static final String DELETE_BY_ID_SQL = "delete from employee where emp_id = ?;";

    private static final String INSERT_SQL = "insert into employee " +
            "(emp_lastname, emp_firstname, emp_patronymic, emp_dep_id, emp_rank_id)" +
            " values(?, ?, ?, ?, ?);";

    private static final String UPDATE_SQL = "update employee " +
            "set emp_lastname=?, emp_firstname=?, emp_patronymic=?, emp_dep_id=?, emp_rank_id=? " +
            "where emp_id=?;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeOrmDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getByDepartment(Integer id) {
        return jdbcTemplate.query(EMPLOYEE_FROM_CUR_DEPARTMENT, new EmployeeMapper(), id);
    }

    @Override
    public Employee get(Integer id) {
        return jdbcTemplate.queryForObject(EMPLOYEE_BY_ID, new EmployeeMapper(), id);
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query(ALL_EMPLOYEES_SQL, new EmployeeMapper());
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from employee where emp_lastname = ?", new EmployeeMapper(), name));
    }

    @Override
    public void create(Employee obj) {
        jdbcTemplate.update(INSERT_SQL,
                obj.getLastName(), obj.getFirstName(), obj.getPatronymic(),
                obj.getDepartment().getId(), obj.getRank().getId());
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, id) == 1;
    }

    @Override
    public void update(Employee obj) {
        jdbcTemplate.update(UPDATE_SQL,
                obj.getLastName(), obj.getFirstName(), obj.getPatronymic(),
                obj.getDepartment().getId(), obj.getRank().getId(), obj.getId());
    }
}
