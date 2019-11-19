package service.impl;

import business.BOFactory;
import business.custom.CheffBO;
import dtos.CheffDTO;
import security.PasswordUtils;
import service.custom.CheffService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CheffServiceIMPL extends UnicastRemoteObject implements CheffService {
    CheffBO bo = (CheffBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEFF);
    private CheffDTO cheffDTO = null;

    public CheffServiceIMPL() throws RemoteException {}

    @Override
    public CheffDTO searchCheff(CheffDTO dto) throws Exception {
        cheffDTO = bo.searchCheff(dto);
        return bo.searchCheff(dto);
    }

    @Override
    public ArrayList<CheffDTO> getAllCheffs() throws Exception {
        return bo.getAllCheffs();
    }

    @Override
    public boolean checkPW(String password) throws Exception {
        return PasswordUtils.verifyUserPassword(password,cheffDTO.getPW(),cheffDTO.getSalt());
    }
}