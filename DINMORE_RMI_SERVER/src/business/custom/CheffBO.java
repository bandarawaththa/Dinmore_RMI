package business.custom;

import business.SuperBO;
import dtos.CheffDTO;
import java.util.ArrayList;

public interface CheffBO extends SuperBO {
    public CheffDTO searchCheff (CheffDTO fdto) throws Exception;
    public ArrayList<CheffDTO> getAllCheffs() throws Exception;
}
