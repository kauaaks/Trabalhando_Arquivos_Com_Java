package Program;

import Model.Entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            Locale.setDefault(Locale.US);
            Scanner sc = new Scanner(System.in);

            List<Produto> list = new ArrayList<>();

            System.out.print("entre o caminho do arquivo: ");
            String UserPath = sc.nextLine();
            File path = new File(UserPath);
            String getParent = path.getParent();

            boolean success = new File(getParent + "\\out").mkdir();

            String creatingFile = getParent + "\\out\\summary.csv";

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String itemCsv = br.readLine();
                while (itemCsv != null) {
                        String[] fields = itemCsv.split(",");
                        String name = fields[0];
                        double price = Double.parseDouble(fields[1]);
                        int quantity = Integer.parseInt(fields[2]);

                        list.add(new Produto(name, price, quantity));

                        itemCsv = br.readLine();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(creatingFile))) {

                    for (Produto item: list) {
                        bw.write(item.getName() + ", " + String.format("%.2f",item.total()));
                        bw.newLine();
                    }
                    System.out.println(creatingFile + " CREATED");
                } catch (IOException e) {
                    System.out.println("Error writing file: "+ e.getMessage());
                }

            } catch (IOException e) {
                   System.out.println("Error:" + e.getMessage());
            }

            sc.close();
    }
}