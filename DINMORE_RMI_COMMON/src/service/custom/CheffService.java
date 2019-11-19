package service.custom;

import dtos.CheffDTO;
import service.SuperService;
import java.util.ArrayList;

public interface CheffService extends SuperService {
    public CheffDTO searchCheff (CheffDTO dto) throws Exception;
    public ArrayList<CheffDTO> getAllCheffs () throws Exception;
    public boolean checkPW (String password) throws Exception;
}