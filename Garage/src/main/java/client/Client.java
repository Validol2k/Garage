package client;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    Scanner in = new Scanner(System.in);
    private ArrayList<Client> garageClient = new ArrayList<>();
    private String nameClient; //имя
    private String telephone; //номер телефона
    private String car; //марка машины
    private int garageNumber; //номер гаража
    private int monthlyFee; //ежемесячная оплата

    //конструктор класса, нужен для добавления клиета через метод
    public Client(String nameClient, String telephone, String car, int garageNumber, int monthlyFee) {
        this.nameClient = nameClient;
        this.telephone = telephone;
        this.car = car;
        this.garageNumber = garageNumber;
        this.monthlyFee = monthlyFee;
    }

    //путой конструктор
    public Client() {
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getCar() {
        return car;
    }

    public int getGarageNumber() {
        return garageNumber;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    //метод добавления клиента
    public void addClient() {
        System.out.println("Задайте ему имя, номер его телефона, укажите марку машины" +
                ", выделите номер его гаража и укажите ежемесячную плату \n");

        //присвоение переменных
        nameClient = in.next();
        telephone = in.next();
        car = in.next();
        garageNumber = in.nextInt();
        monthlyFee = in.nextInt();

        //добавление клиента в ArrayList
        garageClient.add(new Client(nameClient, telephone, car, garageNumber, monthlyFee));
    }

    //удаление клиента
    public void deleteClient(String clientName) {
        //for-each который проходится по всем объектам ArrayList

        for (Client client : garageClient) {

            //если введенное имя совпадет с именем существующего клиента, то он удалится
            if (client.getNameClient().equals(clientName)) {
                garageClient.remove(client);
                System.out.println(client.getNameClient() + " успешно удален");
                break;
            }
        }
    }


    public void clientInfo(String clientName) {

        //for-each который проходится по всем объектам ArrayList
        for (Client client : garageClient) {

            //если введенное имя совпадет с именем существующего клиента, то выведется вся информация о нем
            if (client.getNameClient().equals(clientName)) {
                System.out.println("\tДанные клиента " + client.getNameClient() + "\n" + client.getNameClient() +
                        ", номер телефона " + client.getTelephone() + " арендует гараж под номером "
                        + client.getGarageNumber() + ", имеет машину " + client.getCar()
                        + ", ежемесячная плата составляет " + client.getMonthlyFee() + " рублей\n");
            }
        }
    }

    public void allInfo() {

        //for-each который проходится по всем объектам ArrayList
        for (Client client : garageClient) {
            //выводит информацию о кажом клиенте
            System.out.println("\tДанные клиента " + client.getNameClient() + "\n" + client.getNameClient() +
                    ", номер телефона " + client.getTelephone() + " арендует гараж под номером "
                    + client.getGarageNumber() + ", имеет машину " + client.getCar()
                    + ", ежемесячная плата составляет " + client.getMonthlyFee() + " рублей\n");
        }
    }

    //метод сохранения данных в CVS файл
    public void saveData() {
        String cvsFile = "garageClient.cvs"; //имя файла с сохранением

        try {
            FileWriter writer = new FileWriter(cvsFile); //открытие потока записи

            //проходит по каждому клиету и записывает это все в поток
            for (Client client : garageClient) {
                writer.append(client.getNameClient())
                        .append(",")
                        .append(client.getTelephone())
                        .append(",")
                        .append(client.getCar())
                        .append(",")
                        .append(String.valueOf(client.getGarageNumber()))
                        .append(",")
                        .append(String.valueOf(client.getMonthlyFee()))
                        .append("\n");
            }
            //сохранение файла
            System.out.println("Данные успешно сохранены\n");
            writer.flush();

        } catch (IOException e) { //отлавливает ошибку если такая была
            System.out.println("При сохранении произошла ошибка " + e.getMessage());
        }
    }

    //метод загрузки данных
    public void uploadData() {

        String csvFile = "garageClient.cvs";//имя файла сохранения
        File file = new File(csvFile); //открытие потока

        try {
            Scanner scanner = new Scanner(file);

            //цикл будет идти и записывать пока в файле есть данные
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                String tempName = fields[0];
                String tempTelephone = fields[1];
                String tempCar = fields[2];
                int tempGarageNumber = Integer.parseInt(fields[3]);
                int tempMF = Integer.parseInt(fields[4]);

                //добаляет клиента
                garageClient.add(new Client(tempName, tempTelephone, tempCar, tempGarageNumber, tempMF));
            }
            System.out.println("Данные успешно загружены\n");

        } catch (IOException e) { //отлавливает ошибку если она возникнет
            System.out.println("При загрузке данных произошла ошибка " + e.getMessage());
        }
    }
}

