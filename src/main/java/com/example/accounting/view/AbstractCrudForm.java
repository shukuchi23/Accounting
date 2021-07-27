package com.example.accounting.view;

import com.example.accounting.dao.CrudDao;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
public abstract class AbstractCrudForm<BEAN> extends VerticalLayout {
    protected final Button deleteButton = new Button("Удалить", VaadinIcon.TRASH.create());
    protected final Button addButton = new Button("Добавить", VaadinIcon.PLUS.create());
    private final HorizontalLayout addAndDeleteLayout = new HorizontalLayout(addButton, deleteButton);
    protected final NativeButton forwardToMainView = new NativeButton("На главную");

    protected final CrudDao<Integer, BEAN> dao;
    protected final CreationForm<BEAN> form;

    protected Grid<BEAN> grid = new Grid<>();

    public AbstractCrudForm(CrudDao<Integer, BEAN> dao_bean, CreationForm<BEAN> form) {
        dao = dao_bean;
        this.form = form;
        configGrid();
        configButtons();
        forwardToMainView.addClickListener(e -> forwardToMainView.getUI()
                .ifPresent(ui -> ui
                        .navigate("")));
        add(addAndDeleteLayout, grid, forwardToMainView);
    }


    protected void configGrid() {
    }

    protected void configButtons() {
    }

}