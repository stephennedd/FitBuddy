package com.san.fitbuddy.Model;


import java.util.ArrayList;
import java.util.List;

public class ClientAdmin {
    public static List<Client> clientList;

    static {
        clientList = new ArrayList<Client>();
    }

    public ClientAdmin() { clientList = new ArrayList<Client>(); }

    public List getClientList() {
        if (clientList != null) {
            return clientList;
        }
        System.out.println("null list!");
        return null;
    }

    public void setClientList() {
        List<Workout> workoutList1 = new ArrayList<Workout>();
        List<Workout> workoutList2 = new ArrayList<Workout>();
        List<Workout> workoutList3 = new ArrayList<Workout>();
        List<Workout> workoutList4 = new ArrayList<Workout>();
        List<Workout> workoutList5 = new ArrayList<Workout>();
        List<Workout> workoutList6 = new ArrayList<Workout>();
        List<Workout> workoutList7 = new ArrayList<Workout>();
        List<Workout> workoutList8 = new ArrayList<Workout>();
        List<Workout> workoutList9 = new ArrayList<Workout>();
        List<Workout> workoutList10 = new ArrayList<Workout>();
        List<Workout> workoutList11 = new ArrayList<Workout>();
        List<Workout> workoutList12 = new ArrayList<Workout>();

        Client client1 = new Client("Marvin Jacobs", "Work harder!", 80.2, 1.88, 24, "Male", 75.0, "avatar_1.jpg", workoutList1);
        Client client2 = new Client("Shannon Jeffries", "Great job so far!", 70.4, 1.75, 25, "Female", 60.0, "avatar_2.jpg", workoutList2);
        Client client3 = new Client("Jeniffer Anniston", "Loved the last movie! Also nice work staying fit!", 55.2, 1.64, 45, "Female", 50.0, "avatar_3.jpg", workoutList3);
        Client client4 = new Client("Mary Poppins", "Need to work on leg-press form", 87.4, 1.60, 42, "Female", 80.0, "avatar_4.jpg", workoutList4);
        Client client5 = new Client("Ivonne de Windt", "Max weight is still quite low", 45.0, 1.67, 27, "Female", 55.0, "avatar_5.jpg", workoutList5);
        Client client6 = new Client("Karla Mijers", "New Client", 77.9, 1.79, 22, "Female", 70.0, "avatar_6.jpg", workoutList6);
        Client client7 = new Client("Gina King", "Working, next session tomorrow", 56.1,1.56, 61, "Female", 60.0, "avatar_7.jpg", workoutList7);
        Client client8 = new Client("Joe Rogan", "Good job", 89.5, 1.68, 42, "Male", 80.0, "avatar_8.jpg", workoutList8);
        Client client9 = new Client("Martin King", "Will definitely hit your targets", 50.4, 1.70, 35, "Male", 55.0, "avatar_9.jpg", workoutList9);
        Client client10 = new Client("Greg DaSantos", "Great conditioning", 68.8, 1.80, 31, "Male", 75.0, "avatar_10.jpg", workoutList10);
        Client client11 = new Client("Melvin o'Reilly", "Keep up the great work", 86.6, 1.85, 55, "Male", 80.0, "avatar_11.jpg", workoutList11);
        Client client12 = new Client("Julia Griffin", "Sick", 53.2, 1.40, 24, "Female", 65.0, "avatar_12.jpg", workoutList12);

        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        clientList.add(client5);
        clientList.add(client6);
        clientList.add(client7);
        clientList.add(client8);
        clientList.add(client9);
        clientList.add(client10);
        clientList.add(client11);
        clientList.add(client12);
    }

}
