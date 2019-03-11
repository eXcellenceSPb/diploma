package com.jwt.dao;

import com.jwt.model.Medical;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("MedicalDAO")
@Transactional
public class MedicalDAOImpl extends AbstractDao<Integer, Medical> implements MedicalDAO {
    @Override
    public void addMed(Medical medical) {
        persist(medical);
    }

    @Override
    public List<Medical> getAll() {
        return getEm().createQuery("select a from Medical a", Medical.class)
                .getResultList();
    }

    @Override
    public void deleteMed(Integer medId) {
        Medical medical = find(medId);
        delete(medical);
    }

    @Override
    public Medical getMed(Integer medId) {
        return find(medId);
    }

    @Override
    public Medical updateMed(Medical medical) {
        merge(medical);
        return medical;
    }

    @Override
    public Medical findMedByQueue(String queue) {
        Query query = getEm().createQuery("select medical from Medical as medical" +
                " where medical.queue =:queue", Medical.class);
        query.setParameter("queue", queue);
        return (Medical) query.getSingleResult();
    }

    @Override
    public boolean isExist(int id) {
        Query query = getEm().createQuery("select case when count (e)>0 " +
                "then true " +
                "else false end from Medical e where e.id =:id", Boolean.class);
        query.setParameter(id, id);
        return (Boolean) query.getSingleResult();
    }
}
