package business.custom.impl;

import business.custom.CheffBO;
import dao.DAOFactory;
import dao.custom.CheffDAO;
import dbHandler.DBConnection;
import dtos.CheffDTO;
import entity.CheffEntity;
import java.sql.Connection;
import java.util.ArrayList;

public class CheffBOIMPL implements CheffBO {
    CheffDAO dao = (CheffDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CHEFF);

    @Override
    public CheffDTO searchCheff(CheffDTO dto) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            CheffEntity search = dao.search(new CheffEntity(dto.getUName()));
            return (null == search) ? null : new CheffDTO(Integer.toString(search.getId()), search.getUName(), search.getPW(), search.getSalt());
        }
    }

    @Override
    public ArrayList<CheffDTO> getAllCheffs() throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<CheffEntity> allEntities = dao.getAll();
            ArrayList<CheffDTO> allDTOS = new ArrayList<>();
            for (CheffEntity entity :
                    allEntities) {
                allDTOS.add(new CheffDTO(Integer.toString(entity.getId()), entity.getUName(), entity.getPW(), entity.getSalt()));
            }
            return allDTOS;
        }
    }
}