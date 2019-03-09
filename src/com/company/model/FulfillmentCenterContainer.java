package com.company.model;
/*
Author: BeGieU
Date: 09.03.2019
*/

import com.company.enums.ItemCondition;

import java.util.*;

public class FulfillmentCenterContainer
{
    private Map<String, FulfillmentCenter> magazines;

    public FulfillmentCenterContainer(Map<String, FulfillmentCenter> magazines)
    {
        this.magazines = magazines;
    }

    public FulfillmentCenter addCenter(String name, double capacity)
    {
        return magazines.put(name, new FulfillmentCenter(name, new LinkedList<>(), capacity));
    }

    public FulfillmentCenter addCenter(String name, FulfillmentCenter fulfillmentCenter)
    {
        return magazines.put(name, fulfillmentCenter);
    }

    public FulfillmentCenter removeCenter(String name)
    {
        return magazines.remove(name);
    }

    public List<FulfillmentCenter> findEmpty()
    {

        List<FulfillmentCenter> emptyCenters = new ArrayList<>();

        for (FulfillmentCenter center : magazines.values())
        {
            if (center.getCurrentWeight() == 0)
            {
                emptyCenters.add(center);
            }
        }
        return emptyCenters;
    }

    public void summary()
    {
        System.out.println("===========");
        for (Map.Entry<String, FulfillmentCenter> entry : magazines.entrySet())
        {
            System.out.println("name: " + entry.getKey() +
                    " capacity by percent: " + (entry.getValue().getCurrentWeight() / entry.getValue().getMaxWeight()) * 100
                    + "%");

        }
        System.out.println("===========");
    }


    public static void main(String[] args)
    {
        Map<String, FulfillmentCenter> magazines = new HashMap<>();

        List<Item> itemList = new LinkedList<>();
        itemList.add(new Item("esssaa", ItemCondition.NEW, 1, 1));
        itemList.add(new Item("bucs", ItemCondition.USED, 2, 2));
        itemList.add(new Item("rpk33", ItemCondition.NEW, 1, 4));
        itemList.add(new Item("sdr1", ItemCondition.REFUBRISHED, 2, 5));
        itemList.add(new Item("baca2", ItemCondition.NEW, 1, 7));

        FulfillmentCenter fulfillmentCenter1 = new FulfillmentCenter("Monsoon", itemList, 50);

        magazines.put("Monsoon", fulfillmentCenter1);

        List<Item> itemList2 = new LinkedList<>();
        itemList2.add(new Item("a", ItemCondition.NEW, 12, 1));
        itemList2.add(new Item("b", ItemCondition.USED, 21, 2));
        itemList2.add(new Item("c", ItemCondition.NEW, 12, 4));
        itemList2.add(new Item("d", ItemCondition.REFUBRISHED, 32, 5));

        FulfillmentCenter fulfillmentCenter2 = new FulfillmentCenter("Nike", itemList2, 9999);

        magazines.put("Nike", fulfillmentCenter2);


        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer(magazines);
        fulfillmentCenterContainer.addCenter("Pjuma", 2112);
        fulfillmentCenterContainer.addCenter("Abidos", 21);
        fulfillmentCenterContainer.summary();

        fulfillmentCenterContainer.removeCenter("Nike");
        fulfillmentCenterContainer.summary();

        for (FulfillmentCenter center : fulfillmentCenterContainer.findEmpty())
        {
            System.out.println(center.getName());
        }
    }
}
