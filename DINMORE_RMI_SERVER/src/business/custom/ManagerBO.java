package business.custom;

import business.SuperBO;
import dtos.ManagerDTO;
import java.util.ArrayList;

public interface ManagerBO extends SuperBO {
    public ManagerDTO searchManager (ManagerDTO dto) throws Exception;
    public ArrayList<ManagerDTO> getAllManagers () throws Exception;
    public ManagerDTO searchFromID (int id) throws Exception;
}