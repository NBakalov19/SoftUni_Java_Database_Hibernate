package com.bakalov.gamestoredatabase.services;

import com.bakalov.gamestoredatabase.domains.dtos.GameAddDto;

import java.util.List;


public interface GameService {

  String addGame(GameAddDto gameAddDto);

  String editGame(Integer id, List<String> arguments);

  String deleteGame(Integer id);

  List<String> getAllGamesTitleAndPrice();

  String getDetailsForGameByTitle(String title);

  List<String> getOwnedGames();
}
