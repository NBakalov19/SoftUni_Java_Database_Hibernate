package com.bakalov.xmlproccesing.services.implementations;

import com.bakalov.xmlproccesing.domains.dtos.binds.SupplierSeedDto;
import com.bakalov.xmlproccesing.domains.dtos.binds.roots.SupplierRootDto;
import com.bakalov.xmlproccesing.domains.dtos.views.LocalSuppliersDto;
import com.bakalov.xmlproccesing.domains.entities.Supplier;
import com.bakalov.xmlproccesing.repositories.SupplierRepository;
import com.bakalov.xmlproccesing.services.SupplierService;
import com.bakalov.xmlproccesing.utils.ValidatorUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final SupplierRepository supplierRepository;
  private final XmlParser xmlParser;

  @Autowired
  public SupplierServiceImpl(ValidatorUtil validatorUtil,
                             ModelMapper mapper, SupplierRepository supplierRepository, XmlParser xmlParser) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.supplierRepository = supplierRepository;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedSuppliers(BufferedReader reader) {
    SupplierRootDto uncheckedDtos = this.xmlParser.parseXml(SupplierRootDto.class, reader);

    SupplierRootDto checkedDtos = new SupplierRootDto();
    for (SupplierSeedDto supplierSeedDto : uncheckedDtos.getSupplierSeedDtos()) {
      if (!this.validatorUtil.isValid(supplierSeedDto)) {
        this.validatorUtil.violations(supplierSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedDtos.getSupplierSeedDtos().add(supplierSeedDto);
    }

//    If xml parse not read boolean
//    for (SupplierSeedDto supplierDto : dtos.getSupplierSeedDtos()) {
//      supplierDto.setImporter(supplierDto.getImporter());
//      Supplier supplier = this.mapper.map(supplierDto,Supplier.class);
//      this.supplierRepository.saveAndFlush(supplier);
//    }
    checkedDtos.getSupplierSeedDtos()
            .stream()
            .map(s -> this.mapper.map(s, Supplier.class))
            .forEach(this.supplierRepository::saveAndFlush);
  }

  @Override
  public List<LocalSuppliersDto> localSuppliers() {

    return this.supplierRepository.findAll()
            .stream()
            .filter(Supplier::getImporter)
            .map(supplier -> {
              LocalSuppliersDto localSuppliersDto =
                      this.mapper.map(supplier, LocalSuppliersDto.class);

              localSuppliersDto.setPartsCount(supplier.getParts().size());

              return localSuppliersDto;
            })
            .collect(Collectors.toList());
  }
}
