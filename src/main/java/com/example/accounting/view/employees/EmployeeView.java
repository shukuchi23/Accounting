package com.example.accounting.view.employees;

import com.example.accounting.dao.CrudDao;
import com.example.accounting.model.orm.Employee;
import com.example.accounting.view.AbstractCrudForm;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Route("employees")
@UIScope
@SpringComponent
public class EmployeeView extends AbstractCrudForm<Employee> {
    @Autowired
    public EmployeeView(CrudDao<Integer, Employee> dao, @Qualifier("employeeForm") CreationForm<Employee> form) {
        super(dao, form);
    }

    @Override
    protected void configGrid() {
        grid = new Grid<>();
        grid.setItems(dao.getAll());
        grid.addColumn(Employee::getId).setHeader("ID").setResizable(true);

        grid.addColumn(
                emp -> String.format("%s %.1s. %.1s.", emp.getLastName(), emp.getFirstName(), emp.getPatronymic())
        ).setHeader("Ф.И.О.").setResizable(true);

        grid.addColumn(Employee::getDepartment).setHeader("Отдел").setResizable(true);
        grid.addColumn(Employee::getRank).setHeader("Должность").setResizable(true);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(e -> deleteButton.setEnabled(true));
        form.setGrid(grid);
    }

    @Override
    protected void configButtons() {
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(e -> {
                    ListDataProvider<Employee> provider = (ListDataProvider<Employee>) grid.getDataProvider();
                    grid.getSelectedItems()
                            .forEach(emp -> {
                                dao.delete(emp.getId());
                                provider.getItems().remove(emp);
                            });
                    grid.getDataProvider().refreshAll();
                }
        );
        addButton.addClickListener(e -> {
            form.open();
        });
    }

}
