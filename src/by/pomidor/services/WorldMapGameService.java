package by.pomidor.services;

import by.pomidor.domain.Country;
import by.pomidor.domain.User;

public interface WorldMapGameService {

    void start(User user);

    String nextQuestion();

    Boolean answer(String answer);

    boolean isEnded();

    void end();

    Integer count();

    Integer totalCount();

    Country getCurrentCountry();
}
