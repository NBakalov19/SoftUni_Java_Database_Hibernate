package com.bakalov.jsonparsecardealer.services;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.PartSeedDto;

public interface PartService {

  void seedParts(PartSeedDto[] partSeedDtos);
}
