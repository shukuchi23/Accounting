package com.example.accounting.view.employees;

import com.example.accounting.dao.RankDao;
import com.example.accounting.dao.orm.DepartmentOrmDao;
import com.example.accounting.dao.orm.EmployeeOrmDao;
import com.example.accounting.model.EmployeeJdbc;
import com.example.accounting.model.Rank;
import com.example.accounting.model.orm.Employee;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@UIScope
@SpringComponent("employeeForm")
public class EmployeeForm extends CreationForm<Employee> {
    private final TextField lastname = new TextField("Фамилия");
    private final TextField firstname = new TextField("Имя");
    private final TextField patronymic = new TextField("Отчество");

    private final Select<String> departmentSelect = new Select<>();
    private final Select<Rank> rankSelect = new Select<>();

    private final Employee temp = new Employee();

    @Autowired
    public EmployeeForm(EmployeeOrmDao employeeOrmDao,
                        DepartmentOrmDao departmentOrmDao,
                        RankDao rankDao) {
        super(employeeOrmDao);
        EmployeeJdbc core = temp.getEmployeeJdbc();
        lastname.setPlaceholder("Иванов");
        lastname.addValueChangeListener(e -> core.setLastName(e.getValue()));
        firstname.setPlaceholder("Иван");
        firstname.addValueChangeListener(e -> core.setFirstName(e.getValue()));
        patronymic.setPlaceholder("Иванович");
        patronymic.addValueChangeListener(e -> core.setPatronymic(e.getValue()));
        temp.setEmployeeJdbc(core);
        final List<Employee> all = employeeOrmDao.getAll();
        final Set<String> departments = departmentOrmDao.getAllDepartmentsName();

        departmentSelect.setLabel("Отдел");
        departmentSelect.setItems(departments);
        departmentSelect.setValue(departments.stream().findFirst().orElse("тут пусто"));
        departmentSelect.addValueChangeListener(
                e -> temp.setDepartment(departmentOrmDao.findByName(e.getValue()).orElse(null)));

        // печально, если отделов ещё не существует
        final List<Rank> ranks = rankDao.getAll();
        rankSelect.setLabel("Должность");
        rankSelect.setItems(ranks);
        rankSelect.setValue(ranks != null ? ranks.get(0) : null);
        rankSelect.addValueChangeListener(e -> temp.setRank(e.getValue()));

        setComponents(Arrays.asList(lastname, firstname, patronymic, departmentSelect, rankSelect));
        setSaveListener(() -> employeeOrmDao.create(temp));
        saveButton.setEnabled(true);
        build();
    }
}
