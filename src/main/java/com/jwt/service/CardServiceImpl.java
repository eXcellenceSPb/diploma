package com.jwt.service;

import com.jwt.dao.CardDAO;
import com.jwt.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cardService")
public class CardServiceImpl implements CardService {

    private final CardDAO cardDAO;

    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public void addCard(Card card) {
        cardDAO.addCard(card);
    }

    @Override
    public List<Card> getAll() {
        return cardDAO.getAll();
    }

    @Override
    public void deleteCard(Integer cardId) {
        cardDAO.deleteCard(cardId);
    }

    @Override
    public Card getCard(Integer cardId) {
        return cardDAO.getCard(cardId);
    }

    @Override
    public Card updateCard(Card card) {
        return cardDAO.updateCard(card);
    }

    @Override
    public Card findCardByNum(String number) {
        return cardDAO.findCardByNum(number);
    }

    @Override
    public boolean isExist(String num) {
        return cardDAO.isExist(num);
    }

    @Override
    public void addMedtoCard(int cardId, int medId) {
        cardDAO.addMedtoCard(cardId, medId);
    }
}
