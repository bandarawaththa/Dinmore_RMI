package business.custom.impl;

import business.custom.TelOperatorBO;
import dao.DAOFactory;
import dao.custom.TelOperatorDAO;
import dbHandler.DBConnection;
import dtos.TelOperatorDTO;
import entity.TelOperatorEntity;
import java.sql.Connection;
import java.util.ArrayList;

public class TelOperatorBOIMPL implements TelOperatorBO {
    TelOperatorDAO dao = (TelOperatorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TELOPERATOR);

    @Override
    public TelOperatorDTO searchTelOperator(TelOperatorDTO dto) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            TelOperatorEntity search = dao.search(new TelOperatorEntity(dto.getUName()));
            return (null == search) ? null : new TelOperatorDTO("OP" + search.getId(),search.getUName(), search.getPW(), search.getSalt());
        }
    }

    @Override
    public ArrayList<TelOperatorDTO> getAllOperators() throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<TelOperatorEntity> allEntities = dao.getAll();
            ArrayList<TelOperatorDTO> allDTOS = new ArrayList<>();
            for (TelOperatorEntity entity :
                    allEntities) {
                allDTOS.add(new TelOperatorDTO(Integer.toString(entity.getId()), entity.getUName(), entity.getPW(), entity.getSalt()));
            }
            return allDTOS;
        }
    }

    @Override
    public TelOperatorDTO searchFromID(int id) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            TelOperatorEntity search = dao.searchFromID(id);
            return (null == search) ? null : new TelOperatorDTO(Integer.toString(search.getId()),search.getUName(), search.getPW(), search.getSalt());
        }
    }
}