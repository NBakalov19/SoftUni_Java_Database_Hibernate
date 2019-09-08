package com.bakalov.xmlproccesing.services.implementations;

import com.bakalov.xmlproccesing.domains.dtos.binds.PartSeedDto;
import com.bakalov.xmlproccesing.domains.dtos.binds.roots.PartRootDto;
import com.bakalov.xmlproccesing.domains.entities.Part;
import com.bakalov.xmlproccesing.domains.entities.Supplier;
import com.bakalov.xmlproccesing.repositories.PartRepository;
import com.bakalov.xmlproccesing.repositories.SupplierRepository;
import com.bakalov.xmlproccesing.services.PartService;
import com.bakalov.xmlproccesing.utils.ValidatorUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.Random;


@Service
public class PartServiceImpl implements PartService {
  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;
  private final PartRepository partRepository;
  private final SupplierRepository supplierRepository;


  @Autowired
  public PartServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper,
                         XmlParser xmlParser, PartRepository partRepository,
                         SupplierRepository supplierRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
    this.partRepository = partRepository;
    this.supplierRepository = supplierRepository;
  }

  @Override
  public void seedParts(BufferedReader reader) {
    PartRootDto uncheckedPartRootDto = this.xmlParser.parseXml(PartRootDto.class, reader);

    PartRootDto checkedPartRootDto = new PartRootDto();

    for (PartSeedDto partSeedDto : uncheckedPartRootDto.getPartSeedDtos()) {
      if (!this.validatorUtil.isValid(partSeedDto)) {
        this.validatorUtil.violations(partSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedPartRootDto.getPartSeedDtos().add(partSeedDto);
    }

    checkedPartRootDto.getPartSeedDtos()
            .stream()
            .map(dto -> {

              Part part = this.mapper.map(dto, Part.class);
              part.setSupplier(this.getRandomSupplier());

              return part;
            })
            .forEach(this.partRepository::saveAndFlush);
  }

  private Supplier getRandomSupplier() {
    Random random = new Random();

    int id = random.nextInt((int) this.supplierRepository.count() - 1) + 1;

    return this.supplierRepository.getOne(id);
  }
}
