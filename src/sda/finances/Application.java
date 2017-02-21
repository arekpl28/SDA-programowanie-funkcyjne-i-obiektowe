package sda.finances;


import sda.finances.model.Expense;
import sda.finances.model.Product;

import java.lang.reflect.Array;
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
        expenses.forEach(expense -> {
            expense.getProducts().stream()
                    .filter(product -> product.getName().equals("banan"))
                    .forEach(product -> System.out.println(product));
        });
        System.out.println();

        expenses.stream()
                .filter(expense -> expense.getType().equals("spozywcze"))
                .forEach(expense -> {
                    double price = expense.getPrice();
                    System.out.println(price);
                });
        System.out.println();
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
        Expense expense2 = new Expense("budowlane", products2, 2017, 2, 19);

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
