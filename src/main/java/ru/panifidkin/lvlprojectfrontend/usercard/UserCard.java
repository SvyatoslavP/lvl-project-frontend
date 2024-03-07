package ru.panifidkin.lvlprojectfrontend.usercard;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import ru.panifidkin.lvlprojectfrontend.view.MainNavigation;

import java.time.LocalDate;

@Route(value = "user-card", layout = MainNavigation.class)
public class UserCard extends VerticalLayout {

    public static final String PERSONAL_TITLE_ID = "personal-title-id";
    public static final String ACCOUNTING_TITLE_ID = "accounting-title-id";
    public static final String USER_CARD_ID = "user-card-id";

    private Icon checkIcon;
    private Span passwordStrengthText;

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
        password.setAllowedCharPattern("[A-Za-z0-9]");
        password.setMinLength(6);
        password.setMaxLength(20);

        checkIcon = VaadinIcon.CHECK.create();
        checkIcon.setVisible(false);
        checkIcon.getStyle().set("color", "var(--lumo-success-color)");
        password.setSuffixComponent(checkIcon);

        Div passwordStrength = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text("Надежность пароля: "), passwordStrengthText);
        password.setHelperComponent(passwordStrength);

        password.setValueChangeMode(ValueChangeMode.EAGER);
        password.addValueChangeListener(e -> {
            String pass = e.getValue();
            updateHelper(pass);
        });

        updateHelper("");

        Section accountingInformation = new Section(accountingTitle, login, password);
        accountingInformation.getElement().setAttribute("aria-labelledby", ACCOUNTING_TITLE_ID);
        return accountingInformation;
    }

    private void updateHelper(String pass) {
        if (pass.length() > 12) {
            passwordStrengthText.setText("Надежный");
            passwordStrengthText.getStyle().set("color", "var(--lumo-success-color)");
            checkIcon.setVisible(true);
        } else if (pass.length() > 6) {
            passwordStrengthText.setText("Не достаточно надежный");
            passwordStrengthText.getStyle().set("color", "#e7c200");
            checkIcon.setVisible(false);
        } else {
            passwordStrengthText.setText("Не надежный");
            passwordStrengthText.getStyle().set("color", "var(--lumo-error-color)");
            checkIcon.setVisible(false);
        }
    }

}
