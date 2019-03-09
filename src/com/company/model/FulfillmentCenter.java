package com.company.model;
/*
Author: BeGieU
Date: 09.03.2019
*/

import com.company.enums.ItemCondition;

import java.util.*;

public class FulfillmentCenter
{
    private String name;
    private List<Item> productList;
    private double maxWeight;
    private double currentWeight;

    private  static  final Comparator COMPARATOR_BY_ITEMS_COUNT= new Comparator<Item>()
    {
        @Override
        public int compare(Item item1, Item item2)
        {
            return Integer.compare(item1.getItemsCount(), item2.getItemsCount());
        }
    };

    public FulfillmentCenter(String name, List<Item> productList, double maxWeight)
    {
        this.name = name;
        this.maxWeight = maxWeight;
        currentWeight = 0;

        this.productList = productList;
        for (Item i : productList)
        {
            currentWeight = currentWeight + i.getWeightOfAllItems();
        }
    }

    public void addProduct(Item item)
    {
        if (currentWeight + item.getWeightOfAllItems() > maxWeight)
        {
            System.err.println("przedmiot: " + item.toString() + "  nie dodany, przekoroczona pojemnosc magazynu");
            return;
        }
        else if (productList.contains(item))
        {
            productList.stream().filter(object -> object.getName().equals(item.getName()))
                    .forEach(found ->
                    {
                        found.setItemsCount(found.getItemsCount() + item.getItemsCount());
                    });
        }
        else
        {
            productList.add(item);
        }
        currentWeight = currentWeight + item.getWeightOfAllItems();
        return;
    }

    public Item getProduct(Item item)
    {
        for (Item i : productList)
        {
            if (i.equals(item))
            {
                if (i.getItemsCount() == 1)
                {
                    productList.remove(item);
                }
                else
                {
                    i.setItemsCount(i.getItemsCount() - 1);
                }
                return item;
            }
        }
        return null;
    }

    public boolean removeProduct(Item item)
    {
        return productList.remove(item);
    }

    public Item search(String name)
    {
        //TODO : niezgodna metoda z poleceniem
        return productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);


    }

    public List<Item> searchPartial(String value)
    {
        List<Item> matchingItems = new ArrayList<>();

        productList.stream().filter(prod -> prod.getName().contains(value))
                .forEach(matchingItems::add);

        return matchingItems;
    }

    public int countByConditon(ItemCondition itemCondition)
    {
        int result = 0;
        for (Item item : productList)
        {
            if (item.getItemCondition().equals(itemCondition))
            {
                result++;
            }
        }
        return result;
    }

    public void summary()
    {
        System.out.println("===============================");
        productList.forEach(Item::print);
        System.out.println("===============================");
    }

    public List<Item> sortByName()
    {
        //cloning the arr
        List<Item> sortedList = new LinkedList<>(productList);
        Collections.sort(sortedList);

        return sortedList;
    }

    public List<Item> sortByAmount()
    {
        List<Item> sortedList = new LinkedList<>(productList);
        Collections.sort(sortedList, COMPARATOR_BY_ITEMS_COUNT);

        return sortedList;
    }

    public Item max()
    {
        return Collections.max(productList, COMPARATOR_BY_ITEMS_COUNT);
    }

    public double getCurrentWeight()
    {
        return currentWeight;
    }

    public double getMaxWeight()
    {
        return maxWeight;
    }

    public String getName()
    {
        return name;
    }

    public static void main(String[] args)
    {
        List<Item> itemList = new LinkedList<>();
        itemList.add(new Item("esssa", ItemCondition.NEW, 1, 1));
        itemList.add(new Item("buc", ItemCondition.USED, 2, 2));
        itemList.add(new Item("rpk3", ItemCondition.NEW, 1, 4));
        itemList.add(new Item("sdr", ItemCondition.REFUBRISHED, 2, 5));
        itemList.add(new Item("baca", ItemCondition.NEW, 1, 7));

        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Monsoon", itemList, 100);
        fulfillmentCenter.addProduct(new Item("nowy DUZY item", ItemCondition.REFUBRISHED, 5000, 2));
        fulfillmentCenter.addProduct(new Item("nowy MALY item", ItemCondition.REFUBRISHED, 1, 2));
        fulfillmentCenter.summary();

        fulfillmentCenter.getProduct(fulfillmentCenter.search("buc"));
        fulfillmentCenter.getProduct(fulfillmentCenter.search("rpk3"));
        fulfillmentCenter.summary();

        fulfillmentCenter.removeProduct(fulfillmentCenter.search("sdr"));
        fulfillmentCenter.summary();

        System.out.println("===============================");
        System.out.println(fulfillmentCenter.searchPartial("MALY"));
        System.out.println("===============================");

        System.out.println("===============================");
        System.out.println(fulfillmentCenter.countByConditon(ItemCondition.NEW));
        System.out.println("===============================");

        System.out.println("===============================");
        for (Item item : fulfillmentCenter.sortByName())
        {
            System.out.println(item.getName());
        }
        System.out.println("===============================");
        System.out.println("===============================");
        for (Item item : fulfillmentCenter.sortByAmount())
        {
            System.out.println(item.toString());
        }
        System.out.println("===============================");
        System.out.println(fulfillmentCenter.max());
        System.out.println("===============================");


    }
}
