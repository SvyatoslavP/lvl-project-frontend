package com.lvlMaHa.maHafrontend.view;

import com.lvlMaHa.maHafrontend.util.TabsUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("maHa")
public class MainView extends AppLayout {

    public MainView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Левобережная Волейбольная Лига МаНа | ЛВЛ МАНА");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = TabsUtil.getTabs();
        System.out.println("test");

        addToDrawer(tabs);
        addToNavbar(toggle, title);
    }
}
