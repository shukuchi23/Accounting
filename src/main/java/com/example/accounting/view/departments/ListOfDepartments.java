package com.example.accounting.view.departments;

import com.example.accounting.dao.orm.DepartmentOrmDao;
import com.example.accounting.model.DepartmentJdbc;
import com.example.accounting.model.orm.Department;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class ListOfDepartments extends HorizontalLayout {
    private final DepartmentOrmDao departmentOrmDao;
    private final NativeButton nativeButton = new NativeButton("Перейти к отделу");
    private final Select<String> selector = new Select<>();

    @Autowired
    public ListOfDepartments(DepartmentOrmDao dao) {
        departmentOrmDao = dao;

        final List<DepartmentJdbc> collect = departmentOrmDao.getAll().stream()
                .map(Department::getDepartmentJdbc)
                .collect(Collectors.toList());

        Map<String, String> argumentDescriptor = new HashMap<>(collect.size() + 1);
        argumentDescriptor.put("ALL", "");
        collect.forEach(d -> argumentDescriptor.put(d.getName(), d.getId().toString()));

        selector.setLabel("Отделы");
        selector.setItems(argumentDescriptor.keySet());
        selector.setValue("ALL");
        nativeButton.addClickListener(
                e -> nativeButton
                        .getUI()
                        .ifPresent(ui -> ui
                                .navigate("departments/" + argumentDescriptor.get(selector.getValue())))
        );
        add(selector, nativeButton);
    }

}
