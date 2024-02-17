package ru.panifidkin.lvlprojectfrontend.util;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class TabsUtil {

    public static Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.GROUP, "Команды"),
                createTab(VaadinIcon.BAR_CHART_H, "Таблица"),
                createTab(VaadinIcon.FOLDER_OPEN, "Фото"),
                createTab(VaadinIcon.CALENDAR, "Расписание"),
                createTab(VaadinIcon.USER_CARD, "Игроки"),
                createTab(VaadinIcon.YOUTUBE, "YouTube"),
                createTab(VaadinIcon.HANDSHAKE, "Контакты"),
                createTab(VaadinIcon.COGS, "Настройки")
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }
    public static Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        // link.setRoute(viewClass.java);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}
