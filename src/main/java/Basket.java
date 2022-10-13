import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;

    protected int[] quantity;
    protected int[] quantityProduct;


    public Basket (String[] products, int[] prices) throws FileNotFoundException {
        this.prices = prices;
        this.products = products;
        this.quantity = new int[products.length];
        this.quantityProduct= new int[products.length];
    }


    public void addToCart(int productNum, int amount){
        if (productNum < (products.length + 1) && productNum >= 0) {

            quantity[productNum] += amount;
            int currentPrice = prices[productNum];
            quantityProduct[productNum] = quantity[productNum] * currentPrice;
        } else {
            System.out.println("Такого товара нет");
        }
    }
    public void saveTxt(File textFile) throws IOException {
        JSONObject obj = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        obj.put("productNum",gson.toJson(quantity));
        obj.put("amount",gson.toJson(quantityProduct));
        try (FileWriter file = new FileWriter("basket.json")) {
          file.write(obj.toJSONString());
          file.flush();
                }
        }



    static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("basket.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        //        InputStream in = new FileInputStream(textFile);
//        Scanner scanner1 = new Scanner(in);
//        String[] asd = scanner1.nextLine().split(" ");
//        String[] asd1 = scanner1.nextLine().split(" ");
//        for (int i = 0; i < asd.length; i++) {
//            quantity[i] = Integer.parseInt(asd[i]);
//            quantityProduct[i] = Integer.parseInt(asd1[i]);
//        }
//        return loadFromTxtFile(textFile);


        public void printCart () {
            StringBuilder print = new StringBuilder();
            print.append("Ваша корзина");
            print.append("\n");
            for (int i = 0; i < products.length; i++) {
                if (quantity[i] >= 1) {
                    print.append(products[i] + " " + quantity[i] + " шт " + prices[i] + " руб/шт " + quantityProduct[i] + " руб в сумме ");
                    print.append("\n");
                }
            }
            print.append("Итого: " + Arrays.stream(quantityProduct).sum() + " руб");
            System.out.println(print.toString());
        }

    }










