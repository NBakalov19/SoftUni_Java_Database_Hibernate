package hiberspring.service.implementation;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.BranchImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class BranchServiceImpl implements BranchService {

  private final BranchRepository branchRepository;
  private final TownRepository townRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository,
                           ValidationUtil validatorUtil, FileUtil fileUtil,
                           ModelMapper mapper, Gson gson) {
    this.branchRepository = branchRepository;
    this.townRepository = townRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean branchesAreImported() {
    return this.branchRepository.count() > 0;
  }

  @Override
  public String readBranchesJsonFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "/branches.json");
  }

  @Override
  public String importBranches(String branchesFileContent) {

    StringBuilder sb = new StringBuilder();

    BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesFileContent, BranchImportDto[].class);

    for (BranchImportDto branchImportDto : branchImportDtos) {

      Branch branch = this.mapper.map(branchImportDto, Branch.class);

      Town town = this.townRepository.findByName(branchImportDto.getTown());

      branch.setTown(town);

      if (!this.validatorUtil.isValid(branch)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);
        continue;
      }

      this.branchRepository.saveAndFlush(branch);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              branch.getClass().getSimpleName(),
              branch.getName()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}