package com.bakalov.jsonparsecardealer.services.implementations;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.SupplierSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.LocalSuppliersDto;
import com.bakalov.jsonparsecardealer.domains.entities.Supplier;
import com.bakalov.jsonparsecardealer.repositories.SupplierRepository;
import com.bakalov.jsonparsecardealer.services.SupplierService;
import com.bakalov.jsonparsecardealer.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SupplierServiceImpl implements SupplierService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private SupplierRepository supplierRepository;

  @Autowired
  public SupplierServiceImpl(ValidatorUtil validatorUtil,
                             ModelMapper mapper, SupplierRepository supplierRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.supplierRepository = supplierRepository;
  }

  @Override
  public void seedSuppliers(SupplierSeedDto[] supplierSeedDtos) {

    for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
      if (!this.validatorUtil.isValid(supplierSeedDto)) {
        this.validatorUtil.violations(supplierSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }

      Supplier supplier = this.mapper.map(supplierSeedDto, Supplier.class);

      this.supplierRepository.saveAndFlush(supplier);
    }
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

            }).collect(Collectors.toList());
  }
}
