import java.io.*;
public class Output{

  public Output(){
    //write_file("filename.txt");

	}
  public void write_file(String filename, String type, String message){
    if(type.equals(".lst")){
      filename = modify_filename(filename, type);
    }
    else if(type.equals(".obj")){
      filename = modify_filename(filename, type);
    }

    Writer writer = null;

    try {
        writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(filename, true), "utf-8"));
        writer.append(message);
    } catch (IOException ex) {
      // report
    } finally {
       try {writer.close();} catch (Exception ex) {/*ignore*/}
    }
  }
  public String modify_filename(String filename, String type){

    return filename.substring(0, filename.length()-4)+type;
  }

}
