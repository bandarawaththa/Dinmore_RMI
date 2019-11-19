package service.impl;

import business.BOFactory;
import business.custom.ManagerBO;
import dtos.ManagerDTO;
import security.PasswordUtils;
import service.custom.ManagerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagerServiceIMPL extends UnicastRemoteObject implements ManagerService {
    private ManagerBO bo = (ManagerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGER);
    private ManagerDTO managerDTO = null;
    public ManagerServiceIMPL() throws RemoteException {}

    @Override
    public ManagerDTO searchManager(ManagerDTO dto) throws Exception {
        managerDTO = bo.searchManager(dto);
        return managerDTO;
    }

    @Override
    public ArrayList<ManagerDTO> getAllManagers() throws Exception {
        return bo.getAllManagers();
    }

    @Override
    public ManagerDTO searchFromID(int id) throws Exception {
        return bo.searchFromID(id);
    }

    @Override
    public boolean checkPW(String password) throws Exception {
        return PasswordUtils.verifyUserPassword(password,managerDTO.getPW(),managerDTO.getSalt());
    }
}