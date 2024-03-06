package ru.panifidkin.lvlprojectfrontend.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import ru.panifidkin.lvlprojectfrontend.dto.Team;
import ru.panifidkin.lvlprojectfrontend.view.MainNavigation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Route(value = "table", layout = MainNavigation.class)
@Theme("my-theme")
public class Table extends Div implements AppShellConfigurator {
    private final static String COLUMN_NAME_TEAM = "Название";
    private final static String COLUMN_FOUNDER_TEAM = "Учредитель";
    private final static String COLUMN_RATING_TEAM = "Рейтинг";
    private final static String COLUMN_BIRTHDAY_TEAM = "Дата образования";
    private final static String COLUMN_STATUS_TEAM = "Статус";

    private final static String PLATINUM_SUPER_DIVISION_TABLE_ID = "rating-table-platinum-super-division-id";
    private final static String GOLD_TOP_DIVISION_TABLE_ID = "rating-table-gold-top-division-id";
    private final static String SILVER_PREMIER_DIVISION_TABLE_ID = "rating-table-silver-premier-division-id";
    private final static String BRONZE_LITE_START_TABLE_ID = "rating-table-bronze-lite-start-id";

    public Table() {
        Div container = new Div(getGrid(PLATINUM_SUPER_DIVISION_TABLE_ID),
                getGrid(GOLD_TOP_DIVISION_TABLE_ID));
        container.setId("rating-tables");

        Div container2 = new Div(
                getGrid(SILVER_PREMIER_DIVISION_TABLE_ID),
                getGrid(BRONZE_LITE_START_TABLE_ID));
        container2.setId("rating-tables2");

        add(container, container2);
    }

    private Grid<Team> getGrid(String tableId) {
        //настройки таблицы
        Grid<Team> grid = new Grid<>(Team.class, false);
        grid.addColumn(Team::getName).setHeader(COLUMN_NAME_TEAM);
        grid.addColumn(Team::getFounder).setHeader(COLUMN_FOUNDER_TEAM);
        grid.addColumn(Team::getRating).setHeader(COLUMN_RATING_TEAM);
        grid.addColumn(team -> getFormattedTeamBirthday(team))
                .setTooltipGenerator(team -> "Играют в лиге: " + getPersonAge(team))
                .setHeader(COLUMN_BIRTHDAY_TEAM);
        grid.addComponentColumn(team -> createStatusIcon(team.getStatus()))
                .setTooltipGenerator(team -> team.getStatus())
                .setHeader(COLUMN_STATUS_TEAM);
        //получить список команд
        List<Team> teams = new ArrayList<>();
        teams.add(Team.builder()
                .name("Огонь")
                .founder("Пупкин Василий Петрович")
                .status("Не согласованно")
                .rating("123")
                .birthday(LocalDate.of(1991, 3, 2))
                .build());
        teams.add(Team.builder()
                .name("Вода")
                .founder("Вася Васильев")
                .status("Согласованно")
                .rating("124")
                .birthday(LocalDate.of(1992, 3, 2))
                .build());
        grid.setItems(teams);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setId(tableId);
        return grid;
    }

    private static final DateTimeFormatter birthdayFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");

    private int getPersonAge(Team team) {
        LocalDate birthday = team.getBirthday();
        return LocalDate.now(ZoneId.systemDefault()).getYear()
                - birthday.getYear();
    }

    private String getFormattedTeamBirthday(Team team) {
        LocalDate birthday = team.getBirthday();
        return birthday.format(birthdayFormatter);
    }

    private Icon createStatusIcon(String status) {
        boolean isAvailable = "Согласованно".equals(status);
        Icon icon;
        if (isAvailable) {
            icon = VaadinIcon.CHECK.create();
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = VaadinIcon.CLOSE_SMALL.create();
            icon.getElement().getThemeList().add("badge error");
        }
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

}
