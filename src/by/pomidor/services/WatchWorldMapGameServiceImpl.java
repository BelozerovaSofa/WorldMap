package by.pomidor.services;

import by.pomidor.domain.Country;
import by.pomidor.domain.User;

public class WatchWorldMapGameServiceImpl implements WorldMapGameService {

    @Override
    public void start(User user) {

    }

    @Override
    public String nextQuestion() {
        return null;
    }

    @Override
    public Boolean answer(String answer) {
        return null;
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    @Override
    public void end() {

    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Integer totalCount() {
        return null;
    }

    @Override
    public Country getCurrentCountry() {
        return null;
    }
}
