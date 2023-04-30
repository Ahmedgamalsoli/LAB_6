public class Container implements Comparable<Container> {
    private String ContainerUUID;
    private String ShortName;
    private String LongName;
    public String getContainerUUID(){
        return ContainerUUID;
    }
    public String getShortName(){
        return ShortName;
    }
    public String getLongName(){
        return LongName;
    }
    public void setContainerUUID(String Container){
        ContainerUUID=Container;
    }
    public void setShortName(String ShortN){
        ShortName=ShortN;
    }
    public void setLongName(String LongN){
        LongName=LongN;
    }
    @Override
    public String toString(){
        return "   <CONTAINER " + getContainerUUID() + ">\n" +
                "   <SHORT-NAME>" + getShortName() + "</SHORT-NAME>\n" +
                "   <LONG-NAME>" + getLongName() + "</LONG-Name>\n" +
                "   </CONTAINER>\n" ;
    }

    @Override
    public int compareTo(Container o) {
        if(this.getShortName().charAt(9)>o.getShortName().charAt(9)) return 1;
        else if(this.getShortName().charAt(9)<o.getShortName().charAt(9)) return -1;
        else return 0;
    }
}