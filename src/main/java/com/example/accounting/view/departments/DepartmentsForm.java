package com.example.accounting.view.departments;

import com.example.accounting.dao.orm.DepartmentOrmDao;
import com.example.accounting.model.DepartmentJdbc;
import com.example.accounting.model.orm.Department;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@UIScope
@SpringComponent("departmentsForm")
public class DepartmentsForm extends CreationForm<Department> {
    private final TextField depName = new TextField("Отдел");
    private final TextField depPhone = new TextField("Телефон");
    private final TextField depEmail = new TextField("Email");

    private final Department temp = new Department();

    @Autowired
    public DepartmentsForm(DepartmentOrmDao dao) {
        super(dao);
        DepartmentJdbc core = temp.getDepartmentJdbc();

        depName.setPlaceholder("Название отдела");
        depName.addValueChangeListener(e -> core.setName(e.getValue()));
        depPhone.addValueChangeListener(e -> {
            if (depPhone.isInvalid())
                depPhone.setValue(depPhone.getValue()
                        .replaceFirst("(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                                "$1($2)-$3-$4-$5"));
            core.setPhone(e.getValue());
        });

        depEmail.addValueChangeListener(e -> core.setEmail(e.getValue()));
        temp.setDepartmentJdbc(core);

        saveButton.setEnabled(true);
        setComponents(Arrays.asList(depName, depPhone, depEmail));
        setSaveListener(() -> dao.create(temp));
        build();
    }
}
