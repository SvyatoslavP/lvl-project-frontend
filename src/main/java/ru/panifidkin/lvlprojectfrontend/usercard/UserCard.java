package ru.panifidkin.lvlprojectfrontend.usercard;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import ru.panifidkin.lvlprojectfrontend.view.MainNavigation;

import java.time.LocalDate;

@Route(value = "user-card", layout = MainNavigation.class)
public class UserCard extends VerticalLayout {

    public static final String PERSONAL_TITLE_ID = "personal-title-id";
    public static final String ACCOUNTING_TITLE_ID = "accounting-title-id";
    public static final String USER_CARD_ID = "user-card-id";

    public UserCard() {
        setId(USER_CARD_ID);
        Section personalInformation = getPersonalInformation();
        Section accountingInformation = getAccountingInformation();

        Div container = new Div(personalInformation, accountingInformation);
        add(container);

        // Footer
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getStyle().set("margin-right", "var(--lumo-space-s)");

        Button reset = new Button("Reset");
        reset.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Footer footer = new Footer(save, reset);
        footer.getStyle().set("padding", "var(--lumo-space-wide-m)");
        add(footer);

    }

    private Section getPersonalInformation() {
        H3 personalTitle = new H3("Персональная информация");
        personalTitle.setId(PERSONAL_TITLE_ID);
        TextField firstName = new TextField("Имя");
        firstName.setWidthFull();
        TextField lastName = new TextField("Фамилия");
        lastName.setWidthFull();
        TextField secondName = new TextField("Отчество");
        secondName.setWidthFull();
        DatePicker birthDate = new DatePicker("День рождения");
        birthDate.setInitialPosition(LocalDate.of(2000, 1, 1));
        birthDate.setWidthFull();
        Section personalInformation = new Section(personalTitle, firstName, lastName, secondName, birthDate);
        personalInformation.getElement().setAttribute("aria-labelledby", PERSONAL_TITLE_ID);
        return personalInformation;
    }

    private Section getAccountingInformation() {
        H3 accountingTitle = new H3("Информация учетной записи");
        accountingTitle.setId(ACCOUNTING_TITLE_ID);
        TextField login = new TextField("Логин");
        login.setAllowedCharPattern("[A-Za-z0-9]");
        login.setWidthFull();
        login.setMinLength(6);
        login.setMaxLength(20);
        login.setHelperText("Только латинские символы A-Z или числа. От 6 до 20 символов.");
        PasswordField password = new PasswordField("Пароль");
        password.setWidthFull();
        password.setRequiredIndicatorVisible(true);
        password.setAllowedCharPattern("[A-Za-z0-9]");
        password.setMinLength(6);
        password.setMaxLength(20);
        password.setHelperText("Только латинские символы A-Z или числа. От 6 до 20 символов.");
        Section accountingInformation = new Section(accountingTitle, login, password);
        accountingInformation.getElement().setAttribute("aria-labelledby", ACCOUNTING_TITLE_ID);
        return accountingInformation;
    }

}
