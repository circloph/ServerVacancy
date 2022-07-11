package net.thumbtack.school.hiring.server;

import net.thumbtack.school.hiring.daoimpl.PersonDaoImpl;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.service.EmployeeService;
import net.thumbtack.school.hiring.service.EmployerService;
import net.thumbtack.school.hiring.service.LoginService;
import net.thumbtack.school.hiring.exception.ServerException;

import java.io.*;

public class Server {
    private PersonDaoImpl personDao;
    private EmployeeService employeeService;
    private EmployerService employerService;
    private LoginService loginService;
    private DataBase dataBase;

    public Server() {
    }

    public PersonDaoImpl getPersonDao() {
        return personDao;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public EmployerService getEmployerService() {
        return employerService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void startServer(String savedDataFileName) throws IOException {
        File file = new File(savedDataFileName);
        if (file.exists() && file.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                dataBase = (DataBase) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            dataBase = new DataBase();
        }
        personDao = new PersonDaoImpl(dataBase);
        employeeService = new EmployeeService(personDao);
        employerService = new EmployerService(personDao);
        loginService = new LoginService(personDao);
    }


    public void stopServer(String savedDataFileName) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(savedDataFileName))) {
            objectOutputStream.writeObject(dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String registerEmployee(String requestJsonString)   {
        return employeeService.registerEmployee(requestJsonString);
    }

    public String addSummary(String requestJsonString) {
        return employeeService.addSummary(requestJsonString);
    }

    public String getVacancy(String requestJsonString)   {
        return employeeService.getVacancy(requestJsonString);
    }

    public String getVacancyWithRequire(String requestJsonString)   {
        return employeeService.getVacancyWithRequire(requestJsonString);
    }


    public String getVacancyAllLevel(String requestJsonString)   {
        return employeeService.getVacancyAllLevel(requestJsonString);
    }

    public String getVacancyOnlyOneRequirement(String requestJsonString)   {
        return employeeService.getVacancyOnlyOneRequirement(requestJsonString);
    }

    public String editDataEmployee(String requestJsonString)   {
        return employeeService.editDataEmployee(requestJsonString);
    }

    public String editSummary(String requestJsonString) throws ServerException {
        return employeeService.editSummary(requestJsonString);
    }

    public String makeActive(String requestJsonString)   {
        return employeeService.makeActive(requestJsonString);
    }

    public String makeInactive(String requestJsonString)   {
        return employeeService.makeInactive(requestJsonString);
    }

    public String registerEmployer(String requestJsonString) throws ServerException {
        return employerService.registerEmployer(requestJsonString);
    }

    public String addVacancy(String requestJsonString)   {
        return employerService.addVacancy(requestJsonString);
    }

    public String getSummary(String requestJsonString) throws ServerException {
        return employerService.getSkill(requestJsonString);
    }

    public String getSkill(String requestJsonString) throws ServerException {
        return employerService.getSkill(requestJsonString);
    }

    public String getSummaryAllLevel(String requestJsonString) throws ServerException {
        return employerService.getSkillAllLevel(requestJsonString);
    }

    public String getSummaryOnlyOneRequirement(String requestJsonString) throws ServerException {
        return employerService.getSummaryOnlyOneRequirement(requestJsonString);
    }

    public String getSummaryWithRequire(String requestJsonString) throws ServerException {
        return employerService.getSkillWithRequire(requestJsonString);
    }

    public String editDataEmployer(String requestJsonString)   {
        return employerService.editDataEmployer(requestJsonString);
    }

    public String editVacancy(String requestJsonString)   {
        return employerService.editVacancy(requestJsonString);
    }

    public String makeVacancyActive(String requestJsonString)   {
        return employerService.makeVacancyActive(requestJsonString);
    }

    public String makeVacancyInactive(String requestJsonString)   {
        return employerService.makeVacancyInactive(requestJsonString);
    }

    public String getAllVacancy(String requestJsonString) throws ServerException {
        return employerService.getAllVacancy(requestJsonString);
    }

    public String getVacancyOnlyActive(String requestJsonString)   {
        return employerService.getVacancyOnlyActive(requestJsonString);
    }

    public String getVacancyOnlyInactive(String requestJsonString)   {
        return employerService.getVacancyOnlyInactive(requestJsonString);
    }

    public String login(String requestJsonString) {
        return loginService.login(requestJsonString);
    }

    public void logout(String token) throws ServerException {
        loginService.logout(token);
    }
}
