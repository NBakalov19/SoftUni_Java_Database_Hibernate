package app.ccb.services;

import app.ccb.domain.dtos.json.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.utils.FileUtil;
import app.ccb.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static app.ccb.commons.Constant.*;

@Service
public class BranchServiceImpl implements BranchService {

  private final BranchRepository branchRepository;
  private final ValidationUtil validationUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public BranchServiceImpl(BranchRepository branchRepository, ValidationUtil validationUtil,
                           FileUtil fileUtil, ModelMapper mapper, Gson gson) {
    this.branchRepository = branchRepository;
    this.validationUtil = validationUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean branchesAreImported() {
    return this.branchRepository.count() != 0;
  }

  @Override
  public String readBranchesJsonFile() throws IOException {
    return this.fileUtil.readFile(BRANCHES_JSON_FILE_PATH);
  }

  @Override
  public String importBranches(String branchesJson) {
    StringBuilder sb = new StringBuilder();

    BranchImportDto[] dtos = this.gson.fromJson(branchesJson, BranchImportDto[].class);

    for (BranchImportDto dto : dtos) {

      Branch branch = this.mapper.map(dto, Branch.class);

      if (!this.validationUtil.isValid(branch)) {
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
