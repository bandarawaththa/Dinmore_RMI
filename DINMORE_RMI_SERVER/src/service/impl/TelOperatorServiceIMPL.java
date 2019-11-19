package service.impl;

import business.BOFactory;
import business.custom.TelOperatorBO;
import dtos.CheffDTO;
import dtos.TelOperatorDTO;
import security.PasswordUtils;
import service.custom.TelOperatorService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TelOperatorServiceIMPL extends UnicastRemoteObject implements TelOperatorService {
    TelOperatorBO bo = (TelOperatorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TELOPERATOR);
    private TelOperatorDTO telOperatorDTO = null;

    public TelOperatorServiceIMPL() throws RemoteException {}

    @Override
    public TelOperatorDTO searchTelOperator(TelOperatorDTO dto) throws Exception {
        telOperatorDTO = bo.searchTelOperator(dto);
        return telOperatorDTO;
    }

    @Override
    public ArrayList<TelOperatorDTO> getAllOperators() throws Exception {
        return bo.getAllOperators();
    }

    @Override
    public TelOperatorDTO searchFromID(int id) throws Exception {
        return bo.searchFromID(id);
    }

    @Override
    public boolean checkPW(String password) throws Exception {
        return PasswordUtils.verifyUserPassword(password,telOperatorDTO.getPW(),telOperatorDTO.getSalt());
    }
}