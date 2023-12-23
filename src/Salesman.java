import java.io.*;
import java.util.*;
import java.util.logging.LoggingPermission;

public class Salesman extends Person {
    Salesman(String login, String password) {
        super(login, password);
    }

    @Override
    void showMenu() {
        System.out.println("Приветствую дорогой, Продавец!\n" +
                "Пожалуйста наберите номер меню для работы с программой\n");
        System.out.println("1.\tПоказать весь список товаров для продажи ");
        System.out.println("2.\tИскать товар:");
        System.out.println("3.\tПоказать отчет по продаже ");
        System.out.println("4.\tСделать заказ отсутствующего товара");
        System.out.println("5.\tУдалить заказ:");
        System.out.println("6.\tВыход");
    }

    @Override
    void chooseOption() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1) {
            try {
                FileReader reader = new FileReader("sale.txt");
                int data = reader.read();
                while(data != -1){
                    System.out.print((char)data);
                    data = reader.read();
                }
                reader.close();

            } catch (Exception e) {
                System.out.println("Неверный путь до файла :(");
            }
        }
        else if(option == 2){
            System.out.println("1.\tПо названию ");
            System.out.println("2.\tПо дате ");
            int option1 = scanner.nextInt();
            if (option1 == 1){
                System.out.println("•\tНапишите название товара для поиска:>>");
                String goods = scanner.next();
                try {
                    File file = new File("sale.txt");
                    Scanner scanner1 = new Scanner(file);
                    while(scanner1.hasNextLine()){
                        String data = scanner1.nextLine();
                        if (data.contains(goods)){
                            System.out.println(data);
                            break;
                        }
                        else if(!scanner1.hasNextLine()){
                            System.out.println("Такой товар не найден :(");
                        }
                    }
                }catch (Exception e){

                }
            }
            else if (option1 == 2){
                System.out.println("•\tНапишите дату для поиска:>>");
                String date = scanner.next();

            }
            else{
                System.out.println("Невереный формат ввода. Попробуйте еще раз.");
            }
        }
        else if (option == 3){
            try {
                File file = new File("sold.txt");
                Scanner scanner1 = new Scanner(file);
                while (scanner1.hasNextLine()){
                    String data = scanner1.nextLine();
                    System.out.println(data);
                }
            }catch (Exception e){

            }
        }else if(option == 4){
            try {
                File file = new File("outOfStock.txt");
                Scanner scanner1 = new Scanner(file);
                while (scanner1.hasNextLine()){
                    int i = 1;
                    String data = scanner1.nextLine();
                    System.out.println(i + "." + data);
                    i++;
                }
                System.out.println("Выберите товар, который хотите заказать.");
                int choice = scanner.nextInt();
                System.out.println("Выберите количество, которое хотите заказать.");
                int howMuch = scanner.nextInt();
            }catch (Exception e){

            }
        }
//        else if (option == 5){
//            System.out.println("Какой заказ вы бы хотели удалить? >>>");
//            try {
//                File file = new File("sold.txt");
//                Scanner scanner1 = new Scanner(file);
//                while (scanner1.hasNextLine()) {
//                    int i = 1;
//                    String data = scanner1.nextLine();
//                    System.out.println(i + "." + data);
//                }
//                System.out.println("0.Никакой");
//                int choice = scanner.nextInt();
//                System.out.println();
//
//            }catch (Exception e){}
//        }
        else if (option == 6) {
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");

        }
    }

}
