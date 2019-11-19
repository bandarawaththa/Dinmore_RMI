package service.custom;

import dtos.TelOperatorDTO;
import service.SuperService;
import java.rmi.Remote;
import java.util.ArrayList;

public interface TelOperatorService extends SuperService {
    public TelOperatorDTO searchTelOperator (TelOperatorDTO dto) throws Exception;
    public ArrayList<TelOperatorDTO> getAllOperators () throws Exception;
    public TelOperatorDTO searchFromID (int id) throws Exception;
    public boolean checkPW (String password) throws Exception;
}
