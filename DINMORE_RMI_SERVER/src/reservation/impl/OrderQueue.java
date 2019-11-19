package reservation.impl;

import dtos.OrderDTO;

import java.util.ArrayList;

public class OrderQueue {
    private static ArrayList<OrderDTO> allOrders = new ArrayList<>();

    public void add (OrderDTO dto) {
        allOrders.add(dto);
    }

    public void remove (OrderDTO dto) {
        allOrders.remove(dto);
    }
}
