package retake.instagraph.service;

import java.io.IOException;


public interface PictureService {
  String readPicturesFromJsonFile() throws IOException;

  String importPictures(String pictures);

  Boolean picturesAreImported();

  String exportPictures();

}
