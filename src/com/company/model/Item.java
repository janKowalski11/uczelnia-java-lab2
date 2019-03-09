package com.company.model;
/*
Author: BeGieU
Date: 09.03.2019
*/

import com.company.enums.ItemCondition;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Item implements Comparable<Item>
{
    private final String name;
    private ItemCondition itemCondition;
    private double weight;
    private int itemsCount;

    public Item(String name, ItemCondition itemCondition, double weight, int itemsCount)
    {
        this.name = name;
        this.itemCondition = itemCondition;
        this.weight = weight;
        this.itemsCount = itemsCount;
    }

    @Override
    public int compareTo(Item o)
    {
        return this.name.compareToIgnoreCase(o.name);
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name='" + name + '\'' +
                ", itemCondition=" + itemCondition +
                ", weight=" + weight +
                ", itemsCount=" + itemsCount +
                '}';
    }

    public double getWeightOfAllItems()
    {
        return itemsCount * weight;
    }

    public void print()
    {
        System.out.println(this.toString());
    }

    public String getName()
    {
        return name;
    }

    public ItemCondition getItemCondition()
    {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition)
    {
        this.itemCondition = itemCondition;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public int getItemsCount()
    {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount)
    {
        this.itemsCount = itemsCount;
    }

    public static void main(String[] args)
    {
        //        Item item = new Item("s", ItemCondition.valueOf("NEW"), 21, 21);
        //        item.print();

        List<Item> itemList = new LinkedList<>();
        itemList.add(new Item("essa", ItemCondition.NEW, 21, 21));
        itemList.add(new Item("buc", ItemCondition.NEW, 21, 21));
        itemList.add(new Item("rpk", ItemCondition.NEW, 21, 21));
        itemList.add(new Item("down", ItemCondition.NEW, 21, 21));
        itemList.add(new Item("baca", ItemCondition.NEW, 21, 21));
        Collections.sort(itemList);
        for (Item item : itemList)
        {
            System.out.println(item.name);
        }
    }
}
