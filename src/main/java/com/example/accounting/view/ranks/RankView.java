package com.example.accounting.view.ranks;

import com.example.accounting.dao.CrudDao;
import com.example.accounting.model.Rank;
import com.example.accounting.view.AbstractCrudForm;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Route("ranks")
@SpringComponent
@UIScope
public class RankView extends AbstractCrudForm<Rank> {
    @Autowired
    public RankView(@Qualifier("rankDao") CrudDao<Integer, Rank> rankDao, @Qualifier("rankForm") CreationForm<Rank> form) {
        super(rankDao, form);
    }

    @Override
    protected void configGrid() {
        grid = new Grid<>(Rank.class);
        grid.setItems(dao.getAll());
        grid.setColumns("id", "name", "salary");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(e -> deleteButton.setEnabled(true));
        form.setGrid(grid);
    }

    @Override
    protected void configButtons() {
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(e -> {
                    ListDataProvider<Rank> provider = (ListDataProvider<Rank>) grid.getDataProvider();
                    grid.getSelectedItems()
                            .forEach(rank -> {
                                dao.delete(rank.getId());
                                provider.getItems().remove(rank);
                            });
                    grid.getDataProvider().refreshAll();
                }
        );

        addButton.addClickListener(e -> {
            form.open();
        });
    }
}
