package com.jwt.service;

import com.jwt.dao.MedicalDAO;
import com.jwt.model.Medical;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("medicalService")
public class MedicalServiceImpl implements MedicalService {

    private final MedicalDAO medicalDAO;

    public MedicalServiceImpl(MedicalDAO medicalDAO) {
        this.medicalDAO = medicalDAO;
    }

    @Override
    public void addMed(Medical medical) {
        medicalDAO.addMed(medical);
    }

    @Override
    public List<Medical> getAll() {
        return medicalDAO.getAll();
    }

    @Override
    public void deleteMed(Integer medId) {
        medicalDAO.deleteMed(medId);
    }

    @Override
    public Medical getMed(Integer medId) {
        return medicalDAO.getMed(medId);
    }

    @Override
    public Medical updateMed(Medical medical) {
        return medicalDAO.updateMed(medical);
    }

    @Override
    public Medical findMedByQueue(String queue) {
        return medicalDAO.findMedByQueue(queue);
    }

    @Override
    public boolean isExist(int  id) {
        return medicalDAO.isExist(id);
    }
}
