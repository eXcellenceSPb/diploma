package com.jwt.service;

import com.jwt.model.Medical;

import java.util.List;

public interface MedicalService {
    void addMed(Medical medical);

    List<Medical> getAll();

    void deleteMed(Integer medId);

    Medical getMed(Integer medId);

    Medical updateMed(Medical medical);

    Medical findMedByQueue(String queue);

    boolean isExist(int id);
}
