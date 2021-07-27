package com.example.accounting.view.departments;

import com.example.accounting.dao.orm.DepartmentOrmDao;
import com.example.accounting.model.orm.Department;
import com.example.accounting.model.orm.Employee;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Route("departments/:depId?")
public class DepartmentView extends VerticalLayout implements HasUrlParameter<Integer> {
    private final String PHONE_PATTERN = "\\+\\d\\(\\d{3}\\)\\d{3}\\-\\d{2}\\-\\d{2}";

    @Autowired
    private DepartmentOrmDao departmentOrmDao;
    private Department department;      // текущий отдел

    private final Grid<Employee> grid = new Grid<>();
    private final Button editAndSaveButton = new Button("Редактировать", VaadinIcon.PENCIL.create());

    private final TextField depName = new TextField("Отдел");
    // TODO: добавить ввод формата +7(ххх)ххх-хх-хх
    private final TextField depPhone = new TextField("Телефон");
    private final EmailField depEmail = new EmailField("Email");
    private final Select<Employee> bossSelector = new Select<>();
    private final HasEnabled[] enabledsElements = {depName, depPhone, depEmail, bossSelector};


    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        this.department = departmentOrmDao.get(parameter);
        editAndSaveButton.addClickListener(e -> switchButtonMode());

        // titleButton
        Binder<Department> binder = new Binder<>(Department.class);
        binder.forField(depName)
                .withValidator(Objects::nonNull, "ахаха тут же пусто :D")
                .withValidator(min -> min.length() >= 2, "Минимум 2 символа.")
                .withValidator(uniq ->
                                !departmentOrmDao.getAllDepartmentsName().contains(uniq),
                        "Отдел с таким названием уже существует.")
                .bind("departmentJdbc.name");
        depPhone.addValueChangeListener(
                e -> {
                    if (depPhone.isInvalid())
                        depPhone.setValue(depPhone.getValue()
                                .replaceFirst("(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                                        "$1($2)-$3-$4-$5"));
                }
        );
        binder.forField(depPhone)
                .withValidator(Objects::nonNull, "ахаха тут же пусто :D")
                .withValidator(phone -> phone.length() == 16, "Номер должен состоять из 11-ти символов")
                .bind("departmentJdbc.phone");

        configFields();

        binder.setBean(department);
        configGrid(department);
        add(depName, new HorizontalLayout(bossSelector, depPhone, depEmail), editAndSaveButton, grid);
    }

    private boolean isValid(String valid) {
        return valid != null &&
                valid.length() >= 2 && valid.length() <= 20 &&
                departmentOrmDao.getAllDepartmentsName().contains(valid);
    }

    private void switchButtonMode() {
        if (editAndSaveButton.getText().equals("Редактировать")) {
            for (HasEnabled e : enabledsElements)
                e.setEnabled(true);

            editAndSaveButton.setText("Сохранить");
            editAndSaveButton.setIcon(VaadinIcon.CHECK.create());
        } else {
            if (!(depPhone.isInvalid() || depName.isInvalid() || depEmail.isInvalid())) {
                departmentOrmDao.update(department);
                for (HasEnabled e : enabledsElements)
                    e.setEnabled(false);

                editAndSaveButton.setText("Редактировать");
                editAndSaveButton.setIcon(VaadinIcon.CHECK.create());
            }
        }
    }

    private void configFields() {
        depName.setValue(department.getDepartmentJdbc().getName());
        // bossSelector
        bossSelector.setLabel("Начальник");
        bossSelector.setItems(department.getEmployees());
        bossSelector.setValue(department.getBoss().orElse(null));
        bossSelector.addValueChangeListener(
                event1 -> department.setBoss(event1.getValue())
        );
        // depEmail
        depEmail.setValue(department.getDepartmentJdbc().getEmail());

        // depPhone
        depPhone.setPattern(PHONE_PATTERN);
        depPhone.setPlaceholder(department.getDepartmentJdbc().getPhone());
        for (HasEnabled e : enabledsElements)
            e.setEnabled(false);
    }

    private void configGrid(Department department) {
        grid.setItems(department.getEmployees());
        grid.addColumn(Employee::toString).setHeader("ФИО").setResizable(true);
        grid.addColumn(e -> e.getRank().getName()).setHeader("Должность").setResizable(true);
    }

}
