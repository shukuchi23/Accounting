package com.example.accounting.view.ranks;

import com.example.accounting.dao.RankDao;
import com.example.accounting.model.Rank;
import com.example.accounting.view.CreationForm;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringComponent("rankForm")
public class RankForm extends CreationForm<Rank> {
    private final TextField name = new TextField("Должность");
    private final TextField salary = new TextField("Зарплата");
    private final Rank temp = new Rank();

    @Autowired
    public RankForm(RankDao dao) {

        super(dao);
        name.setPlaceholder("название должности");
        salary.setPlaceholder("10000.00");
        salary.setPattern("[1-9]\\d+(\\.\\d{1,2})?");

        name.addValueChangeListener(t -> temp.setName(name.getValue()));
        salary.addValueChangeListener(t -> {
                    if (!salary.isInvalid()) {
                        temp.setSalary(new BigDecimal(salary.getValue()));
                        saveButton.setEnabled(true);
                    } else
                        saveButton.setEnabled(false);
                }
        );
        setComponents(Arrays.asList(name, salary));
        setSaveListener(() -> dao.create(temp));

        build();
    }


}
