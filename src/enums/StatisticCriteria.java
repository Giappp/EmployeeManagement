package enums;

import constants.Messages;

public enum StatisticCriteria implements ChoiceMapper {
    TOTAL_EMPLOYEE(1, Messages.Prompt.STATS_TOTAL_EMPLOYEE),
    TOTAL_SALARY(2, Messages.Prompt.STATS_TOTAL_SALARY),
    AVG_SALARY(3, Messages.Prompt.STATS_AVG_SALARY),
    MAX_SALARY(4, Messages.Prompt.STATS_MAX_SALARY),
    TOP_3(5, Messages.Prompt.STATS_TOP_3),
    GROUP_BY_DIVISION(6, Messages.Prompt.STATS_GROUP_BY_DIVISION),
    COUNT_ACTIVE(7, Messages.Prompt.STATS_COUNT_ACTIVE);

    private final Integer choice;
    private final String displayName;

    StatisticCriteria(Integer choice, String displayName) {
        this.choice = choice;
        this.displayName = displayName;
    }

    @Override
    public Integer getChoice() {
        return choice;
    }

    @Override
    public String getName() {
        return displayName;
    }
}
