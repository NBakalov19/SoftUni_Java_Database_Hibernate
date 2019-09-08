package retake.instagraph.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface PostService {
  String readPostFromXmlFile() throws IOException;

  String importPosts(String posts) throws JAXBException, FileNotFoundException;

  Boolean postsAreImported();

}
