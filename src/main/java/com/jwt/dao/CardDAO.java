package com.jwt.dao;

import com.jwt.model.Card;
import com.jwt.model.Medical;

import java.util.List;

public interface CardDAO {
    void addCard(Card card);

    List<Card> getAll();

    void deleteCard(Integer cardId);

    Card getCard(Integer cardId);

    Card updateCard(Card card);

    Card findCardByNum(String number);

    boolean isExist(String num);

    List<Medical> getByCardId(int cardId);
}
