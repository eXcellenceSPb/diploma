package com.jwt.service;

import com.jwt.model.Card;

import java.util.List;

public interface CardService {
    void addCard(Card card);

    List<Card> getAll();

    void deleteCard(Integer cardId);

    Card getCard(Integer cardId);

    Card updateCard(Card card);

    Card findCardByNum(String number);

    boolean isExist(String num);
}
