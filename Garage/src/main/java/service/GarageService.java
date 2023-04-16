package service;

import client.Client;

import java.util.Scanner;

public class GarageService {

    Client client = new Client();
    Scanner in = new Scanner(System.in);
    String tempNameClient; //имя
    String tempTelephone; //номер телефона
    String tempCar; //марка машины
    int tempGarageNumber; //номер гаража
    int tempMonthlyFee; //ежемесячная оплата

    //метод запуска всей программы
    public void start() {
        boolean exit = false; //если == true то программа закрывается
        int action; //определяет действие пользователя

        Scanner in = new Scanner(System.in);

        System.out.println("Система умный гараж\n");
        client.uploadData(); //вызов метода загрузки данных (если файла с данными нет то кинет ошибку)

        while (!exit) {


            System.out.println("\nЧто бы вы хотели сделать ?" +
                    "\n\t1) Добавить клиента в систему" +
                    "\n\t2) Удалить клиента из системы" +
                    "\n\t3) Вывести данные о контретном клиента" +
                    "\n\t4) Вывести данные о всех клиентах" +
                    "\n\t5) Закрыть программу");

            action = in.nextInt();

            switch (action) {

                case 1: //кейс добавления клиентов
                    boolean isWorking = true;

                    client.addClient(); // метод добавления клиента

                    while (isWorking) {
                        System.out.println("Хотите добавить еще одного клиента?\n1) Да\t2)нет");
                        int tempVal = in.nextInt();

                        if (tempVal == 1) {

                            client.addClient();
                        } else if (tempVal == 2) {
                            isWorking = false;
                        } else {
                            System.out.println("Введено некорректное число\n");
                        }
                    }
                    break;


                case 2: // кейс удаления клиента
                    System.out.println("Вы хотите удалить клиента\nВведите его имя\n");
                    tempNameClient = in.next();

                    client.deleteClient(tempNameClient); //метод удаления клиента
                    break;

                case 3: // кейс вывода нужного клиета
                    System.out.println("Чтобы вывести данные клиента введите его имя\n");
                    tempNameClient = in.next();

                    client.clientInfo(tempNameClient);
                    break;

                case 4: //кейс вывода всех клиентов
                    client.allInfo();
                    break;

                case 5: //кейс закрытия программы
                    client.saveData(); //сохранение данных при выходе из программы
                    exit = true;
                    break;

                default: //дефолтный кейс, срабатывает если введено неверное число
                    System.out.println("Вы где-то ошиблись\n");
                    break;
            }
        }
    }

}
