package com.example.accounting.view;

import com.example.accounting.dao.orm.DepartmentOrmDao;
import com.example.accounting.model.orm.Department;
import com.example.accounting.view.departments.ListOfDepartments;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route
public class MainView extends HorizontalLayout {

    private final DepartmentOrmDao dao;
    private final ListOfDepartments listOfDepartments;

    @Autowired
    public MainView(DepartmentOrmDao dao, ListOfDepartments list) {
        this.dao = dao;
        this.listOfDepartments = list;

        final List<Department> all = dao.getAll();
        Anchor employeeForwardButton = new Anchor("employees", "Сотрудники");
        Anchor rankForwardButton = new Anchor("ranks", "Должности");

        add(employeeForwardButton, rankForwardButton, listOfDepartments);
    }


}
