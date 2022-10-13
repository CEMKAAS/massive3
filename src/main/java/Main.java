import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File textFile = new File("basket.json");
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        ClientLog clientLog = new ClientLog();
        Basket basket = new Basket(products, prices);

        if (textFile.exists()) {
            Basket.loadFromTxtFile(textFile);
            basket.printCart();
        } else {
            System.out.println("Список возможных товаров для покупки");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб/шт");
            }
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                basket.printCart();
                break;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Нужно ввести два числа на одной строке");
                    continue;
                }

                int product = Integer.parseInt(parts[0]);

                if ((0 >= product) || (product >= products.length + 1)) {
                    System.out.println("В параметре надо указать коректный товар " + product);
                    continue;
                }

                product--;

                int pricesOne = Integer.parseInt(parts[1]);
                if (pricesOne <= 0) {
                    System.out.println("Введите положительное число " + pricesOne);
                    continue;
                }

                basket.addToCart(product, pricesOne);
                basket.saveTxt(textFile);
//                clientLog.log(product, pricesOne);
//                clientLog.exportAsCSV(textFile);
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести два числа, а вы ввели " + input);
                continue;

            }
        }


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Node config = doc.getDocumentElement();
        System.out.println("Корневой элемент: " + root.getNodeName());
        read(root);

        private static void read(Node node) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node_ = nodeList.item(i);
                if (Node.ELEMENT_NODE == node_.getNodeType()) {
                    System.out.println("Текущий узел: " + node_.getNodeName());
                    Element element = (Element) node_;
                    NamedNodeMap map = element.getAttributes();
                    for (int a = 0; a < map.getLength(); a++) {
                        String attrName = map.item(a).getNodeName();
                        String attrValue = map.item(a).getNodeValue();
                        System.out.println("Атрибут: " + attrName + "; значение: " + attrValue);
                    }
                    read(node_);       }





        }
}


