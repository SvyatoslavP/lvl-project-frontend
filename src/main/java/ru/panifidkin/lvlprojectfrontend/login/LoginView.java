package ru.panifidkin.lvlprojectfrontend.login;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "login")
@RouteAlias(value = "")
@PageTitle("ЛВЛ МАНА")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Запомнить меня");

        LoginForm loginForm = new LoginForm();
        LoginI18n loginI18n = createRussianI18n();

        loginForm.setI18n(loginI18n);

        add(new H1("ЛВЛ МАНА"), loginForm, checkbox);

    }

    private LoginI18n createRussianI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Имя приложения");
        i18n.getHeader().setDescription("Описание приложения");
        i18n.getForm().setUsername("Пользователь");
        i18n.getForm().setTitle("Войдите в свой аккаунт");
        i18n.getForm().setSubmit("Авторизоваться");
        i18n.getForm().setPassword("Пароль");
        i18n.getForm().setForgotPassword("Я забыл свой пароль");
        i18n.getErrorMessage().setTitle("Неверное имя пользователя / пароль");
        return i18n;
    }
}
