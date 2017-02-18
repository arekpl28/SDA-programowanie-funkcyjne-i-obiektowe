package sda;

import sda.workers.AbstractEmployee;
import sda.workers.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();
        employeeList.add(new AbstractEmployee("Szymon", "Nowak", 2000, "Java"));
        employeeList.add(new AbstractEmployee("Arek", "Polak", 5000, "Java"));
        employeeList.add(new AbstractEmployee("Jan", "Kowalski", 3000, "Hr"));
        employeeList.add(new AbstractEmployee("Anna", "Pietrzyk", 6000, "Pm"));
        employeeList.add(new AbstractEmployee("Ola", "Wruszczak", 6000, "Cos tam"));
        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getSalary() > 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .filter(e -> e.getSalary() > 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();

        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().equals("Nowak"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("Now"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap((e -> e.getFirstName()), e -> e));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();

        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("Arek Polak"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.sort((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + (int) e.getSalary()));
        System.out.println();

        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));

        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);
        System.out.println();

        AbstractEmployee pooresEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(pooresEmployee);
        System.out.println();

        Map<String, List<AbstractEmployee>> map1 = listMap(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() >= 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });
        System.out.println(tmpList);
    }

    public static Map<String, List<AbstractEmployee>> listMap(List<AbstractEmployee> employees) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employees) {
            if (map.containsKey(employee.getDepartment())) {
                List<AbstractEmployee> listOfEmployees = map.get(employee.getDepartment());
                listOfEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> listOfEmployees = new ArrayList<>();
                listOfEmployees.add(employee);
                map.put(employee.getDepartment(), listOfEmployees);
            }
        }
        return map;
    }
}
