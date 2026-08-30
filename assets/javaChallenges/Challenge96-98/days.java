package Challenge9698;

public enum days {
    Monday(false),
    Tuesday(false),
    Wednesday(false),
    Thursday(false),
    Friday(false),
    Saturday(true),
    Sunday(true);


   private boolean Weekend;

    days(boolean weekend) {
        Weekend = weekend;
    }
    public String isWeekend(){
        return Weekend?"Weekend":"Weekday";
    }
}
