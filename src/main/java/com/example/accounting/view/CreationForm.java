package com.example.accounting.view;

import com.example.accounting.dao.CrudDao;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.LinkedList;
import java.util.List;

@UIScope
public abstract class CreationForm<BEAN> extends Dialog {
    private final CrudDao dao;
    private final Text formText = new Text("Заполните все поля перед сохранением!");
    private final List<Component> fields = new LinkedList<>();
    protected final Button saveButton = new Button("Сохранить", VaadinIcon.CHECK.create());
    protected final Button cancelButton = new Button("Отменить");
    VerticalLayout dialogForm = new VerticalLayout();
    protected Grid<BEAN> grid;

    public CreationForm(CrudDao dao) {
        this.dao = dao;
        saveButton.setEnabled(false);
        fields.add(formText);
    }

    public void setComponents(List<Component> components) {
        fields.addAll(components);
    }

    public void setSaveListener(Runnable e) {

        saveButton.addClickListener(c -> {
            e.run();
            grid.getDataProvider().refreshAll();
            close();
        });
    }


    public void setGrid(Grid<BEAN> grid) {
        this.grid = grid;
    }

    public void build() {
        cancelButton.addClickListener(e -> close());
        int size = fields.size();
        Component[] c = new Component[size];
        for (int i = 0; i < size; i++)
            c[i] = fields.get(i);
        dialogForm.add(c);
        add(dialogForm, saveButton, cancelButton);
    }


}
