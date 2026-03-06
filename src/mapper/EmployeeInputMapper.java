package mapper;

import constants.Messages;
import enums.Status;
import enums.Update;
import lib.InputUtility;
import model.Employee;
import validation.Validator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EmployeeInputMapper {
    public static final Map<Update, Consumer<Employee>> UPDATE_ACTION_MAPPER = new LinkedHashMap<>();

    static {
        UPDATE_ACTION_MAPPER.put(Update.NAME, emp -> emp.setName(InputUtility.getValidInput(Messages.Prompt.NAME_UPDATE, Validator::stringNotBlank, Messages.Error.INVALID_NAME)));
        UPDATE_ACTION_MAPPER.put(Update.EMAIL, emp -> emp.setEmail(InputUtility.getValidInput(Messages.Prompt.EMAIL_UPDATE, Validator::isEmailValid, Messages.Error.INVALID_EMAIL)));
        UPDATE_ACTION_MAPPER.put(Update.PHONE, emp -> emp.setPhone(InputUtility.getValidInput(Messages.Prompt.PHONE_UPDATE, Validator::isPhoneValid, Messages.Error.INVALID_PHONE)));
        UPDATE_ACTION_MAPPER.put(Update.STATUS, emp -> emp.setStatus(InputUtility.getChoice(Status.class)));
        UPDATE_ACTION_MAPPER.put(Update.SALARY, emp -> emp.setSalary(InputUtility.getNumber(Messages.Prompt.SALARY_UPDATE, Double::parseDouble)));
        UPDATE_ACTION_MAPPER.put(Update.DIVISION, emp -> emp.setDivision(InputUtility.getValidInput(Messages.Prompt.DIVISION_UPDATE, Validator::stringNotBlank, Messages.Error.INVALID_DIVISION)));
        UPDATE_ACTION_MAPPER.put(Update.JOIN_DATE, emp -> emp.setJoinDate(InputUtility.getDate(Messages.Prompt.JOIN_DATE_UPDATE)));
    }
}
