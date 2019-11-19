package business.custom.impl;

import business.custom.ManagerBO;
import dao.DAOFactory;
import dao.custom.ManagerDAO;
import dbHandler.DBConnection;
import dtos.ManagerDTO;
import entity.ManagerEntity;

import java.sql.Connection;
import java.util.ArrayList;

public class ManagerBOIMPL implements ManagerBO {
    ManagerDAO dao = (ManagerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MANAGER);

    @Override
    public ManagerDTO searchManager(ManagerDTO dto) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ManagerEntity managerEntity = new ManagerEntity();
            managerEntity.setUName(dto.getUName());
            ManagerEntity search = dao.search(managerEntity);
            return (null == search) ? null : new ManagerDTO("MAN" + search.getId(),search.getUName(), search.getPW(),search.getSalt());
        }
    }

    @Override
    public ArrayList<ManagerDTO> getAllManagers() throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<ManagerEntity> allEntities = dao.getAll();
            ArrayList<ManagerDTO> allDTOS = new ArrayList<>();
            for (ManagerEntity entity :
                    allEntities) {
                allDTOS.add(new ManagerDTO(entity.getPW(), entity.getUName(), entity.getPW(),entity.getSalt()));
            }
            return allDTOS;
        }
    }

    @Override
    public ManagerDTO searchFromID(int id) throws Exception {
        return null;
    }
}