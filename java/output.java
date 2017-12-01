import java.io.*;
public class output{

  public output()throws FileNotFoundException{
    //write_file("filename.txt");

	}
  public void write_old_file(String filename, String message)
  {
    Writer writer = null;

    try {
        writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("filename.txt", true), "utf-8"));
        writer.append(message);
    } catch (IOException ex) {
      // report
    } finally {
       try {writer.close();} catch (Exception ex) {/*ignore*/}
    }
  }

}
