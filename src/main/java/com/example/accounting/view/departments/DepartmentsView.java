package com.example.accounting.view.departments;

import com.example.accounting.dao.CrudDao;
import com.example.accounting.model.orm.Department;
import com.example.accounting.view.AbstractCrudForm;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.Collections;
import java.util.WeakHashMap;

@Route(value = "departments")
@UIScope
@SpringComponent
public class DepartmentsView extends AbstractCrudForm<Department> {
//    private final DepartmentOrmDao departmentOrmDao;
//    private Grid<Department> departmentGrid = new Grid<>();
//    private final String href = "departments/";


    @Autowired
    public DepartmentsView(CrudDao<Integer, Department> dao, @Qualifier("departmentsForm") CreationForm<Department> form) {
        super(dao, form);
    }

    @Override
    protected void configGrid() {
        grid = new Grid<>();
        Grid.Column<Department> depName = grid
                .addColumn(d -> d.getDepartmentJdbc().getName()).setHeader("Отдел");
        Grid.Column<Department> depBoss = grid
                .addColumn(d -> d.getBoss().orElse(null)).setHeader("Босс");

        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setItems(dao.getAll());
        grid.addSelectionListener(e -> deleteButton.setEnabled(true));
        form.setGrid(grid);

    }

    @Override
    protected void configButtons() {
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(e -> {
                    ListDataProvider<Department> provider = (ListDataProvider<Department>) grid.getDataProvider();
                    grid.getSelectedItems()
                            .forEach(dep -> {
                                dao.delete(dep.getId());
                                provider.getItems().remove(dep);
                            });
                    grid.getDataProvider().refreshAll();
                }
        );
        addButton.addClickListener(e -> {
            form.open();
        });
    }
}
