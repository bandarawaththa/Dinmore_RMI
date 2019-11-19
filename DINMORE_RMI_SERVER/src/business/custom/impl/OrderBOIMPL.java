package business.custom.impl;

import business.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dbHandler.DBConnection;
import dtos.OrderDTO;
import entity.OrderEntity;

import java.sql.Connection;
import java.util.ArrayList;

public class OrderBOIMPL implements OrderBO {
    OrderDAO dao = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean addOrder(OrderDTO order) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            return dao.add(new OrderEntity(0, order.getAddOrderTime(), Integer.parseInt(order.getCusID()), Integer.parseInt(order.getTelOpID()), order.getQty(), Integer.parseInt(order.getCheffID()), order.getStartTime(), order.getEndTime(), Integer.parseInt(order.getCount()), order.getDate()));
        }
    }

    @Override
    public boolean deleteOrder(OrderDTO order) throws Exception {
        return false;
    }

    @Override
    public OrderDTO searchOrder(OrderDTO order) throws Exception {
        return null;
    }

    @Override
    public boolean updateOrder(OrderDTO order) throws Exception {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<OrderEntity> allEntities = dao.getAll();
            ArrayList<OrderDTO> allDTOS = new ArrayList<>();
            for (OrderEntity entity :
                    allEntities) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderID("OR" + entity.getOrderID());
                dto.setAddOrderTime(entity.getAddOrderTime());
                dto.setCusID("CUS" + entity.getCusID());
                dto.setTelOpID("TO" + entity.getTelOpID());
                dto.setQty(entity.getQty());
                dto.setCheffID("KO" + entity.getCheffID());
                dto.setStartTime(entity.getStartTime());
                dto.setEndTime(entity.getEndTime());
                dto.setCount(Integer.toString(entity.getCount()));
                dto.setDate(entity.getDate());
                dto.setAmount(entity.getQty() * 100);
                allDTOS.add(dto);
            }
            return allDTOS;
        }
    }

    @Override
    public ArrayList<OrderDTO> searchAllOrders(String oID) throws Exception {
        return null;
    }
}
