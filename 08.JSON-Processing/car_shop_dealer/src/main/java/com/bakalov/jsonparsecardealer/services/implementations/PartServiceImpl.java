package com.bakalov.jsonparsecardealer.services.implementations;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.PartSeedDto;
import com.bakalov.jsonparsecardealer.domains.entities.Part;
import com.bakalov.jsonparsecardealer.domains.entities.Supplier;
import com.bakalov.jsonparsecardealer.repositories.PartRepository;
import com.bakalov.jsonparsecardealer.repositories.SupplierRepository;
import com.bakalov.jsonparsecardealer.services.PartService;
import com.bakalov.jsonparsecardealer.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class PartServiceImpl implements PartService {
  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final PartRepository partRepository;
  private final SupplierRepository supplierRepository;

  @Autowired
  public PartServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper,
                         PartRepository partRepository, SupplierRepository supplierRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.partRepository = partRepository;
    this.supplierRepository = supplierRepository;
  }

  @Override
  public void seedParts(PartSeedDto[] partSeedDtos) {
    for (PartSeedDto partSeedDto : partSeedDtos) {
      if (!this.validatorUtil.isValid(partSeedDto)) {
        this.validatorUtil.violations(partSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }

      Part part = this.mapper.map(partSeedDto, Part.class);
      part.setSupplier(this.getRandomSupplier());

      this.partRepository.saveAndFlush(part);
    }
  }

  private Supplier getRandomSupplier() {
    Random random = new Random();

    int id = random.nextInt((int) this.supplierRepository.count() - 1) + 1;

    return this.supplierRepository.getOne(id);
  }
}
