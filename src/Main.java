import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для запуска программы, пожалуйста введите тип аккаунта: >>>" + "\n" +
                "1.Продавец\n"+
                "2.Доставщик\n"+
                "3.Поставщик");
        String answer = scanner.nextLine();
        System.out.println("Введите логин: ");
        String login = scanner.next();
        System.out.println("Введите пароль: ");
        String password = scanner.next();

        if (answer.equals("1")){
            Salesman salesman = new Salesman(login,password);
            salesman.showMenu();
            salesman.chooseOption();


        }
        else if (answer.equals("2")){
            DeliveryMan deliveryMan = new DeliveryMan(login,password);
        }
        else if (answer.equals("3")){
            Provider provider = new Provider(login,password);
        }
        else{
            System.out.println("Извините, но мы не нашли такой тип аккаунта, пожалуйста повторите.");
        }



    }
}