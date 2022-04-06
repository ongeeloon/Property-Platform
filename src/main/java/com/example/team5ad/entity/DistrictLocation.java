package com.example.team5ad.entity;

/*Used by EeLoon*/

public enum DistrictLocation {
    DST1("01 - Boat Quay / Havelock / Marina / Raffles Place / Suntec"),
    DST2("02 - Chinatown / Raffles Place / Shenton / Tanjong Pagar"),
    DST3("03 - Alexandra / Tiong Bahru / Queenstown"),
    DST4("04 - Keppel / Mount Faber / Sentosa / Telok Blangah"),
    DST5("05 - Buona Vista / Dover / Pasir Panjang / West Coast"),
    DST6("06 - City Hall / High Street / North Bridge Road"),
    DST7("07 - Beach Road / Bencoolen / Bugis / Rochor"),
    DST8("08 - Little India / Farrer Park / Serangoon"),
    DST9("09 - Cairnhill / Leonie Hill / Orchard / Oxley"),
    DST10("10 - Bukit Timah / Holland / River Valley / Tanglin Road"),
    DST11("11 - Chancery / Bukit Timah / Dunearn Road / Newton"),
    DST12("12 - Balestier / Novena / Toa Payoh"),
    DST13("13 - Potong Pasir / Macpherson"),
    DST14("14 - Eunos / Geylang / Kembangan / Paya Lebar"),
    DST15("15 - Katong / Marine Parade / Siglap / Tanjong Rhu"),
    DST16("16 - Bayshore / Bedok / Chai Chee"),
    DST17("17 - Changi / Loyang / Pasir Ris"),
    DST18("18 - Pasir Ris / Simei / Tampines"),
    DST19("19 - Hougang / Punggol / Sengkang"),
    DST20("20 - Ang Mo Kio / Bishan / Braddell / Thomson"),
    DST21("21 - Clementi / Upper Bukit Timah / Hume Avenue"),
    DST22("22 - Boon Lay / Jurong / Tuas"),
    DST23("23 - Bukit Batok / Choa Chu Kang / Hillview"),
    DST24("24 - Kranji / Lim Chu Kang / Sungei Gedong / Tengah"),
    DST25("25 - Admiralty / Woodlands"),
    DST26("26 - Tagore / Yio Chu Kang"),
    DST27("27 - Admiralty / Sembawang / Yishun"),
    DST28("28 - Seletar / Yio Chu Kang");

    private final String district;

    DistrictLocation(String district){
        this.district = district;
    }

    public String getDistrict(){
        return district;
    }
}
