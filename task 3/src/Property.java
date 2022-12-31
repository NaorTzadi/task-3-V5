public class Property {
    private final String CITY_OF_PROPERTY;
    private final String STREET_OF_PROPERTY;
    private final int TYPE_OF_PROPERTY;
    private final String FLAT_OF_PROPERTY;
    private final String  NAME_OF_SELLER;
    private final String NUMBER_OF_ROOMS;
    private final String PRICE_OF_PROPERTY;
    private final String NUMBER_OF_PROPERTY;
    private final boolean IF_FOR_SALE;
    public Property(String NAME_OF_SELLER, String CITY_OF_PROPERTY, String STREET_OF_PROPERTY, int TYPE_OF_PROPERTY,
                    String FLAT_OF_PROPERTY, String NUMBER_OF_ROOMS, String PRICE_OF_PROPERTY, String NUMBER_OF_PROPERTY
            , boolean IF_FOR_SALE){
        this.NAME_OF_SELLER=NAME_OF_SELLER;
        this.CITY_OF_PROPERTY=CITY_OF_PROPERTY;
        this.STREET_OF_PROPERTY=STREET_OF_PROPERTY;
        this.TYPE_OF_PROPERTY=TYPE_OF_PROPERTY;
        this.FLAT_OF_PROPERTY=FLAT_OF_PROPERTY;
        this.NUMBER_OF_PROPERTY=NUMBER_OF_PROPERTY;
        this.NUMBER_OF_ROOMS=NUMBER_OF_ROOMS;
        this.PRICE_OF_PROPERTY=PRICE_OF_PROPERTY;
        this.IF_FOR_SALE=IF_FOR_SALE;
    }
    public String getNAME_OF_SELLER() {return NAME_OF_SELLER;}
    public String getCITY_OF_PROPERTY() {return CITY_OF_PROPERTY;}
    public String getSTREET_OF_PROPERTY() {return STREET_OF_PROPERTY;}
    public int getTYPE_OF_PROPERTY() {return TYPE_OF_PROPERTY;}
    public String getFLAT_OF_PROPERTY(){return FLAT_OF_PROPERTY;}
    public String getNUMBER_OF_ROOMS(){return NUMBER_OF_ROOMS;}
    public String getNUMBER_OF_PROPERTY(){return NUMBER_OF_PROPERTY;}
    public String getPRICE_OF_PROPERTY(){return PRICE_OF_PROPERTY;}
    public boolean isIF_FOR_SALE(){return IF_FOR_SALE;}
}