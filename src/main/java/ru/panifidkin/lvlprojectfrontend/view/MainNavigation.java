package ru.panifidkin.lvlprojectfrontend.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
@PageTitle("ЛВЛ МАНА")
public class MainNavigation extends AppLayout {

    public MainNavigation() {

        SideNav views = getPrimaryNavigation();

        Scroller scroller = new Scroller(views);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        DrawerToggle toggle = new DrawerToggle();

        HorizontalLayout subViews = getSecondaryNavigation();
        subViews.getElement();

        HorizontalLayout wrapper = new HorizontalLayout(toggle, getViewTitle());
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSpacing(false);

        VerticalLayout viewHeader = new VerticalLayout(wrapper, subViews);
        viewHeader.setPadding(false);
        viewHeader.setSpacing(false);

        addToDrawer(getAppTitle(), scroller);
        addToNavbar(viewHeader);

        setPrimarySection(Section.DRAWER);
    }

    private H1 getAppTitle() {
        H1 appTitle = new H1("ЛВЛ МАНА");
        appTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");
        return appTitle;
    }

    private H2 getViewTitle() {
        H2 viewTitle = new H2("Левобережная Волейбольная Лига");
        viewTitle.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        return viewTitle;
    }

    private SideNav getPrimaryNavigation() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Профиль", "/user-card",
                        VaadinIcon.USER_CARD.create()),
                new SideNavItem("Таблица", "/table",
                        VaadinIcon.TABLE.create()),
                new SideNavItem("Команды", "/orders",
                        VaadinIcon.USERS.create()),
                new SideNavItem("Рейтинг", "/customers",
                        VaadinIcon.CHART_LINE.create()),
                new SideNavItem("Статистика", "/products",
                        VaadinIcon.CHART_GRID.create()),
                new SideNavItem("Фото", "/photos",
                        VaadinIcon.PICTURE.create()),
                new SideNavItem("You Tube", "/video",
                        VaadinIcon.YOUTUBE.create())
        );
        return sideNav;
    }

    private HorizontalLayout getSecondaryNavigation() {
        HorizontalLayout navigation = new HorizontalLayout();
        navigation.addClassNames(LumoUtility.JustifyContent.CENTER,
                LumoUtility.Gap.SMALL, LumoUtility.Height.MEDIUM);
        navigation.add(
                createLink("Новости"),
                createLink("Фото"),
                createLink("Контакты"),
                createLink("FAQ")
        );
        return navigation;
    }

    private RouterLink createLink(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        // Demo has no routes
        // link.setRoute(viewClass.java);

        link.addClassNames(LumoUtility.Display.FLEX,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.Padding.Horizontal.MEDIUM,
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.FontWeight.MEDIUM);
        link.getStyle().set("text-decoration", "none");

        return link;
    }

}