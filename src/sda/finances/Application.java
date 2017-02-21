package sda.finances;


import sda.finances.model.Expense;
import sda.finances.model.Product;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {


        List<Expense> expenses = init();
//        expenses.forEach(expense -> {
//            expense.getProducts().stream()
//                    .filter(product -> product.getUnitPrice() <= 3)
//                    .forEach(product -> System.out.println(product));
//        });
//        System.out.println();
//
//        expenses.stream()
//                .filter(expense -> expense.getType().equals("spozywcze"))
//                .forEach(expense -> {
//                    expense.getProducts().stream()
//                            .filter(product -> product.getUnitPrice() <= 3)
//                            .forEach(product -> System.out.println(product));
//                });
//        System.out.println();
//
//        double banan = expenses.stream()
//                .mapToDouble(expense ->
//                        expense.getProducts()
//                                .stream()
//                                .filter(product -> product.getName().equals("banan"))
//                                .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
//                                .sum()
//                )
//                .sum();
//        System.out.println(banan);
//        System.out.println();
//
//        double spozywcze = expenses.stream()
//                .filter(expense -> expense.getType().equals("spozywcze"))
//                .mapToDouble(expense1 -> expense1.getPrice())
//                .sum();
//        System.out.println(spozywcze);
//        System.out.println();
//
//        expenses.stream()
//                .filter(expense -> expense.getDate().isBefore(LocalDate.of(2017, 2, 19)))
//                .forEach(expense -> expense.getProducts()
//                        .forEach(System.out::println));
//        System.out.println();
//6. Wyswietl wydatki dla konkretnego dnia
        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 2, 21)))
                .mapToDouble(expenses1 -> expenses1.getPrice())
                .sum());
        System.out.println();
        //Wyswietl wydatki dla konkretnego dnia na piwo
        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 2, 21)))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("piwo"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())
                .sum());
        System.out.println();
        //7. Zsumować całkowitą kwotę wydan a na produkty zaczynające się na p

        System.out.println(expenses.stream()
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().startsWith("p"))
                        .mapToDouble(product -> product.getAmount() * product.getUnitPrice())
                        .sum())
                .sum());
        System.out.println();
        //8. Zsumować koszt elementów spozywczych które kupiliśmy w parzystych ilościach

        System.out.println(expenses.stream()
                .filter(expense -> expense.getType().equals("spozywcze"))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getAmount() % 2 == 0)
                        .mapToDouble(produkt -> produkt.getAmount() * produkt.getUnitPrice())
                        .sum())
                .sum());
        System.out.println();
        //9. Z każdego expensa wyświetlić produkt za którego zapłaciliśmy najwięcej
        expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                (e1.getAmount() * e1.getUnitPrice()) >
                                        (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                        .get())
                .forEach(product -> System.out.println(product));
        System.out.println();
        //10. Wyświetlić najdroższy produkt z wszystkich expensów
        System.out.println(expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                e1.getUnitPrice() >
                                e2.getUnitPrice() ? 1 : -1)
                        .get())
                .max((e1, e2) ->
                        e1.getUnitPrice() >
                        e2.getUnitPrice() ? 1 : -1)
                .get());
    }


    private static List<Expense> init() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("banan", 5, 2));
        products.add(new Product("piwo", 2, 4));
        products.add(new Product("pomarancza", 10, 0.5));
        products.add(new Product("chipsy", 1, 5));
        Expense expense = new Expense("spozywcze", products);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("farba", 1, 25));
        products2.add(new Product("mlotek", 2, 10));
        products2.add(new Product("gwozdzie", 100, 0.1));
        Expense expense2 = new Expense("budowlane", products2);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("witamina C", 2, 2));
        products3.add(new Product("apap", 2, 10));
        products3.add(new Product("syrop", 3, 5));
        Expense expense3 = new Expense("lekarstwa", products3, 2017, 2, 18);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("banan", 2, 1.5));
        products4.add(new Product("chleb", 2, 2));
        products4.add(new Product("miesa", 2, 15));
        Expense expense4 = new Expense("spozywcze", products4, 2017, 2, 17);

        return Arrays.asList(expense, expense2, expense3, expense4);
    }
}
