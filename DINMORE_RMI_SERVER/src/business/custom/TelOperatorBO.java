package business.custom;

import business.SuperBO;
import dtos.TelOperatorDTO;

import java.util.ArrayList;

public interface TelOperatorBO extends SuperBO {
    public TelOperatorDTO searchTelOperator (TelOperatorDTO dto) throws Exception;
    public ArrayList<TelOperatorDTO> getAllOperators () throws Exception;
    public TelOperatorDTO searchFromID (int id) throws Exception;
}
