package service.custom;

import dtos.ManagerDTO;
import service.SuperService;
import java.util.ArrayList;

public interface ManagerService extends SuperService {
    public ManagerDTO searchManager (ManagerDTO dto) throws Exception;
    public ArrayList<ManagerDTO> getAllManagers () throws Exception;
    public ManagerDTO searchFromID (int id) throws Exception;
    public boolean checkPW (String password) throws Exception;
}
