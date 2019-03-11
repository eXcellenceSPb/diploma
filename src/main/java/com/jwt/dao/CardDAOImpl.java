package com.jwt.dao;

import com.jwt.model.Card;
import com.jwt.model.Medical;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("CardDAO")
@Transactional
public class CardDAOImpl extends AbstractDao<Integer, Card> implements CardDAO {

    private MedicalDAO medicalDAO;

    public CardDAOImpl() {
        medicalDAO = null;
    }

    @Override
    public void addCard(Card card) {
        persist(card);
    }

    @Override
    public List<Card> getAll() {
        return getEm().createQuery("select a from Card a", Card.class)
                .getResultList();
    }

    @Override
    public void deleteCard(Integer cardId) {
        Card card = find(cardId);
        delete(card);
    }

    @Override
    public Card getCard(Integer cardId) {
        return find(cardId);
    }

    @Override
    public Card updateCard(Card card) {
        merge(card);
        return card;
    }

    @Override
    public List<Medical> getByCardId(int cardId){
        return new ArrayList<>(find(cardId).getMedical());
    }

    @Override
    public Card findCardByNum(String number) {
        Query query = getEm().createQuery("select card from Card as card" +
                " where card.number =:number", Card.class);
        query.setParameter("number", number);
        return (Card) query.getSingleResult();
    }

    @Override
    public boolean isExist(String number) {
        Query query = getEm().createQuery("select case when count (e)>0 " +
                "then true " +
                "else false end from Card e where e.number =:number", Boolean.class);
        query.setParameter("number", number);
        return (Boolean) query.getSingleResult();
    }
}
