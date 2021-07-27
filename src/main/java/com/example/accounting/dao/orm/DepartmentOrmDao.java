package com.example.accounting.dao.orm;

import com.example.accounting.dao.CrudDao;
import com.example.accounting.model.DepartmentJdbc;
import com.example.accounting.model.orm.Department;
import com.example.accounting.model.orm.DepartmentMapper;
import com.example.accounting.model.orm.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DepartmentOrmDao implements CrudDao<Integer, Department> {
    private static final String GET_BY_ID_SQL = "select * from department where dep_id=?";
    private static final String DELETE_BY_ID_SQL = "delete from department where dep_id = ?;";
    private static final String INSERT_SQL =
            "insert into department " +
                    "(dep_name, dep_phone, dep_email)" +
                    " values(?, ?, ?);";
    private static final String UPDATE_SQL = "update department " +
            "set dep_name=?, dep_phone=?, dep_email=?,dep_boss_id=? " +
            "where dep_id=?;";


    private final EmployeeOrmDao employeeDao;
    private final JdbcTemplate template;

    @Autowired
    public DepartmentOrmDao(EmployeeOrmDao employeeDao, DataSource dataSource) {
        this.employeeDao = employeeDao;
        template = new JdbcTemplate(dataSource);
    }

    public Set<String> getAllDepartmentsName() {
        return new HashSet<>(template.queryForList("select dep_name from department;", String.class));
    }

    @Override
    public Department get(Integer id) {
        Department department = template.queryForObject(GET_BY_ID_SQL, new DepartmentMapper(), id);
        departmentInit(department);
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = template.query("select * from department;", new DepartmentMapper());
        departments.forEach(this::departmentInit);
        return departments;
    }

    @Override
    public Optional<Department> findByName(String name) {
        Department department = template.queryForObject("select * from department where dep_name=?;", new DepartmentMapper(), name);
        departmentInit(department);
        return Optional.ofNullable(department);
    }

    private void departmentInit(Department d) {
        final List<Employee> byDepartment = employeeDao.getByDepartment(d.getDepartmentJdbc().getId());
        d.addEmployees(byDepartment);
        byDepartment.stream()
                .filter(e -> e.getId().equals(d.getDepartmentJdbc().getBossId()))
                .findFirst().ifPresent(d::setBoss);

    }

    @Override
    public void create(Department obj) {
        DepartmentJdbc core = obj.getDepartmentJdbc();
        template.update(INSERT_SQL, core.getName(), core.getPhone(), core.getEmail());
    }

    @Override
    public boolean delete(Integer id) {
        return template.update(DELETE_BY_ID_SQL, id) == 1;
    }

    @Override
    public void update(Department obj) {
        final DepartmentJdbc core = obj.getDepartmentJdbc();
        Employee boss = obj.getBoss().orElse(null);
        template.update(
                UPDATE_SQL,
                core.getName(), core.getPhone(), core.getEmail(), boss == null ? null : boss.getId(),
                core.getId());

    }
}
