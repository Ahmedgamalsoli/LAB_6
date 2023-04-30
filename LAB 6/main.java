import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class main {

    public static void main(String[] args) {
        try {
            String filename = args[0];

            if (!filename.endsWith(".arxml"))
            {throw new NotVaildAutosarFileException("Invalid file extension (Not arxml)");}

            File file = new File(filename);
            FileInputStream inputStream = new FileInputStream(file);

            int x;
            StringBuilder stringBuilder= new StringBuilder();
            if(inputStream.read()==-1){
                throw new AutosarFileNotFoundException ("EmptyAutosarFileException");}

            while( (x=inputStream.read()) != -1 )  stringBuilder.append((char) x);

            String data=stringBuilder.toString();
            Scanner input = new Scanner(data);

            ArrayList<Container> containers = new ArrayList<>();

            while(input.hasNextLine())
            {
                String line=input.nextLine();

                if(line.contains("<CONTAINER")){
                    String ContainerUUID=line.substring(line.indexOf("UUID="),line.indexOf(">"));

                    String ShortNLine=input.nextLine();
                    String ShortName=ShortNLine.substring(ShortNLine.indexOf(">")+1,ShortNLine.indexOf("</"));

                    String LongNLine=input.nextLine();
                    String LongName=LongNLine.substring(LongNLine.indexOf(">")+1,LongNLine.indexOf("</"));

                    Container container = new Container();

                    container.setContainerUUID(ContainerUUID);
                    container.setLongName(LongName);
                    container.setShortName(ShortName);

                    containers.add(container);
                }
            }
            Collections.sort(containers);

            String outputName = filename.substring( 0 , filename.indexOf(".")) + "_mod";
            FileOutputStream outputStream = new FileOutputStream(outputName);
            outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            outputStream.write("<AUTOSAR>\n".getBytes());

            for (int i=0 ; i<containers.size() ; i++) outputStream.write(containers.get(i).toString().getBytes());

            outputStream.write("</AUTOSAR\n".getBytes());
        }

        catch (java.io.FileNotFoundException e) {
            throw new RuntimeException(e);}

        //catch(EmptyAutosarFileException e) {}

        catch (java.io.IOException e) {
            throw new RuntimeException(e);}

        catch (NotVaildAutosarFileException e) {
            throw new RuntimeException(e);}

        catch (AutosarFileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class AutosarFileNotFoundException extends Exception{
    public AutosarFileNotFoundException(String s){
        super(s);}
}

class EmptyAutosarFileException extends Exception{
    public EmptyAutosarFileException(String s){
        super(s);}
    }

class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String s) {
        super(s);}
}
//class IOException extends Exception {
//    public IOException(String s) {
//        super(s);}
//}